package com.eltonkola.comfyflux.app

import android.content.Context
import android.graphics.Bitmap
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.eltonkola.comfyflux.R
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
    val image: Bitmap? = null
)

class ImageGenerationViewModelFactory(private val context: Context) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ImageGenerationViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return ImageGenerationViewModel(context) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}

class ImageGenerationViewModel(
    private val context: Context,
    private val fluxAPI: FluxAPI = FluxAPI()
) : ViewModel() {

    private val _uiState = MutableStateFlow(ImageGenerationUiState())
    val uiState: StateFlow<ImageGenerationUiState> = _uiState.asStateFlow()


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

    fun setCurrentImage(image: Bitmap?){
        viewModelScope.launch {
            _uiState.update { it.copy(image = image) }
        }
    }

    fun generateImages() {
        viewModelScope.launch {

            _uiState.update { it.copy(isLoading = true, error = null) }
            try {

                val promptRaw = context.resources.openRawResource(R.raw.flux_api)

                var prompt = promptRaw.readTextAndClose()
                prompt = prompt.replace("__prompt__", uiState.value.prompt)
                prompt = prompt.replace("__clientId__", fluxAPI.clientId)


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


}

fun InputStream.readTextAndClose(charset: Charset = Charsets.UTF_8): String {
    return this.bufferedReader(charset).use { it.readText() }
}
