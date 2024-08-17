package com.victoryghor.meditar.bell

import androidx.compose.foundation.background
import androidx.compose.runtime.*
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.victoryghor.meditar.R
import com.victoryghor.meditar.ui.theme.blackBackground
import com.victoryghor.meditar.ui.theme.white0


@Composable
fun RingBellScreen(
    uiState: BellUiState,
    gotToSelectTimer: () -> Unit
) {
    val viewModel: BellViewModel = viewModel()
    Column(
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .background(color = blackBackground)
            .padding(42.dp)
            .scrollable(rememberScrollState(), orientation = Orientation.Vertical)
    ) {
        LaunchedEffect(Unit) {
            viewModel.startRingBell {  gotToSelectTimer() }
        }
        uiState.minutesOfPractice?.let {
            Text(
                stringResource(R.string.minutes_of_practice).format(it),
                fontSize = 32.sp,
                color = white0
            )
        }
        Spacer(modifier = Modifier.size(220.dp))
        BellImage(R.drawable.bell_ring)
    }
}