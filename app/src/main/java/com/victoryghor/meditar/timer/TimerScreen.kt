package com.victoryghor.meditar.timer

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.victoryghor.meditar.R
import com.victoryghor.meditar.ui.components.CancelButton
import com.victoryghor.meditar.ui.components.ConfirmButton
import com.victoryghor.meditar.ui.components.Timer
import com.victoryghor.meditar.ui.components.TimerText
import com.victoryghor.meditar.ui.theme.blackBackground

@Composable
fun TimerScreen(
    goToBellRingScreen: () -> Unit,
    timerViewModel: TimerViewModel
) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .background(blackBackground)
            .verticalScroll(rememberScrollState())
    ) {
        val uiState by timerViewModel.uiState.collectAsStateWithLifecycle()
        LaunchedEffect(Unit) {
            timerViewModel.startTimer(goToBellRingScreen)
        }
        Box(
            contentAlignment = Alignment.Center
        ) {
            Timer(uiState.currentAngle)
            TimerText(
                minutes = uiState.currentMinutes.toString(),
                seconds = uiState.currentSeconds.toString()
            )
        }
        Spacer(modifier = Modifier.size(128.dp))
        CancelButton(
            onClick = {},
            R.string.stop,
        )
    }
}

@Preview
@Composable
private fun TimerScreenPreview() {
    TimerScreen({}, TimerViewModel(15))
}