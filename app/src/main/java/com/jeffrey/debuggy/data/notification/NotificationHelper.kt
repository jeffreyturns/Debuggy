package com.jeffrey.debuggy.data.notification

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.jeffrey.debuggy.R
import com.jeffrey.debuggy.util.system.getAttr

class NotificationHelper(private val context: Context) {

    private val notificationManager = NotificationManagerCompat.from(context)

    fun create(model: BaseNotification): Notification {
        return NotificationCompat.Builder(
            context,
            model.channelId
        ).apply {
            setSmallIcon(R.drawable.ic_debuggy_notification_24dp)
            color = context.getAttr(R.attr.colorPrimaryContainer)
            setContentTitle(model.title)
            setContentText(model.content)
            priority = model.priority

            when (model) {
                is OngoingNotification -> {
                    setOngoing(true)
                    setShowWhen(model.isShowWhen)
                }
                is ExpandableNotification -> {
                    setStyle(NotificationCompat.BigTextStyle().bigText(model.longText))
                }
                else -> {}
            }

            model.actions.forEach { (iconId, title, actionIntent) ->
                addAction(iconId, title, actionIntent)
            }
        }.build()
    }

    fun show(id: Int, notification: BaseNotification) =
        this.notificationManager.notify(id, create(notification))

    fun cancel(id: Int) = this.notificationManager.cancel(id)

    companion object {

        fun createChannels(context: Context) {
            if (Build.VERSION.SDK_INT < Build.VERSION_CODES.O) return

            val notificationManager =
                context.getSystemService(NotificationManager::class.java) as NotificationManager

            listOf(
                NotificationChannel(
                    Notifications.CHANNEL_ENABLE_ADB,
                    context.getString(R.string.title_adb_enabled),
                    NotificationManager.IMPORTANCE_DEFAULT
                )
            ).forEach { notificationManager.createNotificationChannel(it) }
        }
    }
}