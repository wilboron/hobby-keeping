package com.gmail.williammingardi.hobbykeeping.exception

import java.time.LocalDateTime

data class ApplicationErrorResponse(
    val timestamp: LocalDateTime = LocalDateTime.now(),
    val status: Int,
    val error: String,
    val message: String?,
    val path: String,
    val method: String
)

data class ApplicationErrorDictResponse(
    val timestamp: LocalDateTime = LocalDateTime.now(),
    val status: Int,
    val error: String,
    val message: Map<String, Any>,
    val path: String,
    val method: String
)
