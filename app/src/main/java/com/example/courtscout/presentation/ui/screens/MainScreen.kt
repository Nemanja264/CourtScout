package com.example.courtscout.presentation.ui.screens

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.courtscout.presentation.AppViewModelProvider
import com.example.courtscout.presentation.navigation.BottomNavItem
import com.example.courtscout.presentation.ui.components.CourtScoutBottomBar
import com.example.courtscout.presentation.viewmodels.AuthViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(rootNavController: NavController) {
    val bottomNavController = rememberNavController()
    val authViewModel: AuthViewModel = viewModel(factory = AppViewModelProvider.Factory)

    Scaffold(
        bottomBar = { CourtScoutBottomBar(navController = bottomNavController) }
    ) { innerPadding ->
        NavHost(
            navController = bottomNavController,
            startDestination = BottomNavItem.Home.route,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(BottomNavItem.Home.route) { HomeScreen(navController = rootNavController, authViewModel = authViewModel) }
            composable(BottomNavItem.Map.route) { MapScreen() }
            composable(BottomNavItem.CourtsList.route) { CourtsListScreen() }
            composable(BottomNavItem.ScoreBoard.route) { ScoreBoardScreen() }
            composable(BottomNavItem.Profile.route) { ProfileScreen() }
        }
    }
}