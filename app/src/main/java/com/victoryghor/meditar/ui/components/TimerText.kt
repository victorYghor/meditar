package com.victoryghor.meditar.ui.components

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.victoryghor.meditar.ui.theme.white0
import com.victoryghor.meditar.ui.theme.white100
import com.victoryghor.meditar.ui.theme.white200


@Composable
fun TimerText(minutes: String, seconds:String, modifier: Modifier = Modifier) {
    Text(
        text = formatNumberWithSpace(minutes) + " : " + formatNumberWithSpace(seconds),
        fontSize = 64.sp,
        color = white100,
        modifier = modifier
    )
}
fun formatNumberWithSpace(time: String) = if(time.length == 2) time else time + " "

@Preview
@Composable
fun TimerTextPreview() {
    TimerText(minutes = "3", seconds = "30")
}