package com.victoryghor.meditar.bell

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.victoryghor.meditar.R

@Composable
fun BellImage(@DrawableRes image: Int) {
    Image(
        painterResource(id = image),
        contentDescription = stringResource(R.string.hitting_a_tibetan_bell)
    )
}