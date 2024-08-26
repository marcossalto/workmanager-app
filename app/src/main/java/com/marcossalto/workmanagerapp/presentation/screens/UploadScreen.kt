package com.marcossalto.workmanagerapp.presentation.screens

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.marcossalto.workmanagerapp.core.WorkerEvent
import com.marcossalto.workmanagerapp.presentation.components.HomeTopBar
import com.marcossalto.workmanagerapp.presentation.components.UploadContent

@Composable
fun UploadScreen(
    viewModel: HomeViewModel = hiltViewModel()
) {
    Scaffold(
        topBar = {
            HomeTopBar()
        },
        content = { innerPadding ->
            UploadContent(
                modifier = Modifier
                    .padding(innerPadding),
                onUpload = { viewModel.onEvent(WorkerEvent.UploadImage(it)) }
            )
        }
    )
}
