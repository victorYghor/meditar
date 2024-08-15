package com.victoryghor.meditar.bell

import com.victoryghor.meditar.R

sealed class BellState(imageRes: Int) {
    data object Ring : BellState(imageRes = R.drawable.bell_making_sound)
    data class Hitting(val numberOfHits: Int) : BellState(imageRes = R.drawable.hitbell)
}
