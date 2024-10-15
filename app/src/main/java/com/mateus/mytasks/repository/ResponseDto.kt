package com.mateus.mytasks.repository

data class ResponseDto<T>(
    val value: T? = null,
    val isError: Boolean = false
)
