package com.jeffrey.debuggy.receiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast
import com.jeffrey.debuggy.R
import com.jeffrey.debuggy.data.notification.NotificationHelper
import com.jeffrey.debuggy.data.preference.PreferencesHelper
import com.jeffrey.debuggy.util.RootUtils
import com.jeffrey.debuggy.util.system.LogLevel
import com.jeffrey.debuggy.util.system.writeLog
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class BootDeviceReceiver : BroadcastReceiver(), KoinComponent {

    private val notification by inject<NotificationHelper>()
    private val preference by inject<PreferencesHelper>()

    override fun onReceive(context: Context, intent: Intent) {
        when (intent.action) {
            Intent.ACTION_BOOT_COMPLETED -> {
                if (preference.runAfterBoot) {
                    RootUtils.enableTcp(notification, context, preference.port)
                    Toast.makeText(
                        context,
                        context.resources.getString(R.string.toast_debuggy_running),
                        Toast.LENGTH_LONG
                    ).show()
                }
                writeLog(LogLevel.INFO, "$TAG thread is sleep (${THREAD_SLEEP_DURATION}L)")
                Thread.sleep(THREAD_SLEEP_DURATION)
            }
        }
    }

    companion object {
        const val TAG = "BootDeviceReceiver"

        private const val THREAD_SLEEP_DURATION = 8000L
    }
}