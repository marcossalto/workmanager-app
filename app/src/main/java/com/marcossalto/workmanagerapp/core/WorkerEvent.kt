package com.marcossalto.workmanagerapp.core

sealed class WorkerEvent {
    data object StartPeriodicSync : WorkerEvent()
    data object Backup : WorkerEvent()
}
