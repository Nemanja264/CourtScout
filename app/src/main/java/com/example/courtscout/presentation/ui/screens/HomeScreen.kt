package com.example.courtscout.presentation.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.navOptions
import com.example.courtscout.presentation.AppViewModelProvider
import com.example.courtscout.presentation.navigation.Screen
import com.example.courtscout.presentation.services.auth.AuthState
import com.example.courtscout.presentation.viewmodels.AuthViewModel

@Composable
fun HomeScreen(modifier: Modifier = Modifier, navController: NavController, authViewModel: AuthViewModel = viewModel(factory = AppViewModelProvider.Factory))
{
    val context = LocalContext.current

    fun backToLogIn()
    {
        navController.navigate(Screen.LoginScreen.route) {
            popUpTo(Screen.HomeScreen.route) { inclusive = true }
            launchSingleTop = true
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 32.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Text(text = "Welcome to Home Screen!")
        Button(
            onClick = {
                authViewModel.signOut()
                backToLogIn() },
            modifier = Modifier.fillMaxWidth(),
            enabled = true
        ){
            Text("Sign Out")
        }
    }

}