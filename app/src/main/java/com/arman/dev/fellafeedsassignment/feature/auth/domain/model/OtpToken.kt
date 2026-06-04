package com.arman.dev.fellafeedsassignment.feature.auth.domain.model

import kotlinx.serialization.SerialName

data class OtpToken(
    @SerialName("token")
    val token : String
)