package com.arman.dev.fellafeedsassignment.feature.login.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.arman.dev.fellafeedsassignment.core.common.Resource
import com.arman.dev.fellafeedsassignment.feature.login.domain.repository.AuthRepository
import com.arman.dev.fellafeedsassignment.feature.login.presentation.contract.LoginEffect
import com.arman.dev.fellafeedsassignment.feature.login.presentation.contract.LoginIntent
import com.arman.dev.fellafeedsassignment.feature.login.presentation.contract.LoginUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class LoginViewModel @Inject constructor(
    private val authRepository: AuthRepository
) : ViewModel() {
    private val _loginUiState = MutableStateFlow(LoginUiState())
    val loginUiState = _loginUiState.asStateFlow()

    private val _effect = Channel<LoginEffect>()
    val effect = _effect.receiveAsFlow()

    fun onIntent(intent: LoginIntent) {
        when (intent) {
            LoginIntent.ContinueClicked -> {
                sendOtp()
            }

            is LoginIntent.PhoneNumberChanged -> {
                val isValid = intent.number.length == 10
                _loginUiState.update {
                    it.copy(
                        phoneNumber = intent.number,
                        isContinueButtonEnabled = isValid
                    )
                }
            }
        }
    }

    private fun sendOtp(){
        viewModelScope.launch {
            _loginUiState.update {
                it.copy(
                    isLoading = true
                )
            }

            val result = authRepository.requestOtp(
                phone = loginUiState.value.phoneNumber
            )

            when(result){
                is Resource.Error -> {
                    //here we can simply send event and say there occurred some error
                    //in the snack bar.
                }
                is Resource.Success<*> -> {
                    // we can send event to the ui telling it to move to the verify otp
                    // screen.
                }
            }
        }
    }


    // in the same way all these function can be used and implemented
    // this viewmodel need to be shared with the both screen or we can have different
    //viewmodel to keep it clean.

    private fun verifyOtp(){

    }

    private fun logout(){

    }
}