package com.eltonkola.comfyflux.app.usecase

import android.annotation.SuppressLint
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class TimerUseCase {

    private val _timer = MutableStateFlow("00:00")
    val timer: StateFlow<String> = _timer

    private var running = false
    private var elapsedTime = 0L

    suspend fun startTimer() {
        if (running) return
        running = true
        while (running) {
            delay(1000) // Update every second
            elapsedTime += 1000 // Increment by 1 second
            _timer.value = formatTime(elapsedTime)
        }
    }

    fun stopTimer() {
        running = false
    }

    fun resetTimer() {
        running = false
        elapsedTime = 0L
        _timer.value = formatTime(elapsedTime)
    }

    @SuppressLint("DefaultLocale")
    private fun formatTime(time: Long): String {
        val minutes = (time / 1000) / 60
        val seconds = (time / 1000) % 60
        return String.format("%02d:%02d", minutes, seconds)
    }

}