package com.marcossalto.workmanagerapp.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.marcossalto.workmanagerapp.R

@Composable
fun HomeContent(
    modifier: Modifier = Modifier,
    onStartSync: () -> Unit,
    onBackup: () -> Unit
) {
    Surface(
        color = MaterialTheme.colorScheme.surface,
        modifier = modifier
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center

        ) {
            Button(
                onClick = { onStartSync() }
            ) {
                Text(
                    text = stringResource(id = R.string.start_sync)
                )
            }
            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(16.dp)
            )
            Button(
                onClick = { onBackup() }
            ) {
                Text(
                    text = stringResource(id = R.string.backup)
                )
            }
        }

    }
}
