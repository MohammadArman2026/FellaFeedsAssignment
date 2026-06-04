package com.arman.dev.fellafeedsassignment.feature.auth.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.arman.dev.fellafeedsassignment.R
import com.arman.dev.fellafeedsassignment.feature.auth.presentation.contract.AuthOtpIntent
import com.arman.dev.fellafeedsassignment.feature.auth.presentation.contract.AuthOtpUiState


@Composable
fun AuthOtpScreen(
    modifier: Modifier = Modifier,
    onIntent :(AuthOtpIntent)->Unit ,
    uiState: AuthOtpUiState
){
    Scaffold(
        modifier = modifier.fillMaxSize()
    ) {paddingValues ->
        Column (
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ){
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
                text = "Lets confirm it's you,",
                modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp),
                style = TextStyle(
                    fontWeight = FontWeight.W600,
                    fontSize = 32.sp,
                    color = Color(0xFF1F1F1F)
                )
            )

            Spacer(modifier = Modifier.height(12.dp))

            Text(
                text = "Enter the six digit code we sent.\n +91634621052" ,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
            )

            Spacer(modifier = Modifier.height(12.dp))

            OtpField(
                otp = uiState.otp ,
                onOtpChange = {
                    onIntent(AuthOtpIntent.OtpChanged(it))
                }
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
                    enabled = uiState.isSubmitEnabled && !uiState.isLoading,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(56.dp) ,
                    shape = RoundedCornerShape(73.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFF1F1F1F) ,
                        contentColor = Color.White
                    ),
                    onClick = {

                    }
                ){
                   when{
                       uiState.isLoading->{
                           CircularProgressIndicator()
                       }
                       else->{
                           Text(
                               text = "submit"
                           )
                       }
                   }
                }
            }
        }
    }
}


@Composable
fun OtpField(
    otp: String,
    onOtpChange: (String) -> Unit
) {

    val focusRequester = remember {
        FocusRequester()
    }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
            .clickable {
                focusRequester.requestFocus()
            }
    ) {

        BasicTextField(
            value = otp,
            onValueChange = { value ->

                if (
                    value.length <= 6 &&
                    value.all { it.isDigit() }
                ) {
                    onOtpChange(value)
                }
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.NumberPassword
            ),
            singleLine = true,
            textStyle = TextStyle(
                color = Color.Transparent
            ),
            modifier = Modifier
                .fillMaxWidth()
                .focusRequester(focusRequester),
            decorationBox = {

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {

                    repeat(6) { index ->

                        val char =
                            otp.getOrNull(index)?.toString() ?: ""

                        val isFilled =
                            char.isNotEmpty()

                        Box(
                            modifier = Modifier
                                .size(48.dp)
                                .border(
                                    width = 1.dp,
                                    color = if (isFilled)
                                        Color.Red
                                    else
                                        Color.LightGray,
                                    shape = RoundedCornerShape(20.dp)
                                ),
                            contentAlignment = Alignment.Center
                        ) {

                            Text(
                                text = char,
                                fontSize = 28.sp,
                                fontWeight = FontWeight.SemiBold,
                                color = Color.Black
                            )
                        }
                    }
                }
            }
        )
    }

    LaunchedEffect(Unit) {
        focusRequester.requestFocus()
    }
}






@Composable
@Preview(showBackground = true)
fun AuthOtpScreenPreview(){
    AuthOtpScreen(
        modifier = Modifier,
        onIntent = {},
        uiState = AuthOtpUiState()
    )
}