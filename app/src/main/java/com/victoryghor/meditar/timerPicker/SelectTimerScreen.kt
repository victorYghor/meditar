package com.victoryghor.meditar.timerPicker

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.victoryghor.meditar.R
import com.victoryghor.meditar.ui.components.ConfirmButton
import com.victoryghor.meditar.ui.components.SelectTimer
import com.victoryghor.meditar.ui.theme.blackBackground
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope.coroutineContext
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import kotlin.coroutines.coroutineContext

@Preview
@Composable
fun SelectTimerScreen(viewModel: TimerPickerViewModel = viewModel()) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxSize()
            .background(blackBackground)
    ) {
        LaunchedEffect(uiState.selectTime) {
            launch {
                uiState.listState.scrollToItem(uiState.selectTime - 1)
            }
        }
        SelectTimer(uiState.listState, Modifier)
        Spacer(modifier = Modifier.height(200.dp))
        ConfirmButton(
            onClick = {
                CoroutineScope(Dispatchers.Default).launch {
                    viewModel.selectMinutes(uiState.listState.firstVisibleItemIndex + 1).join()
                }
            },
            text = R.string.start
        )
    }
}