package com.arman.dev.fellafeedsassignment.feature.auth.domain.model

import kotlinx.serialization.SerialName

data class OtpRequest(
    @SerialName("token")
    val phoneNumber : String
)