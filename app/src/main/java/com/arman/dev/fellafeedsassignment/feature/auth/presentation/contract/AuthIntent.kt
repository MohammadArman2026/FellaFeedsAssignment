package com.arman.dev.fellafeedsassignment.feature.auth.presentation.contract

sealed class AuthIntent {
    data class PhoneNumberChanged(val number : String): AuthIntent()
    data object ContinueClicked : AuthIntent()
}