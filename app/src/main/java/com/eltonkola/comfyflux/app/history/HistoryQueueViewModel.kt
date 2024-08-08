package com.eltonkola.comfyflux.app.history


import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.eltonkola.comfyflux.app.netwrok.FluxAPI
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class HistoryUiState(
    val history: List<HistoryItem> = emptyList(),
    val loading: Boolean = true,
    val error: Boolean = false
)

data class QueueUiState(
    val queue: Queue? = null,
    val loading: Boolean = true,
    val error: Boolean = false
)

class HistoryQueueViewModel(
    private val fluxAPI: FluxAPI = FluxAPI()
    ) : ViewModel() {

    private val _historyUiState = MutableStateFlow(HistoryUiState())
    val historyUiState: StateFlow<HistoryUiState> = _historyUiState.asStateFlow()


    private val _queueUiState = MutableStateFlow(QueueUiState())
    val queueUiState: StateFlow<QueueUiState> = _queueUiState.asStateFlow()


    init {
        loadHistory()
        loadQueue()
    }

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
        }
    }

    fun loadQueue(intial: Boolean = true) {
        if(intial) {
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
        }
    }

    fun cancelQueueWorkflow(workflow: Queue.Workflow) {
        viewModelScope.launch {
            fluxAPI.cancelWorkflow(workflow.id)
            loadQueue(intial = false)
        }
    }
}

data class HistoryItem(
    val images: List<String>,
    val success: Boolean,
    val completed: Boolean,
    val prompt: String
)

data class Queue(
    val running: List<Workflow>,
    val pending: List<Workflow>,
){
    data class Workflow(
        val id: String,
        val prompt: String
    )
}