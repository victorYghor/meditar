package com.victoryghor.meditar.bell

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class BellViewModel(): ViewModel() {
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