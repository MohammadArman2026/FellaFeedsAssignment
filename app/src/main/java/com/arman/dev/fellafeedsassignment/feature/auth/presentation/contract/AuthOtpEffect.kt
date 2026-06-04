package com.arman.dev.fellafeedsassignment.feature.auth.presentation.contract

sealed class AuthOtpEffect {
 data object NavigateToOnBoardingScreen : AuthOtpEffect()
}