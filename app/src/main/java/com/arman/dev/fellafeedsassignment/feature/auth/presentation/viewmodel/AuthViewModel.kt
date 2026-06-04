package com.arman.dev.fellafeedsassignment.feature.auth.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.arman.dev.fellafeedsassignment.feature.auth.presentation.contract.AuthEffect
import com.arman.dev.fellafeedsassignment.feature.auth.presentation.contract.AuthIntent
import com.arman.dev.fellafeedsassignment.feature.auth.presentation.contract.AuthUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(): ViewModel(){

    private val _authUiState = MutableStateFlow(AuthUiState())
    val authUiState = _authUiState.asStateFlow()

    val effect = Channel<AuthEffect>().receiveAsFlow()


    fun onIntent(intent: AuthIntent){
        when(intent){
            AuthIntent.ContinueClicked -> requestOtp()
            is AuthIntent.PhoneNumberChanged -> setPhoneNumber(intent.number)
        }
    }

    private fun requestOtp(){

        viewModelScope.launch {
            _authUiState.update {
                it.copy(
                    isLoading = true
                )
            }



        }

    }

    private fun setPhoneNumber(phoneNumber : String){
        val isContinueEnabled = phoneNumber.length == 10
        _authUiState.update {
            it.copy(
                phoneNumber = phoneNumber,
                isContinueEnabled = isContinueEnabled
            )
        }
    }
}