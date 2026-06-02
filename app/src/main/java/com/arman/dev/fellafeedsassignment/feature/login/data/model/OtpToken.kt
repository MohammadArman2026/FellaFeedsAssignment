package com.arman.dev.fellafeedsassignment.feature.login.data.model

import kotlinx.serialization.SerialName

data class OtpToken(
    @SerialName("token")
    val token : String
)