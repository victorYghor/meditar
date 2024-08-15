package com.victoryghor.meditar.timer

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.victoryghor.meditar.R
import com.victoryghor.meditar.ui.components.CancelButton
import com.victoryghor.meditar.ui.components.ConfirmButton
import com.victoryghor.meditar.ui.components.Timer
import com.victoryghor.meditar.ui.components.TimerText
import com.victoryghor.meditar.ui.theme.blackBackground

@Preview
@Composable
fun TimerScreen() {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .background(blackBackground)
    ) {
        Box(
            contentAlignment = Alignment.Center
        ) {
            Timer(270f)
            TimerText(minutes = "10", seconds = "30")
        }
        Spacer(modifier = Modifier.size(128.dp))
        CancelButton(
            onClick = {},
            R.string.stop,
        )
    }
}