package com.arman.dev.fellafeedsassignment.feature.auth.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.arman.dev.fellafeedsassignment.core.common.Resource
import com.arman.dev.fellafeedsassignment.feature.auth.domain.repository.AuthenticationRepository
import com.arman.dev.fellafeedsassignment.feature.auth.presentation.contract.AuthEffect
import com.arman.dev.fellafeedsassignment.feature.auth.presentation.contract.AuthIntent
import com.arman.dev.fellafeedsassignment.feature.auth.presentation.contract.AuthUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val authenticationRepository: AuthenticationRepository
): ViewModel(){

    private val _authUiState = MutableStateFlow(AuthUiState())
    val authUiState = _authUiState.asStateFlow()

    private val _effect = Channel<AuthEffect>()
    val effect = _effect.receiveAsFlow()


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

            val phoneNumber = _authUiState.value.phoneNumber

            when(val result = withContext(Dispatchers.IO){authenticationRepository.requestOtp(phoneNumber)}){
                is Resource.Error -> {
                    _authUiState.update {
                        it.copy(
                            isLoading = false ,
                            error = result.message
                        )
                    }
                    _effect.send(AuthEffect.ShowToastMessage(result.message))
                }
                is Resource.Success -> {
                    _authUiState.update {
                        it.copy(
                            isLoading = false ,
                            error = null ,
                        )
                    }
                    _effect.send(AuthEffect.NavigateToOtpScreen)
                }
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