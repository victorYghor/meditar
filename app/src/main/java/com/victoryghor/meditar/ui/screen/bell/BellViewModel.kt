package com.victoryghor.meditar.ui.screen.bell

import android.media.MediaPlayer
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import com.victoryghor.meditar.util.removeCurlyBrackets

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
            delay(5_000L)
            withContext(Dispatchers.Main) {
                goToNextScreen()
            }
        }
    }
    fun startHitBell(bellPlayer: MediaPlayer, goToNextScreen: () -> Unit) {
        viewModelScope.launch {
            bellPlayer.start()
            delay(3_000L)
            withContext(Dispatchers.Main) {
                goToNextScreen()
            }
        }
    }
}