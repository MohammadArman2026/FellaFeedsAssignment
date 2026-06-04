package com.arman.dev.fellafeedsassignment.feature.auth.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.arman.dev.fellafeedsassignment.feature.auth.presentation.contract.AuthOtpEffect
import com.arman.dev.fellafeedsassignment.feature.auth.presentation.contract.AuthOtpIntent
import com.arman.dev.fellafeedsassignment.feature.auth.presentation.contract.AuthOtpUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthOtpViewModel @Inject constructor(): ViewModel() {

    private val _uiState = MutableStateFlow(AuthOtpUiState())
    val uiState = _uiState.asStateFlow()
    private val _effect = Channel<AuthOtpEffect>()
    val effect = _effect.receiveAsFlow()

    fun onIntent(intent: AuthOtpIntent){
        when(intent){
            AuthOtpIntent.ContinueClicked -> onContinueClicked()
            is AuthOtpIntent.OtpChanged -> setOtp(intent.otp)
        }
    }

   private fun setOtp(otp: String){
       val isContinueEnabled = otp.length == 6
       _uiState.update {
           it.copy(
               otp = otp ,
               isSubmitEnabled = isContinueEnabled
           )
       }
    }

    fun onContinueClicked (){
        viewModelScope.launch {
            _effect.send(AuthOtpEffect.NavigateToOnBoardingScreen)
        }
    }
}