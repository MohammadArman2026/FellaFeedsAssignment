package com.arman.dev.fellafeedsassignment.feature.login.data.model

import kotlinx.serialization.SerialName

data class OtpRequest(
    @SerialName("token")
    val phoneNumber : String
)