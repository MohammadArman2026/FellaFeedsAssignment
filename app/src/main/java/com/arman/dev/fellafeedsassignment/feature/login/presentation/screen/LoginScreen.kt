package com.arman.dev.fellafeedsassignment.feature.login.presentation.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.arman.dev.fellafeedsassignment.R
import com.arman.dev.fellafeedsassignment.feature.login.presentation.contract.LoginEffect
import com.arman.dev.fellafeedsassignment.feature.login.presentation.contract.LoginIntent
import com.arman.dev.fellafeedsassignment.feature.login.presentation.viewmodel.LoginViewModel

@Composable
fun LoginScreen(
    viewModel: LoginViewModel = hiltViewModel()
) {
    val context = LocalContext.current

    val  snackBarState = remember {
        SnackbarHostState()
    }

    LaunchedEffect(Unit) {
        viewModel.effect.collect {effect ->

            when(effect){
                LoginEffect.NavigateToOtp -> {
                    //trigger navigation
                    snackBarState.showSnackbar("trigger navigate to otp")
                }
                is LoginEffect.ShowToast -> {
                    //show toast or snack bar
                    snackBarState.showSnackbar(effect.message)
                }
            }
        }
    }

    val uiState by viewModel.loginUiState.collectAsStateWithLifecycle()

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        snackbarHost = {
            SnackbarHost(
                hostState = snackBarState
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            Image(
                painter = painterResource(
                    id = R.drawable.logo
                ),
                modifier = Modifier.size(68.dp),
                contentScale = ContentScale.Fit,
                contentDescription = "Logo"
            )

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = "Welcome,",
                modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp),
                style = TextStyle(
                    fontWeight = FontWeight.W600,
                    fontSize = 32.sp,
                    color = Color(0xFF1F1F1F)
                )
            )
            Spacer(modifier = Modifier.height(12.dp))
            Text(
                text = "Sign in or join to continue your home search." ,
                modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp)
            )

            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                modifier = Modifier.fillMaxWidth().height(54.dp).padding(horizontal = 16.dp),
                value = uiState.phoneNumber,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Phone
                ),
                onValueChange = {
                    viewModel.onIntent(LoginIntent.PhoneNumberChanged(it))
                } ,
                placeholder = {
                    Text(
                        text = "Enter your mobile number"
                    )
                },
                shape = RoundedCornerShape(84.dp)
            )

            Spacer(modifier = Modifier.weight(1f))

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .imePadding()
                    .padding(
                        horizontal = 16.dp,
                    )
            ){
                Button(
                    enabled = uiState.isContinueButtonEnabled && !uiState.isLoading,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(56.dp) ,
                    shape = RoundedCornerShape(73.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFF1F1F1F) ,
                        contentColor = Color.White
                    ),
                    onClick = {
                        viewModel.onIntent(LoginIntent.ContinueClicked)
                    }
                ) {
                    when{
                        uiState.isLoading->{
                            CircularProgressIndicator(
                                modifier = Modifier ,
                                color = Color.Black
                            )
                        }
                        else->{
                            Text(
                                text = "Continue"
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
@Preview(showBackground = true)
fun LoginScreenPreview() {
    LoginScreen()
}