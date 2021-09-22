package com.jeffrey.debuggy.receiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import android.widget.Toast
import com.jeffrey.debuggy.R
import com.jeffrey.debuggy.data.notification.NotificationHelper
import com.jeffrey.debuggy.data.preference.PreferenceHelper
import com.jeffrey.debuggy.utils.RootUtil

class BootDeviceReceiver : BroadcastReceiver() {

    private var notificationHelper: NotificationHelper? = null

    override fun onReceive(context: Context, intent: Intent) {
        notificationHelper = NotificationHelper(context)
        val action = intent.action
        if (action != null) {
            Log.d(TAG_BOOT_BROADCAST_RECEIVER, action)
        }
        if (Intent.ACTION_BOOT_COMPLETED == action) {
            if (PreferenceHelper.runAfterBoot(context)) {
                RootUtil.enableTcp(notificationHelper!!, context)
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