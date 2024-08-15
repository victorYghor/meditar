package com.victoryghor.meditar.ui.components

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.victoryghor.meditar.R
import com.victoryghor.meditar.ui.theme.blackBackground

@Composable
fun HitBellImage(@DrawableRes image: Int) {
    Image(
        painterResource(id = image),
        contentDescription = stringResource(R.string.hitting_a_tibetan_bell)
    )
}