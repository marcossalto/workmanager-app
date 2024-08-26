package com.marcossalto.workmanagerapp.core

import android.net.Uri

sealed class WorkerEvent {
    data object StartPeriodicSync : WorkerEvent()
    data object Backup : WorkerEvent()
    data class UploadImage(val uri: Uri) : WorkerEvent()
}
