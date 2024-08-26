package com.victoryghor.meditar.model
import androidx.annotation.Keep
import kotlinx.serialization.Serializable

@Keep
@Serializable
data class TimeSettings(
    val previousMinutes: Int = 2
)
