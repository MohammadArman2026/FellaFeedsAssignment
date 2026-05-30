package com.arman.dev.fellafeedsassignment.feature.login.presentation.contract

data class LoginUiState (
    val phoneNumber: String = "" ,
    val isLoading : Boolean  = false ,
    val isContinueButtonEnabled : Boolean = false
)