package com.victoryghor.meditar.bell

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

data class BellUiState(
    val minutesOfPractice: Int? = null,
    val quantityOfHits: Int
)
class BellViewModel(): ViewModel() {
    private val _uiState = MutableStateFlow(BellUiState(15, 3))
    val uiState = _uiState.asStateFlow()

    fun startRingBell(goToSelectTimer: () -> Unit) {
        viewModelScope.launch {
            // tocar o sino
            delay(5_000L)
            withContext(Dispatchers.Main) {
                goToSelectTimer()
            }
        }
    }
}