package com.marcossalto.workmanagerapp.presentation.screens

sealed class Screen(val route: String) {
    data object Home : Screen("home")
}
