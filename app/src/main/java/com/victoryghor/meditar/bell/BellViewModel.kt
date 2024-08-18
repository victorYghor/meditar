package com.victoryghor.meditar.bell

import android.media.MediaPlayer
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.victoryghor.meditar.Destinations
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import com.victoryghor.meditar.Destinations.HIT_BELL_SCREEN
import com.victoryghor.meditar.Destinations.RING_BELL_SCREEN
import com.victoryghor.meditar.Destinations.SELECT_TIMER_SCREEN
import com.victoryghor.meditar.Destinations.TIMER_SCREEN
import com.victoryghor.meditar.util.removeCurlyBrackets
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.update

data class BellUiState(
    val minutesOfPractice: Int? = null,
    val quantityOfHits: Int
)

class BellViewModel(handle: SavedStateHandle) : ViewModel() {
    private val _uiState: MutableStateFlow<BellUiState> by lazy {
        MutableStateFlow(BellUiState(
            minutesOfPractice = handle.get<String>("minutes")?.removeCurlyBrackets()?.toInt(),
            quantityOfHits = handle.get<String>("quantityOfHits")?.removeCurlyBrackets()?.toInt() ?: 3
        ))
    }
    val uiState by lazy {
        _uiState.asStateFlow()
    }

    fun startRingBell(bellPlayer: MediaPlayer? = null, goToNextScreen: () -> Unit) {
        viewModelScope.launch {
            bellPlayer?.start()
            delay(4_000L)
            withContext(Dispatchers.Main) {
                goToNextScreen()
            }
        }
    }
    fun startHitBell(bellPlayer: MediaPlayer, goToNextScreen: () -> Unit) {
        viewModelScope.launch {
            bellPlayer.start()
            delay(2_000L)
            withContext(Dispatchers.Main) {
                goToNextScreen()
            }
        }
    }
}