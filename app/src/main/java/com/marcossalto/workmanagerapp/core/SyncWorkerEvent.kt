package com.marcossalto.workmanagerapp.core

sealed class SyncWorkerEvent {
    data object StartPeriodicSync : SyncWorkerEvent()
}
