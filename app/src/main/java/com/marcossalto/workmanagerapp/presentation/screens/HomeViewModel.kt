package com.marcossalto.workmanagerapp.presentation.screens

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.work.Constraints
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.NetworkType
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import com.marcossalto.workmanagerapp.core.SyncWorker
import com.marcossalto.workmanagerapp.core.SyncWorkerEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import java.util.concurrent.TimeUnit
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val application: Application) : ViewModel() {
    companion object {
        private const val REPEAT_INTERVAL = 15L
    }

    fun onEvent(
        event: SyncWorkerEvent
    ) {
        when (event) {
            is SyncWorkerEvent.StartPeriodicSync -> {
                setupPeriodicSync()
            }
        }
    }

    private fun setupPeriodicSync() {

        // Set constraints: Only sync when device is connected to Wi-Fi
        val constraints = Constraints.Builder()
            .setRequiredNetworkType(NetworkType.UNMETERED)
            .build()

        // Create a periodic work request
        val syncRequest = PeriodicWorkRequestBuilder<SyncWorker.SyncWorker>(REPEAT_INTERVAL, TimeUnit.MINUTES)
            .setInitialDelay(REPEAT_INTERVAL, TimeUnit.MINUTES)
            .setConstraints(constraints)
            .build()

        // Queue the request with WorkManager
        WorkManager.getInstance(application).enqueueUniquePeriodicWork(
            "SyncWork",
            ExistingPeriodicWorkPolicy.KEEP, // Keep the existing task if it is already queued
            syncRequest
        )
    }
}
