package com.eltonkola.comfyflux.app.usecase

import android.util.Log
import com.eltonkola.comfyflux.app.model.HistoryUiState
import com.eltonkola.comfyflux.app.netwrok.FluxAPI
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class HistoryUseCase(
    private val fluxAPI: FluxAPI
) {
    private val _historyUiState = MutableStateFlow(HistoryUiState())
    val historyUiState: StateFlow<HistoryUiState> = _historyUiState.asStateFlow()

    suspend fun loadHistory(silentUpdate: Boolean = false, onDone: () -> Unit) {
        if (silentUpdate) {
            _historyUiState.update { it.copy(silentLoading = true) }
        } else {
            _historyUiState.update { it.copy(loading = true) }
        }
        try {
            val history = fluxAPI.fetchHistory()
            Log.d("HistoryQueueViewModel", "history: ${history.size}")
            _historyUiState.update {
                it.copy(
                    loading = false,
                    history = history.reversed(),
                    error = false,
                    silentLoading = false
                )
            }
        } catch (e: Exception) {
            _historyUiState.update { it.copy(loading = false, error = true, silentLoading = false) }
        }
        onDone()
    }

}