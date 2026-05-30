package com.arman.dev.fellafeedsassignment.feature.login.presentation.contract

sealed class LoginIntent {
    data class PhoneNumberChanged(val number : String) : LoginIntent()
    data object ContinueClicked : LoginIntent()
}
