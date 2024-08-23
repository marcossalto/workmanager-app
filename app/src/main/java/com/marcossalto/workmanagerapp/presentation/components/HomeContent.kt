package com.marcossalto.workmanagerapp.presentation.components

import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.marcossalto.workmanagerapp.R

@Composable
fun HomeContent(
    modifier: Modifier = Modifier,
    onStartSync: () -> Unit
) {
    Surface(
        color = MaterialTheme.colorScheme.surface,
        modifier = modifier
    ) {
        Button(
            onClick = { onStartSync() }
        ) {
            Text(
                text = stringResource(id = R.string.start_sync)
            )
        }
    }
}
