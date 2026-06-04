package com.arman.dev.fellafeedsassignment.feature.auth.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.arman.dev.fellafeedsassignment.feature.auth.presentation.contract.AuthBoardingEffect
import com.arman.dev.fellafeedsassignment.feature.auth.presentation.contract.AuthBoardingIntent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthBoardingViewModel @Inject constructor(): ViewModel(){
    private val _effect = Channel<AuthBoardingEffect>()
    val effect =_effect.receiveAsFlow()

    fun onIntent(intent: AuthBoardingIntent){
        when(intent){
            AuthBoardingIntent.ClickedLetsStart ->onLetsStartClick()
        }
    }

    fun onLetsStartClick(){
        viewModelScope.launch {
            _effect.send(AuthBoardingEffect.NavigateFromAuthBoarding)
        }
    }
}