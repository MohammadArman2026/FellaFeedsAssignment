package com.arman.dev.fellafeedsassignment.feature.login.data.model

import kotlinx.serialization.SerialName

data class VerifyOtpRequest (
    @SerialName("phone")
    val phoneNumber : String ,
    @SerialName("otp")
    val otp : String
)