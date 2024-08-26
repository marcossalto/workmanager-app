package com.marcossalto.workmanagerapp.presentation.screens

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.work.Constraints
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.NetworkType
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import com.marcossalto.workmanagerapp.core.BackupWorker
import com.marcossalto.workmanagerapp.core.SyncWorker
import com.marcossalto.workmanagerapp.core.WorkerEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import java.util.concurrent.TimeUnit
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val application: Application) : ViewModel() {
    companion object {
        private const val REPEAT_INTERVAL = 15L
        private const val ONE_TIME = 1L
    }

    fun onEvent(
        event: WorkerEvent
    ) {
        when (event) {
            is WorkerEvent.StartPeriodicSync -> {
                setupPeriodicSync()
            }
            is WorkerEvent.Backup -> {
                scheduleBackup()
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

    private fun scheduleBackup() {
        // Set constraints: Only backup when connected to Wi-Fi and charging
        val constraints = Constraints.Builder()
            .setRequiredNetworkType(NetworkType.UNMETERED)
            .setRequiresCharging(true)
            .build()

        // Create a one-time job request for backup
        val backupRequest = OneTimeWorkRequestBuilder<BackupWorker>()
            .setInitialDelay(ONE_TIME, TimeUnit.MILLISECONDS)
            .setConstraints(constraints)
            .build()

        // Queue the request with WorkManager
        WorkManager.getInstance(application).enqueue(backupRequest)
    }
}
