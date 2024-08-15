package com.victoryghor.meditar.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
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
import com.victoryghor.meditar.ui.theme.white0
import com.victoryghor.meditar.ui.theme.white200


@Composable
fun SelectTimer(state: LazyListState, modifier: Modifier) {
    val minutes = List<Int>(62) { it }
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        Text(stringResource(R.string.minutes), fontSize = 40.sp, color = black100)
        Spacer(modifier = Modifier.height(16.dp))
        LazyColumn(
            state = state,
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = modifier.size(width = 128.dp, height = 256.dp)
        ) {
            val centerIndex = state.firstVisibleItemIndex + 1
            minutes.forEach { minute ->
                item {
                    Text(
                        minute.toString().takeIf { it != "0"} ?: " ",
                        fontSize = 64.sp,
                        color = if (centerIndex == minute) white0 else black100
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun SelectMinutesPreview() {
    val listState = rememberLazyListState()
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxSize().background(blackBackground)
    ) {
        SelectTimer(state = listState, Modifier)
    }
}