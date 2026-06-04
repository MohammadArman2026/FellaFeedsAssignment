package com.arman.dev.fellafeedsassignment.feature.auth.presentation.contract

import androidx.annotation.BoolRes

data class AuthUiState(
    val isLoading : Boolean = false ,
    val error : String ? = null ,
    val phoneNumber : String = "" ,
    val isContinueEnabled :Boolean = false
)