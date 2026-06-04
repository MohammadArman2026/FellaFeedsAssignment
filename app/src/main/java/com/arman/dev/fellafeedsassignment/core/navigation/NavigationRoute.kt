package com.arman.dev.fellafeedsassignment.core.navigation

sealed class NavigationRoute (val route: String){
    object AuthScreen: NavigationRoute("Auth")
    object OtpScreen: NavigationRoute("OtpScreen")
    object BoardingScreen: NavigationRoute("BoardingScreen")
}