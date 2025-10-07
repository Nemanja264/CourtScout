package com.example.courtscout.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.Navigation
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.courtscout.presentation.auth.LoginScreen
import com.example.courtscout.presentation.auth.RegisterScreen

@Composable
fun Navigation(){
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Screen.LoginScreen.route){
        composable(route = Screen.LoginScreen.route){
            LoginScreen()
        }
        composable(route = Screen.RegisterScreen.route){
            RegisterScreen()
        }
    }
}