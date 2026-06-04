package com.arman.dev.fellafeedsassignment.feature.auth.presentation

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
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.arman.dev.fellafeedsassignment.R
import com.arman.dev.fellafeedsassignment.feature.auth.presentation.contract.AuthIntent
import com.arman.dev.fellafeedsassignment.feature.auth.presentation.contract.AuthUiState

@Composable
fun AuthScreen(
    modifier: Modifier = Modifier ,
    uiState : AuthUiState ,
    onIntent: (AuthIntent)-> Unit
) {

    LaunchedEffect(Unit) {

    }

    Scaffold(
        modifier = modifier
            .fillMaxSize()
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {

            Spacer(modifier = Modifier.height(12.dp))

            Image(
                painter = painterResource(R.drawable.logo),
                contentDescription = null,
                modifier = Modifier.size(68.dp)
            )

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp)
            ) {

                ReusableText(
                    text = "Welcome",
                    textStyle = TextStyle(
                        fontSize = 32.sp,
                        lineHeight = 32.sp,
                        fontWeight = FontWeight.W600,
                        textAlign = TextAlign.Start
                    )
                )

                Spacer(modifier = Modifier.height(12.dp))

                ReusableText(
                    text = "Sign in or join to continue your home search.",
                    textStyle = TextStyle(
                        fontSize = 18.sp,
                        lineHeight = 18.sp,
                        fontWeight = FontWeight.W300,
                        textAlign = TextAlign.Start
                    )
                )

                Spacer(modifier = Modifier.height(24.dp))

                PhoneNumberField(
                    phone = uiState.phoneNumber ,
                    onPhoneChange = onIntent
                )

                Spacer(modifier = Modifier.weight(1f))

                Box(modifier = Modifier
                    .fillMaxWidth()
                    .imePadding()
                    .padding(horizontal = 20.dp)){
                    Button(
                        modifier = Modifier.height(56.dp) ,
                        shape = RoundedCornerShape(30.dp) ,
                        enabled = uiState.isContinueEnabled && !uiState.isLoading,
                        onClick = {
                            onIntent(AuthIntent.ContinueClicked)
                        }
                    ) {
                       when{
                           uiState.isLoading->{
                               CircularProgressIndicator()
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
}


@Composable
fun PhoneNumberField(
    modifier: Modifier = Modifier,
    phone: String, onPhoneChange: (AuthIntent) -> Unit
) {

    OutlinedTextField(
        modifier = modifier
            .fillMaxWidth()
            .height(54.dp),
        shape = RoundedCornerShape(30.dp),
        value = phone,
        onValueChange = {
            if(it.length<=10){
                onPhoneChange(AuthIntent.PhoneNumberChanged(it))
            }
        },
        placeholder = {
            Text(
                text = "Enter your mobile number"
            )
        }
    )
}

@Composable
fun ReusableText(
    text: String,
    textStyle: TextStyle
) {
    Text(
        text = text,
        style = textStyle
    )
}


@Composable
@Preview(showBackground = true)
fun AuthScreenPreview() {
    AuthScreen(
        modifier = Modifier,
        uiState = AuthUiState(),
        onIntent = {}
    )
}