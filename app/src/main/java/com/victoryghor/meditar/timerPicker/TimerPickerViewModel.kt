package com.victoryghor.meditar.timerPicker

import android.app.Application
import androidx.compose.foundation.lazy.LazyListState
import androidx.datastore.core.DataStore
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.victoryghor.meditar.model.TimeSettings
import com.victoryghor.meditar.model.timeSettingsDataStore
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

data class TimerPickerState(
    val listState: LazyListState = LazyListState(),
    val selectTime: Int = 2
)

class TimerPickerViewModel(application: Application) : AndroidViewModel(application) {
    private val _uiState = MutableStateFlow(TimerPickerState())
    val uiState: StateFlow<TimerPickerState> = _uiState.asStateFlow()

    private val timeSettingsDataStore: DataStore<TimeSettings> =
        getApplication<Application>().applicationContext.timeSettingsDataStore

    init {
        CoroutineScope(Dispatchers.Default).launch {
            goToSavedItem()
        }
    }

    /**
     * Get the value stored in DataStore and put in the  uiState
     */
    private suspend fun goToSavedItem() = viewModelScope.launch(Dispatchers.IO) {
        timeSettingsDataStore.data.collectLatest { timeSettings ->
            _uiState.update {
                it.copy(selectTime = timeSettings.previousMinutes)
            }
        }
    }


    private fun storeMinutes(minutes: Int) = viewModelScope.launch(Dispatchers.IO) {
        timeSettingsDataStore.updateData {
            it.copy(previousMinutes = minutes)
        }
    }

    fun selectMinutes(minutes: Int) = viewModelScope.launch(Dispatchers.Default) {
        storeMinutes(minutes).join()
    }
}