package com.jeffrey.debuggy.receiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import android.widget.Toast
import com.jeffrey.debuggy.R
import com.jeffrey.debuggy.data.notification.NotificationHelper
import com.jeffrey.debuggy.data.preference.PreferencesHelper
import com.jeffrey.debuggy.utils.RootUtil
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class BootDeviceReceiver : BroadcastReceiver(), KoinComponent {

    private val notification: NotificationHelper by inject()
    private val preference: PreferencesHelper by inject()

    override fun onReceive(context: Context, intent: Intent) {
        val action = intent.action
        if (action != null) {
            Log.d(TAG_BOOT_BROADCAST_RECEIVER, action)
        }
        if (Intent.ACTION_BOOT_COMPLETED == action) {
            if (preference.runAfterBoot) {
                RootUtil.enableTcp(notification, context, preference.port)
                Toast.makeText(
                    context,
                    context.resources.getString(R.string.toast_debuggy_running),
                    Toast.LENGTH_LONG
                ).show()
            }
            Thread.sleep(8000)
        }
    }

    companion object {
        private const val TAG_BOOT_BROADCAST_RECEIVER = "BOOT_BROADCAST_RECEIVER"
    }
}