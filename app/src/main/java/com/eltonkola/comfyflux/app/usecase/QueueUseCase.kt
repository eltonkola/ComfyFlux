package com.eltonkola.comfyflux.app.usecase

import android.util.Log
import com.eltonkola.comfyflux.data.model.QueueUiState
import com.eltonkola.comfyflux.data.netwrok.FluxAPI
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class QueueUseCase(
    private val fluxAPI: FluxAPI
) {
    private val _queueUiState = MutableStateFlow(QueueUiState())
    val queueUiState: StateFlow<QueueUiState> = _queueUiState.asStateFlow()

    suspend fun loadQueue(showLoading: Boolean = true, onDone: () -> Unit) {
        if (showLoading) {
            _queueUiState.update { it.copy(loading = true) }
        }
        try {
            val queue = fluxAPI.fetchQueue()
            Log.d("HistoryQueueViewModel", "queue pending: ${queue.pending.size}")
            _queueUiState.update { it.copy(loading = false, queue = queue, error = false) }
        } catch (e: Exception) {
            _queueUiState.update { it.copy(loading = false, error = true) }
        }
        onDone()
    }

}