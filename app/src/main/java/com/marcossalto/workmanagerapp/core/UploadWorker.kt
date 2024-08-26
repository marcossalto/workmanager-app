package com.marcossalto.workmanagerapp.core

import android.content.Context
import android.net.Uri
import android.util.Log
import androidx.hilt.work.HiltWorker
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream

@HiltWorker
class UploadWorker @AssistedInject constructor(
    @Assisted private val appContext: Context,
    @Assisted private val workerParams: WorkerParameters
) : CoroutineWorker(appContext, workerParams) {

    override suspend fun doWork(): Result {
        return withContext(Dispatchers.IO) {
            try {
                var body: MultipartBody.Part? = null
                val imageUri = Uri.parse(inputData.getString("URI"))

                // Open and read the selected image file
                appContext.contentResolver.openFileDescriptor(
                    imageUri,
                    "r"
                ).use { fs ->
                    fs?.let {
                        FileInputStream(it.fileDescriptor).use { inputStream ->
                            // Create a temporary file and copy the image contents
                            val imageFile = File(appContext.cacheDir, "image.jpeg")
                            val outputStream = FileOutputStream(imageFile)
                            inputStream.copyTo(outputStream)

                            // Create a MultipartBody.Part for the image file
                            val requestFile =
                                imageFile.asRequestBody("image/jpeg".toMediaTypeOrNull())
                            body = MultipartBody.Part.createFormData(
                                "image",
                                imageFile.name,
                                requestFile
                            )
                        }
                    }
                }

                // If the image file is not created, consider the work a failure
                if (body == null)
                    return@withContext Result.failure()

                Log.d("UploadWorker", "Uploading image...")

                // UploadImage the image and handle the response
                uploadImage(body!!)

                // Success
                Log.d("UploadWorker", "Upload completed successfully")
                Result.success()
            } catch (e: Exception) {
                Log.e("UploadWorker", "Error during upload", e)
                Result.failure()
            }
        }
    }

    private fun uploadImage(body: MultipartBody.Part) {
        // Implements the logic to upload the image
        // Example: Uploading image to a remote server
        Thread.sleep(4000) // Simulates an operation that takes time
    }
}