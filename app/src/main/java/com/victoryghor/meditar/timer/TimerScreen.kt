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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.victoryghor.meditar.R
import com.victoryghor.meditar.ui.components.CancelButton
import com.victoryghor.meditar.ui.components.TextMinutesOfPractice
import com.victoryghor.meditar.ui.components.Timer
import com.victoryghor.meditar.ui.components.TimerText
import com.victoryghor.meditar.ui.theme.blackBackground

@Composable
fun TimerScreen(
    goToBellRingScreen: () -> Unit,
    startTimer: (() -> Unit) -> Unit,
    uiState: TimerUIState,
    selectMinutes: Int,
    onStop: () -> Unit
) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .background(blackBackground)
            .verticalScroll(rememberScrollState())
    ) {
        LaunchedEffect(Unit) {
            startTimer(goToBellRingScreen)
        }
        TextMinutesOfPractice(minutes = selectMinutes)
        Spacer(modifier = Modifier.size(64.dp))
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
            onClick = { onStop() },
            R.string.stop,
        )
    }
}

//@Preview
//@Composable
//private fun TimerScreenPreview() {
//    TimerScreen({}, {}, uiState = TimerUIState(5, 30, 180f), 11)
//}