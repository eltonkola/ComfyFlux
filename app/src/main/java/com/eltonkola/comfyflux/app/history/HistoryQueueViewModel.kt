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

class HistoryQueueViewModel(
    private val fluxAPI: FluxAPI = FluxAPI()
    ) : ViewModel() {

    private val _historyUiState = MutableStateFlow(HistoryUiState())
    val historyUiState: StateFlow<HistoryUiState> = _historyUiState.asStateFlow()


    init {
        loadHistory()
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
}

data class HistoryItem(
    val images: List<String>,
    val success: Boolean,
    val completed: Boolean,
    val prompt: String
)
