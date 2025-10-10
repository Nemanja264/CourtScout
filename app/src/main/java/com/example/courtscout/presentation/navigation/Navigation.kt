package com.example.courtscout.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.courtscout.presentation.ui.screens.LoginScreen
import com.example.courtscout.presentation.ui.screens.RegisterScreen
import com.example.courtscout.presentation.ui.screens.HomeScreen
import androidx.navigation.NavHostController
import com.google.firebase.auth.FirebaseAuth

@Composable
fun Navigation(modifier: Modifier, navController: NavHostController = rememberNavController()){
    val user = FirebaseAuth.getInstance().currentUser
    val startRoute = if (user != null)
        Screen.HomeScreen.route
    else
        Screen.LoginScreen.route

    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = startRoute, modifier = modifier){
        composable(route = Screen.LoginScreen.route){
            LoginScreen(navController = navController, onNavigateToRegister = {
                navController.navigate(Screen.RegisterScreen.route)
            })
        }
        composable(route = Screen.RegisterScreen.route){
            RegisterScreen(onRegisterSuccess = {
                navController.navigate(Screen.HomeScreen.route) {
                    popUpTo(Screen.LoginScreen.route) { inclusive = true }
                    launchSingleTop = true
                } },
                onNavigateBack={
                    navController.popBackStack()
                },
                navController = navController)
        }

        composable(route = Screen.HomeScreen.route)
        {
            HomeScreen(navController=navController)
        }
    }
}