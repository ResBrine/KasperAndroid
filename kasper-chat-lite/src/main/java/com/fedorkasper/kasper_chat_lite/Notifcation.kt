package com.fedorkasper.kasper_chat_lite

import android.annotation.SuppressLint
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat

private var NOTIFICATION_ID = 1
private const val CHANNEL_ID = "my_channel_id"
private const val NAME_CHANNEL = "Kasper Chat Lite"
private const val NAME_CHANNEL_DESCRIPTION = "For message Kasper Chat Lite"

@SuppressLint("MissingPermission")
fun createNotification(context: Context, title: String, content: String) {
    createNotificationChannel(context)

    val builder = NotificationCompat.Builder(context, CHANNEL_ID)
        .setSmallIcon(android.R.drawable.ic_dialog_info)
        .setContentTitle(title)
        .setContentText(content)
        .setPriority(NotificationCompat.PRIORITY_DEFAULT)

    with(NotificationManagerCompat.from(context)) {
        notify(NOTIFICATION_ID++, builder.build())
    }
}
private fun createNotificationChannel(context: Context) {
    val importance = NotificationManager.IMPORTANCE_DEFAULT
    val channel = NotificationChannel(CHANNEL_ID, NAME_CHANNEL, importance).apply {
        description = NAME_CHANNEL_DESCRIPTION
    }
    val notificationManager: NotificationManager =
        context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
    notificationManager.createNotificationChannel(channel)
}

