package com.victoryghor.meditar.ui.components

import android.widget.Toast
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.victoryghor.meditar.R
import com.victoryghor.meditar.ui.theme.black400
import com.victoryghor.meditar.ui.theme.white200
import com.victoryghor.meditar.ui.theme.white200Transparent


@Composable
fun ConfirmButton(
    onClick: () -> Unit,
    text: Int,
    disabled: Boolean = false,
    @StringRes reasonOfDisabled: Int = R.string.you_cannot_click_in_the_button,
    modifier: Modifier = Modifier
) {
    val alertChooseATime = Toast.makeText(
        LocalContext.current,
        stringResource(reasonOfDisabled), Toast.LENGTH_LONG
    )
    val handleDisableClick = if (disabled) {
        { alertChooseATime.show() }
    } else {
        onClick
    }
    Button(
        onClick = handleDisableClick,
        colors = if(disabled)
            ButtonDefaults.buttonColors(containerColor = white200Transparent)
        else
            ButtonDefaults.buttonColors(containerColor = white200),
        modifier = modifier.size(height = 80.dp, width = 256.dp)
    ) {
        Text(text = stringResource(text), color = black400, fontSize = 32.sp)
    }
}

@Preview
@Composable
fun ConfirmButtonPreview() {
    ConfirmButton(onClick = { }, text = R.string.test, disabled = true)
}