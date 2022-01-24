package com.jeffrey.debuggy

import com.jeffrey.debuggy.data.notification.NotificationHelper.Companion.createChannels
import com.jeffrey.debuggy.util.RootUtils

class App : AppModule() {

    companion object {
        var root = false
        var daemonStatus = ""
    }

    override fun onCreate() {
        super.onCreate()

        daemonStatus = RootUtils.daemonStatus
        root = daemonStatus != ""
        createChannels(this)
    }
}