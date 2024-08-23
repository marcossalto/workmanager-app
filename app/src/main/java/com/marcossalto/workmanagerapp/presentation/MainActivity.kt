package com.marcossalto.workmanagerapp.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.marcossalto.workmanagerapp.presentation.navigation.Navigation
import com.marcossalto.workmanagerapp.ui.theme.WorkManagerAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            WorkManagerAppTheme {
                Navigation()
            }
        }
    }
}
