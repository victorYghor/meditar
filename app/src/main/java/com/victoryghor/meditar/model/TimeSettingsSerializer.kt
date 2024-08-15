package com.victoryghor.meditar.model

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.core.Serializer
import androidx.datastore.dataStore
import kotlinx.serialization.json.Json
import java.io.InputStream
import java.io.OutputStream

object TimeSettingsSerializer: Serializer<TimeSettings> {
    override val defaultValue: TimeSettings
        get() = TimeSettings()

    override suspend fun readFrom(input: InputStream): TimeSettings {
        return try {
            Json.decodeFromString(
                deserializer = TimeSettings.serializer(),
                string = input.readBytes().decodeToString()
            )
        } catch (e: Exception) {
            e.printStackTrace()
            defaultValue
        }
    }

    override suspend fun writeTo(t: TimeSettings, output: OutputStream) {
        try {
            output.write(
                Json.encodeToString(
                    serializer = TimeSettings.serializer(),
                    value = t
                ).toByteArray()
            )
        } catch (e: Exception) {
          e.printStackTrace()
        }
    }
}

val Context.timeSettingsDataStore: DataStore<TimeSettings> by dataStore(
    serializer = TimeSettingsSerializer,
    fileName = "time_settings.json"
)