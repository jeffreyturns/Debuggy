package com.jeffrey.debuggy.worker

import androidx.lifecycle.LifecycleOwner
import androidx.work.ExistingWorkPolicy
import androidx.work.OneTimeWorkRequest
import androidx.work.WorkManager
import com.jeffrey.debuggy.util.system.LogLevel
import com.jeffrey.debuggy.util.system.writeLog

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
                writeLog(LogLevel.DEBUG, "${request.javaClass.canonicalName} status: $state")
            }
    }

    fun cancelUniqueWork(workManager: WorkManager, workerTaskName: String) {
        workManager.cancelUniqueWork(workerTaskName)
    }
}