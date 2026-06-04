package com.arman.dev.fellafeedsassignment.feature.auth.presentation.contract

data class AuthOtpUiState(
    val otp : String = "" ,
    val isLoading : Boolean = false ,
    val error : String ? = null ,
    val isSubmitEnabled : Boolean = false,
    val phoneNumber : String = ""
)