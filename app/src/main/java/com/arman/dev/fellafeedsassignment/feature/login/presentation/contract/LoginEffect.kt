package com.arman.dev.fellafeedsassignment.feature.login.presentation.contract

sealed class LoginEffect {
    data object NavigateToOtp : LoginEffect()
    data class ShowToast(val message : String) : LoginEffect()
}