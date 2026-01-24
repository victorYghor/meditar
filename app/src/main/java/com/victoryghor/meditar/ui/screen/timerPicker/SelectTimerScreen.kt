package com.victoryghor.meditar.ui.screen.timerPicker

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import com.victoryghor.meditar.R
import com.victoryghor.meditar.ui.components.AdaptiveLayout
import com.victoryghor.meditar.ui.components.ConfirmButton
import com.victoryghor.meditar.ui.components.SelectTimer
import com.victoryghor.meditar.ui.theme.blackBackground
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

@Composable
fun SelectTimerScreen(
    goToRingBellScreen: (minutes: Int) -> Unit,
    selectMinutes: (minutes: Int) -> Job,
    uiState: TimerPickerState
) {
    val configuration = LocalConfiguration.current
    val screenHeight = configuration.screenHeightDp.dp
    val screenWidth = configuration.screenWidthDp.dp
    println("Screen Height: $screenHeight, Screen Width: $screenWidth")
    AdaptiveLayout(modifier = Modifier.fillMaxSize().background(blackBackground))
    {
         LaunchedEffect(uiState.selectTime) {
             launch {
                 uiState.listState.scrollToItem(uiState.selectTime - 1)
             }
         }
         SelectTimer(uiState.listState, Modifier.padding(top = if(screenHeight > 720.dp) 64.dp else 32.dp))
             ConfirmButton(
                 onClick = {
                     CoroutineScope(Dispatchers.Default).launch {
                         selectMinutes(uiState.listState.firstVisibleItemIndex + 1).join()
                     }
                     goToRingBellScreen(uiState.listState.firstVisibleItemIndex + 1)
                 },
                 text = R.string.start,
                 modifier = Modifier.padding(bottom = if(screenHeight> 720.dp) 64.dp else 32.dp)
             )

     }
}