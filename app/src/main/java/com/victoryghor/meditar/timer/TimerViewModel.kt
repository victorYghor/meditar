package com.victoryghor.meditar.timer

import android.os.Bundle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.AbstractSavedStateViewModelFactory
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import androidx.savedstate.SavedStateRegistryOwner
import kotlinx.coroutines.CoroutineStart
import kotlinx.coroutines.cancel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class uiStateTimer(
    val currentMinutes: Long = 0,
    val currentSeconds: Long = 0,
    val currentAngle: Float = 0f,
)
class TimerViewModel(selectTimerInMinutes: Int, handle: SavedStateHandle): ViewModel() {
    private val selectTimerInSeconds = selectTimerInMinutes * 60L

    private val _uiState = MutableStateFlow(
        uiStateTimer()
    )
    val uiState: StateFlow<uiStateTimer> = _uiState.asStateFlow()

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

    companion object {
        fun providedFactory(
            selectTimerInMinutes: Int,
            owner: SavedStateRegistryOwner,
            defaultArgs: Bundle? = null
        ) = object : AbstractSavedStateViewModelFactory(owner, defaultArgs) {
            override fun <T : ViewModel> create(
                key: String,
                modelClass: Class<T>,
                handle: SavedStateHandle
            ): T {
                return TimerViewModel(selectTimerInMinutes, handle) as T
            }

        }
    }
}