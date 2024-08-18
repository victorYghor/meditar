package com.victoryghor.meditar.bell

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
import kotlinx.coroutines.flow.update

data class BellUiState(
    val minutesOfPractice: Int? = null,
    val quantityOfHits: Int
)

class BellViewModel(handle: SavedStateHandle) : ViewModel() {
    private lateinit var _uiState: MutableStateFlow<BellUiState>
    val uiState by lazy {
        _uiState.asStateFlow()
    }
    val minutesOfPractice by lazy {
        handle.get<String>("minutes")?.removeCurlyBrackets()?.toInt()
    }

    val quantityOfHits by lazy {
        handle.get<String>("quantityOfHits")?.removeCurlyBrackets()?.toInt() ?: 3
    }
    init {
        _uiState = MutableStateFlow(
            BellUiState(
                minutesOfPractice = minutesOfPractice,
                quantityOfHits = quantityOfHits
            )
        )
    }
    fun startRingBell(goToNextScreen: () -> Unit) {
        viewModelScope.launch {
            // tocar o sino
            delay(4_000L)
            withContext(Dispatchers.Main) {
                goToNextScreen()
            }
        }
    }

    fun startHitBell(goToNextScreen: () -> Unit) {
        viewModelScope.launch {
            delay(1_200L)
            withContext(Dispatchers.Main) {
                goToNextScreen()
            }
        }
    }
}