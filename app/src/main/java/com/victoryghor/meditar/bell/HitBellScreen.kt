package com.victoryghor.meditar.bell

import androidx.compose.foundation.background
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.victoryghor.meditar.R
import com.victoryghor.meditar.ui.theme.black100
import com.victoryghor.meditar.ui.theme.blackBackground
import com.victoryghor.meditar.ui.components.TextMinutesOfPractice
import com.victoryghor.meditar.ui.theme.white0

@Composable
fun HitBellScreen(
    uiState: BellUiState
) {
    Column(
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .background(color = blackBackground)
            .padding(42.dp)
            .scrollable(rememberScrollState(), orientation = Orientation.Vertical)
    ) {
        uiState.minutesOfPractice?.let {
            TextMinutesOfPractice(it)
        }
        Spacer(modifier = Modifier.size(220.dp))
        BellImage(R.drawable.hitbell)
        Spacer(modifier = Modifier.size(128.dp))
        if (uiState.quantityOfHits != 0)
            Text(
                stringResource(R.string.the_bell_will_be_hitting_x_times).format(uiState.quantityOfHits),
                fontSize = 24.sp,
                color = black100
            )
    }
}



//@Preview
//@Composable
//fun BellScreenPreview() {
//    HitBellScreen(15)
//}