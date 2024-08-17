package com.victoryghor.meditar.ui.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.material3.ButtonDefaults
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.victoryghor.meditar.R
import com.victoryghor.meditar.ui.theme.black400
import com.victoryghor.meditar.ui.theme.white200
import com.victoryghor.meditar.ui.theme.white200Transparent
import com.victoryghor.meditar.ui.theme.white300

@Composable
fun CancelButton(onClick: () -> Unit, text: Int) {
    Button(
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(containerColor = white300),
        modifier = Modifier.size(width = 180.dp, height = 60.dp)
    ) {
        Text(stringResource(text), fontSize = 28.sp, color = black400)
    }
}

@Preview
@Composable
private fun CancelButtonPreview() {
    CancelButton(onClick = {}, text = R.string.stop)
}