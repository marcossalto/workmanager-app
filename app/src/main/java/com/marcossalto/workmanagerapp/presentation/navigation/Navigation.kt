package com.marcossalto.workmanagerapp.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.marcossalto.workmanagerapp.presentation.screens.HomeScreen
import com.marcossalto.workmanagerapp.presentation.screens.Screen
import com.marcossalto.workmanagerapp.presentation.screens.UploadScreen

@Composable
fun Navigation() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Screen.Upload.route
    ) {
        composable(
            route = Screen.Home.route
        ) {
            HomeScreen()
        }
        composable(
            route = Screen.Upload.route
        ) {
            UploadScreen()
        }
    }
}
