package com.marcossalto.workmanagerapp.presentation.screens

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.marcossalto.workmanagerapp.core.SyncWorkerEvent
import com.marcossalto.workmanagerapp.presentation.components.HomeContent
import com.marcossalto.workmanagerapp.presentation.components.HomeTopBar

@Composable
fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel()
) {
    Scaffold(
        topBar = {
            HomeTopBar()
        },
        content = { innerPadding ->
            HomeContent(
                modifier = Modifier
                    .padding(innerPadding),
                onStartSync = { viewModel.onEvent(SyncWorkerEvent.StartPeriodicSync) }
            )
        }
    )
}
