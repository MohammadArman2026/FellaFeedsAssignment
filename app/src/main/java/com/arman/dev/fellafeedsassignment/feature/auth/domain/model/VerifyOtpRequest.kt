package com.arman.dev.fellafeedsassignment.feature.auth.domain.model

import kotlinx.serialization.SerialName

data class VerifyOtpRequest (
    @SerialName("phone")
    val phoneNumber : String ,
    @SerialName("otp")
    val otp : String
)