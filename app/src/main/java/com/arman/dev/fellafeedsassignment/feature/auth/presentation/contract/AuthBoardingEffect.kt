package com.arman.dev.fellafeedsassignment.feature.auth.presentation.contract

sealed class AuthBoardingEffect{
    data object NavigateFromAuthBoarding : AuthBoardingEffect()
}