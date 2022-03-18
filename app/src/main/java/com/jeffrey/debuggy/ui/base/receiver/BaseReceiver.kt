package com.jeffrey.debuggy.ui.base.receiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.jeffrey.debuggy.data.notification.NotificationHelper
import com.jeffrey.debuggy.data.preference.PreferencesHelper
import com.jeffrey.debuggy.util.system.LogLevel
import com.jeffrey.debuggy.util.system.writeLog
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

open class BaseReceiver : BroadcastReceiver(), KoinComponent {

    val notification by inject<NotificationHelper>()
    val preference by inject<PreferencesHelper>()

    override fun onReceive(context: Context, intent: Intent) =
        writeLog(LogLevel.INFO, "Successfully invoked!")

}