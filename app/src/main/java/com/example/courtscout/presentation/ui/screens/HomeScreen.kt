package com.example.courtscout.presentation.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.courtscout.presentation.AppViewModelProvider
import com.example.courtscout.presentation.navigation.Screen
import com.example.courtscout.presentation.ui.components.OptionsMenu
import com.example.courtscout.presentation.ui.theme.OrangePrimary
import com.example.courtscout.presentation.viewmodels.AuthViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    navController: NavController,
    authViewModel: AuthViewModel = viewModel(factory = AppViewModelProvider.Factory)
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("CourtScout") },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = OrangePrimary
                ),
                actions = {
                    OptionsMenu(onSignOutClick = {
                        authViewModel.signOut()
                        navController.navigate(Screen.LoginScreen.route) {
                            popUpTo("main_screen") { inclusive = true }
                            launchSingleTop = true
                        }
                    })
                }
            )
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            contentAlignment = Alignment.Center
        ) {
            Text(text = "Home Screen")
        }
    }
}
