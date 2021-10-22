package com.jeffrey.debuggy.worker

import android.content.Context
import android.content.Intent
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.jeffrey.debuggy.data.notification.NotificationHelper
import com.jeffrey.debuggy.data.preference.PreferencesHelper
import com.jeffrey.debuggy.ui.main.MainActivity
import com.jeffrey.debuggy.util.RootUtils
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class TimeoutWorker(context: Context, parameters: WorkerParameters) :
    CoroutineWorker(context, parameters), KoinComponent {

    private val preference: PreferencesHelper by inject()
    private val notification: NotificationHelper by inject()

    val intent = Intent(applicationContext, MainActivity::class.java)

    override suspend fun doWork(): Result = withContext(Dispatchers.IO) {
        return@withContext runCatching {
            runTimer()

            Result.success()
        }.getOrElse {
            Result.failure()
        }
    }

    private suspend fun runTimer() {
        if (preference.adbAfterWhile != 0) {
            val time = preference.adbAfterWhile.toLong() * (60 * 60000)
            delay(time)
            RootUtils.disableTcp(notification)
            preference.adbEnabled = false
        } else {
            Result.success()
        }
    }
}