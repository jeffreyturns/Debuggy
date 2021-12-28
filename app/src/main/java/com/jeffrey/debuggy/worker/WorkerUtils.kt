package com.jeffrey.debuggy.worker

import android.util.Log
import androidx.lifecycle.LifecycleOwner
import androidx.work.ExistingWorkPolicy
import androidx.work.OneTimeWorkRequest
import androidx.work.WorkManager

object WorkerUtils {

    fun beginUniqueWork(
        workManager: WorkManager,
        request: OneTimeWorkRequest,
        lifecycleOwner: LifecycleOwner
    ) {
        workManager.beginUniqueWork(
            Workers.WORKER_TIMEOUT_TASK_NAME, ExistingWorkPolicy.APPEND_OR_REPLACE,
            request
        ).enqueue().state
            .observe(lifecycleOwner) { state ->
                Log.d("WorkerHelper", "${request.javaClass.canonicalName} status: $state")
            }
    }

    fun cancelUniqueWork(workManager: WorkManager, workerTaskName: String) {
        workManager.cancelUniqueWork(workerTaskName)
    }
}