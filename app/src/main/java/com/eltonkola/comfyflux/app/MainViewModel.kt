package com.eltonkola.comfyflux.app

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import com.eltonkola.comfyflux.data.AppSettings
import com.eltonkola.comfyflux.data.PromptRepo
import com.eltonkola.comfyflux.data.SettingsState
import com.eltonkola.comfyflux.data.model.HistoryUiState
import com.eltonkola.comfyflux.data.model.ImageGenerationUiState
import com.eltonkola.comfyflux.data.model.ImageViewerUiState
import com.eltonkola.comfyflux.data.model.ProgressGenerationUIState
import com.eltonkola.comfyflux.data.model.Queue
import com.eltonkola.comfyflux.data.model.QueueUiState
import com.eltonkola.comfyflux.data.model.WorkflowFile
import com.eltonkola.comfyflux.data.netwrok.FluxAPI
import com.eltonkola.comfyflux.app.usecase.HistoryUseCase
import com.eltonkola.comfyflux.app.usecase.ImageViewerUseCase
import com.eltonkola.comfyflux.app.usecase.MainUseCase
import com.eltonkola.comfyflux.app.usecase.PromptSearchUseCase
import com.eltonkola.comfyflux.app.usecase.QueueUseCase
import com.eltonkola.comfyflux.app.usecase.TimerUseCase
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch


class MainViewModel(
    private val applicationContext: Application,
    private val fluxAPI: FluxAPI,
    private val promptRepo: PromptRepo,
    private val promptSearchUseCase: PromptSearchUseCase,
    private val historyUseCase: HistoryUseCase,
    private val imageViewerUseCase: ImageViewerUseCase,
    private val queueUseCase: QueueUseCase,
    private val mainUseCase: MainUseCase,
    private val appSettings: AppSettings,
    private val timerUseCase: TimerUseCase
) : AndroidViewModel(applicationContext) {

    val promptSearchPaging: StateFlow<PagingData<String>> = promptSearchUseCase.promptSearchPaging
    val historyUiState: StateFlow<HistoryUiState> = historyUseCase.historyUiState
    val progressUiState: StateFlow<ProgressGenerationUIState> = fluxAPI.progressUiState
    val imageUiState: StateFlow<ImageViewerUiState> = imageViewerUseCase.imageUiState
    val queueUiState: StateFlow<QueueUiState> = queueUseCase.queueUiState
    val uiState: StateFlow<ImageGenerationUiState> = mainUseCase.uiState
    val settingsState: StateFlow<SettingsState> = appSettings.settingsState
    val timer: StateFlow<String> = timerUseCase.timer

    init {
        checkStatus()
        searchLines("")
    }

    fun searchLines(query: String) {
        viewModelScope.launch {
            promptSearchUseCase.searchLines(query, viewModelScope)
        }
    }

    fun updateSeverUrl(url: String) {
        viewModelScope.launch {
            mainUseCase.updateSeverUrl(url)
        }
    }

    fun viewImage(images: List<String> = emptyList(), selected: Int = 0) {
        imageViewerUseCase.viewImage(images, selected)
    }

    fun updatePrompt(prompt: String) {
        viewModelScope.launch {
            mainUseCase.updatePrompt(prompt)
        }
    }

    fun updateWorkflow(workflow: WorkflowFile) {
        viewModelScope.launch {
            mainUseCase.updateWorkflow(workflow)
        }
    }

    fun setImageWidth(width: Int) {
        viewModelScope.launch {
            mainUseCase.setImageWidth(width)
        }
    }

    fun setImageHeight(height: Int) {
        viewModelScope.launch {
            mainUseCase.setImageHeight(height)
        }
    }

    fun setBatchSize(batchSize: Int) {
        viewModelScope.launch {
            mainUseCase.setBatchSize(batchSize)
        }
    }

    fun setSeed(seed: Long, isRandom: Boolean) {
        viewModelScope.launch {
            mainUseCase.setSeed(seed, isRandom)
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
            mainUseCase.generateImages(
                start = {
                    viewModelScope.launch {
                        timerUseCase.resetTimer()
                        timerUseCase.startTimer()
                    }
                },
                stop = {
                    timerUseCase.stopTimer()
                }
            )
        }
    }

    fun enrichPrompt() {
        viewModelScope.launch {
            mainUseCase.enrichPrompt()
        }
    }

    fun checkStatus() {
        viewModelScope.launch {
            mainUseCase.checkStatus()
        }
    }

    fun randomSeed() {
        mainUseCase.randomSeed()
    }

    fun loadHistory(silentUpdate: Boolean = false) {
        viewModelScope.launch {
            historyUseCase.loadHistory(silentUpdate) {
                checkStatus()
            }
        }
    }

    fun loadQueue(showLoading: Boolean = true) {
        viewModelScope.launch {
            queueUseCase.loadQueue(showLoading) {
                checkStatus()
            }
        }
    }

    fun cancelQueueWorkflow(workflow: Queue.Workflow) {
        viewModelScope.launch {
            fluxAPI.cancelWorkflow(workflow.id)
            loadQueue(showLoading = false)
        }
    }

    fun deleteHistory(id: String) {
        viewModelScope.launch {
            fluxAPI.deleteHistory(id)
            loadHistory(silentUpdate = true)
        }
    }

    fun randomPrompt() {
        viewModelScope.launch {
            val prompt = promptRepo.randomPrompt()
            updatePrompt(prompt)
        }
    }

    override fun onCleared() {
        super.onCleared()
        timerUseCase.resetTimer()
    }

    fun updateSystemTheme(system: Boolean) {
        appSettings.setSystemTheme(system)
    }

    fun updateDarkTheme(dark: Boolean) {
        appSettings.setDarkTheme(dark)
    }

    fun updateDynamicColor(dynamic: Boolean) {
        appSettings.setDynamicColor(dynamic)
    }

    fun setGrqApiKey(key: String) {
        appSettings.setGroqApiKey(key)
    }

}
