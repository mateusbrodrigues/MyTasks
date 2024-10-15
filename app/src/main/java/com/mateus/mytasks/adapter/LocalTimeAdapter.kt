package com.mateus.mytasks.adapter

import com.google.gson.TypeAdapter
import com.google.gson.stream.JsonReader
import com.google.gson.stream.JsonWriter
import java.time.LocalTime

class LocalTimeAdapter : TypeAdapter<LocalTime>() {

    override fun write(jsonWriter: JsonWriter?, value: LocalTime?) {
        jsonWriter?.value(value?.toString())
    }

    override fun read(jsonReader: JsonReader?): LocalTime {
        return LocalTime.parse(jsonReader?.nextString())
    }
}