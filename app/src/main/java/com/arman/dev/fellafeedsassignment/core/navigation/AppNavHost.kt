package com.arman.dev.fellafeedsassignment.core.navigation

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.arman.dev.fellafeedsassignment.feature.auth.presentation.AuthBoardingScreen
import com.arman.dev.fellafeedsassignment.feature.auth.presentation.AuthOtpScreen
import com.arman.dev.fellafeedsassignment.feature.auth.presentation.AuthScreen
import com.arman.dev.fellafeedsassignment.feature.auth.presentation.contract.AuthBoardingEffect
import com.arman.dev.fellafeedsassignment.feature.auth.presentation.contract.AuthEffect
import com.arman.dev.fellafeedsassignment.feature.auth.presentation.contract.AuthOtpEffect
import com.arman.dev.fellafeedsassignment.feature.auth.presentation.viewmodel.AuthBoardingViewModel
import com.arman.dev.fellafeedsassignment.feature.auth.presentation.viewmodel.AuthOtpViewModel
import com.arman.dev.fellafeedsassignment.feature.auth.presentation.viewmodel.AuthViewModel

@Composable
fun AppNavHost(
    modifier: Modifier,
    navController: NavHostController,
){
    NavHost(
        startDestination = NavigationRoute.AuthScreen.route ,
        navController = navController
    ){
        composable(
            route = NavigationRoute.AuthScreen.route
        ){
            val viewmodel : AuthViewModel = hiltViewModel()
            val uiState by viewmodel.authUiState.collectAsStateWithLifecycle()
            val context = LocalContext.current
            LaunchedEffect(Unit) {
                viewmodel.effect.collect {
                    when(it){
                        AuthEffect.NavigateToOtpScreen -> navController.navigate(NavigationRoute.OtpScreen.route)
                        is AuthEffect.ShowToastMessage -> {
                            Toast.makeText(
                                context ,
                                it.message,
                                Toast.LENGTH_LONG
                            ).show()
                        }
                    }
                }
            }
            AuthScreen(
              uiState = uiState ,
                onIntent = viewmodel::onIntent ,
            )
        }

        composable(
            route = NavigationRoute.OtpScreen.route
        ){

            val viewModel : AuthOtpViewModel  = hiltViewModel()
            val uiState by viewModel.uiState.collectAsStateWithLifecycle()

            LaunchedEffect(Unit) {
                viewModel.effect.collect {
                    when(it){
                        AuthOtpEffect.NavigateToOnBoardingScreen -> navController.navigate(
                            NavigationRoute.BoardingScreen.route)
                    }
                }
            }

            AuthOtpScreen(
                onIntent = viewModel::onIntent,
                uiState = uiState
            )
        }

        composable(
            route = NavigationRoute.BoardingScreen.route
        ){
            val viewModel : AuthBoardingViewModel = hiltViewModel()
            LaunchedEffect(Unit) {
                viewModel.effect.collect {
                    when(it){
                        AuthBoardingEffect.NavigateFromAuthBoarding -> navController.navigate("")
                    }
                }
            }
            AuthBoardingScreen (
                onIntent = viewModel::onIntent
            )
        }
    }
}