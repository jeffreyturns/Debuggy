package com.jeffrey.debuggy.data.notification

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.jeffrey.debuggy.R
import com.jeffrey.debuggy.util.Constants
import com.jeffrey.debuggy.util.Utils
import com.jeffrey.debuggy.util.extensions.getAttr

class NotificationHelper(context: Context) {

    private var notificationManager = NotificationManagerCompat.from(context)

    fun createADBEnabledNotification(context: Context, intent: Intent) {
        this.notificationManager.notify(
            Notifications.ID_CREATE_ENABLED_ADB,
            NotificationCompat.Builder(
                context,
                Notifications.BASE_CHANNEL_NAME
            ).setSmallIcon(R.drawable.ic_debuggy_notification_24dp)
                .setOngoing(true)
                .setColor(context.getAttr(R.attr.colorSecondary))
                .setContentTitle(context.resources.getString(R.string.title_adb_enabled))
                .setContentText(
                    if (Utils.ip() != Constants.UNDEFINED_TEXT) context.resources.getString(
                        R.string.content_adb_enabled,
                        Utils.ip()
                    ) else context.getString(R.string.message_ip_unable_determine)
                ).setPriority(-1)
                .setCategory(NotificationCompat.CATEGORY_SERVICE).setContentIntent(
                    PendingIntent.getActivity(
                        context.applicationContext,
                        0,
                        intent,
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
                            PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_UPDATE_CURRENT else
                            PendingIntent.FLAG_UPDATE_CURRENT
                    )
                ).build()
        )
    }

    fun cancelADBEnabledNotification() {
        this.notificationManager.cancel(Notifications.ID_CREATE_ENABLED_ADB)
    }

    fun crateNoInternetConnectionNotification(context: Context, intent: Intent) {
        this.notificationManager.notify(
            Notifications.ID_NO_INTERNET_CONNECTION,
            NotificationCompat.Builder(
                context,
                Notifications.BASE_CHANNEL_NAME
            ).setSmallIcon(R.drawable.ic_debuggy_notification_24dp)
                .setOngoing(true)
                .setColor(context.getAttr(R.attr.colorSecondary))
                .setContentTitle("Связь с интернетом потеряна")
                .setContentText("Устройство может быть недоступно для отладки")
                .setPriority(-1)
                .setCategory(NotificationCompat.CATEGORY_SERVICE).setContentIntent(
                    PendingIntent.getActivity(
                        context.applicationContext,
                        0,
                        intent,
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
                            PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_UPDATE_CURRENT else
                            PendingIntent.FLAG_UPDATE_CURRENT
                    )
                ).build()
        )
    }

    fun cancelNoInternetConnectionNotification() {
        this.notificationManager.cancel(Notifications.ID_NO_INTERNET_CONNECTION)
    }

    companion object {

        fun createNotificationChannel(channel: String, context: Context) {
            if (Build.VERSION.SDK_INT >= 26) {
                val notificationChannel =
                    NotificationChannel(
                        channel,
                        context.getString(R.string.title_adb_enabled),
                        NotificationManager.IMPORTANCE_DEFAULT
                    )
                (context.getSystemService(NotificationManager::class.java) as NotificationManager).createNotificationChannel(
                    notificationChannel
                )
            }
        }
    }
}