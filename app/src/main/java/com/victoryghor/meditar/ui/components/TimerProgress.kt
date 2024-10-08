package com.victoryghor.meditar.ui.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.victoryghor.meditar.ui.theme.blackBackground
import com.victoryghor.meditar.ui.theme.redDark

/**
 * @param angle a porcentagem do timer em graus, 360f para uma volta completa.
 */
@Composable
fun Timer(angle: Float) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.background(blackBackground)
    ) {
        Canvas(modifier = Modifier.size(330.dp)) {
            drawArc(redDark, -90f, angle, true)
        }
        Canvas(modifier = Modifier.size(300.dp)) {
            drawArc(blackBackground, -90f, angle, true)
        }
        Canvas(modifier = Modifier.size(300.dp)) {
            drawArc(blackBackground, -90f, angle, true, style = Stroke(10f))
        }
    }
}

@Preview
@Composable
fun TimerPreview() {
    Timer(angle = 270f)
}