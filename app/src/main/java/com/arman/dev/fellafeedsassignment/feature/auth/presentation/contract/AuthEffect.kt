package com.arman.dev.fellafeedsassignment.feature.auth.presentation.contract

sealed class AuthEffect{
    data object NavigateToOtpScreen : AuthEffect()
    data class ShowToastMessage(val message : String) : AuthEffect()
}