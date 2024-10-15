package com.mateus.mytasks.entity

import java.io.Serializable
import java.time.LocalDate
import java.time.LocalTime

data class Task(
    var id: Long? = null,
    var title: String,
    var description: String? = null,
    var date: LocalDate? = null,
    var time: LocalTime? = null,
    var completed: Boolean = false
) : Serializable
