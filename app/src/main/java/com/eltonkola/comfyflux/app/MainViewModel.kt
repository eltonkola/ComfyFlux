package com.eltonkola.comfyflux.app

import android.content.Context
import android.graphics.Bitmap
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.eltonkola.comfyflux.R
import com.eltonkola.comfyflux.app.model.SystemStats
import com.eltonkola.comfyflux.app.model.Workflow
import com.eltonkola.comfyflux.app.model.workflows
import com.eltonkola.comfyflux.app.netwrok.DEFAULT_URL
import com.eltonkola.comfyflux.app.netwrok.FluxAPI
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.InputStream
import java.nio.charset.Charset

data class ImageGenerationUiState(
    val prompt: String = "a cat with a hat",
    val server: String = DEFAULT_URL,
    val images: Map<String, List<ByteArray>> = emptyMap(),
    val isLoading: Boolean = false,
    val error: String? = null,
    val image: Bitmap? = null,

    val loadingStats: Boolean = false,
    val stats: SystemStats? = null,
    val workflow: Workflow = workflows.first(),
    val width: Int = 512,
    val height: Int = 512,
    val batchSize: Int = 1
)

class ImageGenerationViewModelFactory(private val context: Context) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return MainViewModel(context) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}

class MainViewModel(
    private val context: Context,
    private val fluxAPI: FluxAPI = FluxAPI()
) : ViewModel() {

    private val _uiState = MutableStateFlow(ImageGenerationUiState())
    val uiState: StateFlow<ImageGenerationUiState> = _uiState.asStateFlow()

    init {
        checkStatus()
    }

    fun updateSeverUrl(url: String){
        viewModelScope.launch {
            fluxAPI.setServerAddress(url)
            _uiState.update { it.copy(server = url) }
        }
    }

    fun updatePrompt(prompt: String){
        viewModelScope.launch {
            _uiState.update { it.copy(prompt = prompt) }
        }
    }

    fun updateWorkflow(workflow: Workflow){
        viewModelScope.launch {
            _uiState.update { it.copy(workflow = workflow) }
        }
    }


    fun setCurrentImage(image: Bitmap?){
        viewModelScope.launch {
            _uiState.update { it.copy(image = image) }
        }
    }

    fun setImageWidth(width: Int){
        viewModelScope.launch {
            _uiState.update { it.copy(width = width) }
        }
    }

    fun setImageHeight(height: Int){
        viewModelScope.launch {
            _uiState.update { it.copy(height = height) }
        }
    }

    fun setBatchSize(batchSize: Int){
        viewModelScope.launch {
            _uiState.update { it.copy(batchSize = batchSize) }
        }
    }

    fun generateImages() {
        viewModelScope.launch {

            _uiState.update { it.copy(isLoading = true, error = null) }
            try {

                val prompt = loadWorkflow()


                Log.d("FluxApp", "prompt: $prompt")

                val result = withContext(Dispatchers.IO) {
                    fluxAPI.getImages(prompt)
                }

                _uiState.update { it.copy(images = result, isLoading = false) }
            } catch (e: Exception) {
                _uiState.update { it.copy(error = e.message, isLoading = false) }
            }
        }
    }

    fun checkStatus(){
        _uiState.update { it.copy(loadingStats = true) }
        viewModelScope.launch {
            val status = fluxAPI.checkSystemStats()
            _uiState.update { it.copy(loadingStats = false, stats = status) }

        }

    }

    private fun loadWorkflow() : String {
        val workflow = _uiState.value.workflow
        val promptRaw = context.resources.openRawResource(workflow.workflowRes)
        var prompt = promptRaw.readTextAndClose()
        prompt = prompt.replace("__prompt__", uiState.value.prompt)
        prompt = prompt.replace("__width__", uiState.value.width.toString())
        prompt = prompt.replace("__height__", uiState.value.height.toString())
        prompt = prompt.replace("__batch_size__", uiState.value.batchSize.toString())

        return """{
  "client_id": "${fluxAPI.clientId}",
  "prompt": $prompt
}""".trimIndent()
    }

}

fun InputStream.readTextAndClose(charset: Charset = Charsets.UTF_8): String {
    return this.bufferedReader(charset).use { it.readText() }
}
