package com.arman.dev.fellafeedsassignment.feature.auth.presentation.contract

sealed class AuthBoardingIntent {
    data object ClickedLetsStart : AuthBoardingIntent()
}