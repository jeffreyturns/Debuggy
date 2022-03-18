package com.jeffrey.debuggy.data.notification

import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.work.WorkManager
import com.jeffrey.debuggy.ui.base.receiver.BaseReceiver
import com.jeffrey.debuggy.util.RootUtils
import com.jeffrey.debuggy.util.system.LogLevel
import com.jeffrey.debuggy.util.system.writeLog
import com.jeffrey.debuggy.worker.WorkerUtils
import com.jeffrey.debuggy.worker.Workers
import com.jeffrey.debuggy.BuildConfig.APPLICATION_ID as ID

open class NotificationReceiver : BaseReceiver() {

    private var workManager: WorkManager? = null
    private val work: WorkerUtils = WorkerUtils

    override fun onReceive(context: Context, intent: Intent) {

        workManager = WorkManager.getInstance(context)

        when (intent.action) {
            ACTION_DISABLE_ADB -> {
                RootUtils.disableTcp(notification)
                work.cancelUniqueWork(workManager!!, Workers.WORKER_TIMEOUT_TASK_NAME)
                preference.adbEnabled = false

                context.sendBroadcast(Intent(ACTION_DISABLE_ADB))
            }
            else -> {
                writeLog(LogLevel.ERROR, "Undefined action")
            }
        }
    }

    companion object {
        const val TAG = "NotificationReceiver"

        const val ACTION_DISABLE_ADB = "$ID.$TAG.DISABLE_ADB"

        private val flags: Int = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
            PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_UPDATE_CURRENT else
            PendingIntent.FLAG_UPDATE_CURRENT

        internal fun disableTCP(context: Context): PendingIntent {
            val intent = Intent(context, NotificationReceiver::class.java).apply {
                action = ACTION_DISABLE_ADB
            }
            return PendingIntent.getBroadcast(context, 0, intent, flags)
        }
    }
}