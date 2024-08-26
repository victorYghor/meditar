package com.victoryghor.meditar.ui.components

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.victoryghor.meditar.R
import com.victoryghor.meditar.ui.theme.white0
import com.victoryghor.meditar.ui.theme.white200
import com.victoryghor.meditar.ui.theme.white200Transparent
import com.victoryghor.meditar.ui.theme.white300

@Composable
fun TextMinutesOfPractice(minutes: Int) {
    Text(
        stringResource(R.string.minutes_of_practice).format(minutes),
        fontSize = 32.sp,
        color = white300
    )
}

@Preview
@Composable
fun TextMinutesOfPracticePreview() {
    TextMinutesOfPractice(minutes = 3)
}