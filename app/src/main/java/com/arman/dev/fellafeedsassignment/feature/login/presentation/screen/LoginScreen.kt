package com.arman.dev.fellafeedsassignment.feature.login.presentation.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.arman.dev.fellafeedsassignment.R

@Composable
fun LoginScreen() {

    Scaffold(
        modifier = Modifier.fillMaxSize()
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

            Text(
                text = "Sign in or join to continue your home search." ,
                modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp)
            )

            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                modifier = Modifier.fillMaxWidth().height(54.dp).padding(horizontal = 16.dp),
                value = "" ,
                onValueChange = {} ,
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
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(56.dp) ,
                    shape = RoundedCornerShape(73.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFF1F1F1F) ,
                        contentColor = Color.White
                    ),
                    onClick = {}
                ) {
                    Text(
                        text = "Continue"
                    )
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