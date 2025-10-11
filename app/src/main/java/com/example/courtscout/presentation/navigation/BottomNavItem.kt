package com.example.courtscout.presentation.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Place
import androidx.compose.material.icons.filled.Star
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomNavItem(val route: String, val icon: ImageVector, val title: String) {
    object Home : BottomNavItem("home", Icons.Default.Home, "Home")
    object Map : BottomNavItem("map", Icons.Filled.Place, "Map")
    object CourtsList : BottomNavItem("courts_list", Icons.Default.List, "List")
    object ScoreBoard : BottomNavItem("scoreboard", Icons.Default.Star, "ScoreBoard")
    object Profile : BottomNavItem("profile", Icons.Default.Person, "Profile")
}
