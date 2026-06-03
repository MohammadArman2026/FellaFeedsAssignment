package com.arman.dev.fellafeedsassignment.feature.login.presentation.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun BoardingScreen(){
    Scaffold(
        modifier = Modifier
            .fillMaxSize() ,
        containerColor = Color.Yellow
    ) {paddingValues ->
       Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {

           Column(
               modifier = Modifier
                   .fillMaxWidth()
                   .padding(horizontal = 53.dp)
                   .align(
                       Alignment.Center
                   ),
               horizontalAlignment = Alignment.CenterHorizontally
           ){
               Box(
                   modifier = Modifier
                       .size(68.dp)
                       .clip(CircleShape)
                       .border(2.dp , Color.Black , CircleShape) ,
                   contentAlignment = Alignment.Center
               ){
                   Icon(
                       imageVector = Icons.Default.Done ,
                       contentDescription = null ,
                       modifier = Modifier.size(24.dp) ,
                       tint = Color.Black
                   )
               }

               Spacer(modifier = Modifier.height(12.dp))

               Text(
                   text = "Operator Confirmed" ,
                   style = TextStyle(
                       fontSize = 32.sp ,
                       lineHeight = 32.sp ,
                       fontWeight = FontWeight.W600 ,
                   )
               )

               Spacer(modifier = Modifier.height(12.dp))

               Text(
                   text = "Now you can find your daily schedule, tickets, & shift timings" ,
                   style = TextStyle(
                       fontSize = 18.sp ,
                       lineHeight = 18.sp ,
                       fontWeight = FontWeight.W300 ,
                       textAlign = TextAlign.Center
                   )
               )
           }

           Box(modifier = Modifier
               .fillMaxWidth()
               .align(Alignment.BottomCenter)){
               Button(
                   modifier = Modifier
                       .fillMaxWidth()
                       .padding(horizontal = 20.dp),
                   onClick = {} ,
                   colors = ButtonDefaults.buttonColors(
                       containerColor = Color.Black ,
                       contentColor = Color.White
                   )
               ) {
                   Text(
                       text = "Lets start"
                   )
               }
           }
        }
    }
}

@Composable
@Preview(showBackground = true)
fun BoardingScreenPreview(){
    BoardingScreen()
}