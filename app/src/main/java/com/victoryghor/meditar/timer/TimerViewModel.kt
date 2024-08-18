package com.victoryghor.meditar.timer

import androidx.lifecycle.ViewModel
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.victoryghor.meditar.util.removeCurlyBrackets
import kotlinx.coroutines.CoroutineStart
import kotlinx.coroutines.cancel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class TimerUIState(
    val currentMinutes: Long = 0,
    val currentSeconds: Long = 0,
    val currentAngle: Float = 0f,
)
class TimerViewModel(handle: SavedStateHandle): ViewModel() {
    val selectTimerInMinutes by lazy {
        handle.get<String>("minutes")?.removeCurlyBrackets()?.toInt() ?: 5
    }

    private val selectTimerInSeconds = selectTimerInMinutes * 60L

    private val _uiState = MutableStateFlow(
        TimerUIState()
    )
    val uiState: StateFlow<TimerUIState> = _uiState.asStateFlow()

    private val _secondsCount = MutableStateFlow(0L)


    private val timerJob = viewModelScope.launch(start = CoroutineStart.LAZY) {
        while(true) {
            delay(1000L)
            _secondsCount.value++
            _uiState.update {
                it.copy(
                    currentMinutes = _secondsCount.value / 60,
                    currentSeconds = _secondsCount.value % 60,
                    currentAngle = (_secondsCount.value.toFloat() / selectTimerInSeconds.toFloat() * 360f)
                )
            }
            if(_secondsCount.value >= selectTimerInSeconds)
                this.cancel()
        }
    }

    fun startTimer(goToBellRingScreen: () -> Unit) {
        viewModelScope.launch {
            timerJob.join()
            goToBellRingScreen()
        }
    }
}