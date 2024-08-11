package com.eltonkola.comfyflux.app

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.eltonkola.comfyflux.app.data.PromptRepo
import com.eltonkola.comfyflux.app.model.HistoryUiState
import com.eltonkola.comfyflux.app.model.ProgressGenerationUIState
import com.eltonkola.comfyflux.app.model.PromptRequest
import com.eltonkola.comfyflux.app.model.Queue
import com.eltonkola.comfyflux.app.model.QueueUiState
import com.eltonkola.comfyflux.app.model.SystemStats
import com.eltonkola.comfyflux.app.model.Workflow
import com.eltonkola.comfyflux.app.model.WorkflowFile
import com.eltonkola.comfyflux.app.model.updatePrompt
import com.eltonkola.comfyflux.app.model.updateSeed
import com.eltonkola.comfyflux.app.model.updateSizeAndBatch
import com.eltonkola.comfyflux.app.model.workflows
import com.eltonkola.comfyflux.app.netwrok.DEFAULT_URL
import com.eltonkola.comfyflux.app.netwrok.FluxAPI
import com.eltonkola.comfyflux.app.netwrok.GroqAPI
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlinx.serialization.json.Json
import okhttp3.internal.applyConnectionSpec
import java.io.InputStream
import java.nio.charset.Charset
import kotlin.random.Random

data class ImageGenerationUiState(
    val prompt: String = "a cat with a hat",
    val server: String = DEFAULT_URL,

    val loadingStats: Boolean = false,
    val stats: SystemStats? = null,

    val workflow: WorkflowFile = workflows.first(),
    val width: Int = 512,
    val height: Int = 512,
    val batchSize: Int = 1,
    val seed: Long = generateRandom16DigitNumber(),
    val isRandom : Boolean = true
)

data class ImageViewerUiState(
    val images: List<String> = emptyList(),
    val selected: Int = 0
)

class MainViewModelFactory(private val application: Application) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return MainViewModel(application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}

class MainViewModel(
    private val applicationContext: Application,
    private val fluxAPI: FluxAPI = FluxAPI(),
    private val groqAPI: GroqAPI = GroqAPI(),
    ) : AndroidViewModel(applicationContext) {

    private val repository = PromptRepo(applicationContext)
    //prompt search
    private val _pagingDataFlow = MutableStateFlow<PagingData<String>>(PagingData.empty())
    val pagingDataFlow: StateFlow<PagingData<String>> = _pagingDataFlow


    //main screen state
    private val _uiState = MutableStateFlow(ImageGenerationUiState())
    val uiState: StateFlow<ImageGenerationUiState> = _uiState.asStateFlow()

    val progressUiState: StateFlow<ProgressGenerationUIState> = fluxAPI.progressUiState


    //history state
    private val _historyUiState = MutableStateFlow(HistoryUiState())
    val historyUiState: StateFlow<HistoryUiState> = _historyUiState.asStateFlow()

    //queue state
    private val _queueUiState = MutableStateFlow(QueueUiState())
    val queueUiState: StateFlow<QueueUiState> = _queueUiState.asStateFlow()

    //image viewer
    private val _imageUiState = MutableStateFlow(ImageViewerUiState())
    val imageUiState: StateFlow<ImageViewerUiState> = _imageUiState.asStateFlow()


    init {
        checkStatus()
        searchLines("")
    }


    fun searchLines(query: String) {
        viewModelScope.launch {
            Pager(
                config = PagingConfig(pageSize = 100),
                pagingSourceFactory = { repository.getPagingSource(query) }
            ).flow.cachedIn(viewModelScope).collectLatest {
                _pagingDataFlow.value = it
            }
        }
    }

    fun updateSeverUrl(url: String){
        viewModelScope.launch {
            fluxAPI.setServerAddress(url)
            _uiState.update { it.copy(server = url) }
        }
    }

    fun viewImage(images: List<String> = emptyList(), selected: Int = 0) {
        _imageUiState.value = ImageViewerUiState(images, selected)
    }

    fun updatePrompt(prompt: String){
        viewModelScope.launch {
            _uiState.update { it.copy(prompt = prompt) }
        }
    }

    fun updateWorkflow(workflow: WorkflowFile){
        viewModelScope.launch {
            _uiState.update { it.copy(workflow = workflow) }
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

    fun setSeed(seed: Long, isRandom: Boolean){
        viewModelScope.launch {
            _uiState.update { it.copy(seed = seed, isRandom = isRandom) }
        }
    }


    fun interruptImages() {
        viewModelScope.launch {
            fluxAPI.interrupt()
            checkStatus()
        }
    }

    fun generateImages() {
        viewModelScope.launch {
            try {
                val prompt = loadWorkflow()
                Log.d("FluxApp", "prompt: $prompt")
                withContext(Dispatchers.IO) {
                    fluxAPI.generateImage(prompt)
                }
                checkStatus()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun enrichPrompt(){
        viewModelScope.launch {
            try{
                val richPrompt = groqAPI.enrichPrompt(uiState.value.prompt)
                _uiState.update { it.copy(prompt = richPrompt) }

            }catch (e: Exception){
                e.printStackTrace()
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

    private fun loadWorkflow() : PromptRequest {

        val seed = if(uiState.value.isRandom) {
            val newSeed = generateRandom16DigitNumber()
            _uiState.update { it.copy(seed = newSeed) }
            newSeed
        } else {
            uiState.value.seed
        }

        val workflowConfig = _uiState.value.workflow
        val workflowRaw = applicationContext.resources.openRawResource(workflowConfig.workflowRes)
        val workflowInput = workflowRaw.readTextAndClose()
        val workflow = Json.decodeFromString<Workflow>(workflowInput)


        workflow.updateSizeAndBatch(
            width = uiState.value.width,
            height = uiState.value.height,
            batchSize = uiState.value.batchSize
        )

        workflow.updatePrompt(prompt = uiState.value.prompt.cleanPrompt())

        workflow.updateSeed(seed = seed)

        return PromptRequest(fluxAPI.clientId, workflow)
    }

    fun randomSeed() {
        _uiState.update { it.copy(seed = generateRandom16DigitNumber()) }
    }



    //history and queue

    fun loadHistory() {
        _historyUiState.update { it.copy(loading = true) }
        viewModelScope.launch {
            try{
                val history = fluxAPI.fetchHistory()
                Log.d("HistoryQueueViewModel", "history: ${history.size}")
                _historyUiState.update { it.copy(loading = false, history = history.reversed(), error = false) }
            }catch (e: Exception){
                _historyUiState.update { it.copy(loading = false, error = true) }
            }
            checkStatus()
        }
    }

    fun loadQueue(showLoading: Boolean = true) {
        if(showLoading) {
            _queueUiState.update { it.copy(loading = true) }
        }
        viewModelScope.launch {
            try{
                val queue = fluxAPI.fetchQueue()
                Log.d("HistoryQueueViewModel", "queue pending: ${queue.pending.size}")
                _queueUiState.update { it.copy(loading = false, queue = queue, error = false) }
            }catch (e: Exception){
                _queueUiState.update { it.copy(loading = false, error = true) }
            }
            checkStatus()
        }
    }

    fun cancelQueueWorkflow(workflow: Queue.Workflow) {
        viewModelScope.launch {
            fluxAPI.cancelWorkflow(workflow.id)
            loadQueue(showLoading = false)
            checkStatus()
        }
    }

    fun deleteHistory(id: String) {
        viewModelScope.launch {
            fluxAPI.deleteHistory(id)
            loadHistory()
        }
    }

    fun randomPrompt() {
        viewModelScope.launch {
            val prompt = repository.randomPrompt()
            updatePrompt(prompt)
        }
    }


}

fun InputStream.readTextAndClose(charset: Charset = Charsets.UTF_8): String {
    return this.bufferedReader(charset).use { it.readText() }
}

fun generateRandom16DigitNumber(): Long {
    val random = Random.nextLong(100000000000000, 999999999999999)
    return random
}

fun String.cleanPrompt() : String {
    return this
        .replace("\\", "\\\\") // Escape backslashes
        .replace("\"", "\\\"") // Escape double quotes
        .replace("\n", "") // Remove newlines
        .replace("\r", "") // Remove carriage returns
        .replace("\t", "") // Remove tabs
}