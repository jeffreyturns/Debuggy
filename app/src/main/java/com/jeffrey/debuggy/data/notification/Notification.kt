package com.jeffrey.debuggy.data.notification

import android.app.PendingIntent

sealed class BaseNotification(
    val channelId: String,
    val title: String,
    val content: String,
    val priority: Int,
    val actions: List<NotificationAction> = mutableListOf()
)

class OngoingNotification(
    channelId: String,
    title: String,
    content: String,
    priority: Int,
    val isShowWhen: Boolean,
    actions: List<NotificationAction> = listOf(),
) : BaseNotification(channelId, title, content, priority, actions = actions)

class BasicNotification(
    channelId: String,
    title: String,
    content: String,
    priority: Int,
    actions: List<NotificationAction> = listOf(),
) : BaseNotification(channelId, title, content, priority, actions = actions)

class ExpandableNotification(
    channelId: String,
    title: String,
    content: String,
    priority: Int,
    val longText: String
) : BaseNotification(channelId, title, content, priority)

data class NotificationAction(
    val iconId: Int,
    val title: String,
    val actionIntent: PendingIntent
)