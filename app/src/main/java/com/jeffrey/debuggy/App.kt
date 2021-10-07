package com.jeffrey.debuggy

import com.jeffrey.debuggy.data.notification.NotificationHelper.Companion.createNotificationChannel
import com.jeffrey.debuggy.data.notification.Notifications
import com.jeffrey.debuggy.utils.Constants
import com.jeffrey.debuggy.utils.RootUtil

class App : AppModule() {

    private val process: Process = Runtime.getRuntime().exec("su")

    companion object {

        private var root = false
        private var daemonStatus = Constants.UNDEFINED_TEXT

        fun isRoot(): Boolean {
            return root
        }

        fun daemonStatus(): String {
            return daemonStatus
        }
    }

    override fun onCreate() {
        super.onCreate()
        initModule()

        root = RootUtil.canRunRootCommands(process)
        if (root) daemonStatus = RootUtil.getDaemonStatus(process)
        createNotificationChannel(Notifications.BASE_CHANNEL_NAME, this)
    }
}