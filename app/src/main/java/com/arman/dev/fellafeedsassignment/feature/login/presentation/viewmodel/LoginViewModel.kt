package com.arman.dev.fellafeedsassignment.feature.login.presentation.viewmodel

import androidx.compose.runtime.MutableState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.arman.dev.fellafeedsassignment.feature.login.presentation.contract.LoginEffect
import com.arman.dev.fellafeedsassignment.feature.login.presentation.contract.LoginIntent
import com.arman.dev.fellafeedsassignment.feature.login.presentation.contract.LoginUiState
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

class LoginViewModel @Inject constructor() : ViewModel() {
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

            delay(3000)

            _loginUiState.update {
                it.copy(
                    isLoading = false
                )
            }

            _effect.send(LoginEffect.NavigateToOtp)
        }
    }
}