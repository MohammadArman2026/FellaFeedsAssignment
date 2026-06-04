package com.arman.dev.fellafeedsassignment

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.arman.dev.fellafeedsassignment.core.navigation.AppNavHost
import com.arman.dev.fellafeedsassignment.core.ui.theme.FellaFeedsAssignmentTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            FellaFeedsAssignmentTheme {
                val navController = rememberNavController()
                AppNavHost(
                    modifier = Modifier,
                    navController = navController
                )
            }
        }
    }
}