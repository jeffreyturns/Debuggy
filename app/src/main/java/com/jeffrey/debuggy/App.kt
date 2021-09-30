package com.jeffrey.debuggy

import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import android.os.Build
import com.jeffrey.debuggy.data.notification.Notifications
import com.jeffrey.debuggy.data.notification.NotificationHelper
import com.jeffrey.debuggy.utils.Constants
import com.jeffrey.debuggy.utils.RootUtil

class App : Application() {

    private val channel = Notifications.BASE_CHANNEL_NAME
    private val process: Process = Runtime.getRuntime().exec("su")

    companion object {

        private var root = false
        private var daemonStatus = Constants.UNDEFINED_TEXT
        private var notificationHelper: NotificationHelper? = null

        fun isRoot(): Boolean {
            return root
        }

        fun daemonStatus(): String {
            return daemonStatus
        }

        fun notificationManager(): NotificationHelper {
            return notificationHelper!!
        }
    }

    override fun onCreate() {
        super.onCreate()
        notificationHelper = NotificationHelper(this)
        root = RootUtil.canRunRootCommands(process)
        if (root) daemonStatus = RootUtil.getDaemonStatus(process)
        createNotificationChannel()
    }

    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= 26) {
            val notificationChannel =
                NotificationChannel(
                    channel,
                    getString(R.string.title_adb_enabled),
                    NotificationManager.IMPORTANCE_DEFAULT
                )
            (getSystemService(NotificationManager::class.java) as NotificationManager).createNotificationChannel(
                notificationChannel
            )
        }
    }
}