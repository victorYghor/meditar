package com.victoryghor.meditar.model
import kotlinx.serialization.Serializable

@Serializable
data class TimeSettings(
    val previousMinutes: Int = 0
)
