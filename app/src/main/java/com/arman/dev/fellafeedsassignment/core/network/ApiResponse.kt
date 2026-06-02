package com.arman.dev.fellafeedsassignment.core.network

import kotlinx.serialization.SerialName

data class ApiResponse <T>(
    @SerialName("success")
    val success: Boolean,

    @SerialName("status_code")
    val statusCode: Int,

    @SerialName("message")
    val message: String,

    @SerialName("data")
    val data: T?
)