package com.arman.dev.fellafeedsassignment.feature.auth.presentation.contract

sealed class AuthOtpIntent{
    data class OtpChanged (val otp : String) : AuthOtpIntent()
    data object ContinueClicked : AuthOtpIntent()
}