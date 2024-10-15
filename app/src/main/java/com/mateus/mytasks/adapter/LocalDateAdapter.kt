package com.mateus.mytasks.adapter

import com.google.gson.TypeAdapter
import com.google.gson.stream.JsonReader
import com.google.gson.stream.JsonWriter
import java.time.LocalDate

class LocalDateAdapter : TypeAdapter<LocalDate>() {

    override fun write(jsonWriter: JsonWriter?, value: LocalDate?) {
        jsonWriter?.value(value?.toString())
    }

    override fun read(jsonReader: JsonReader?): LocalDate {
        return LocalDate.parse(jsonReader?.nextString())
    }
}