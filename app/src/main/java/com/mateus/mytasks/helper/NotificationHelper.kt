package com.mateus.mytasks.helper

import android.Manifest
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.app.PendingIntentCompat
import androidx.core.content.ContextCompat
import com.mateus.mytasks.R
import com.mateus.mytasks.activity.MainActivity

class NotificationHelper(private val context: Context) {

    private val notificationManager = NotificationManagerCompat.from(context)

    companion object {
        const val CHANNEL_ID = "daily_notification"
    }

    init {
        val channelName = ContextCompat.getString(context, R.string.daily_notification)
        val channel = NotificationChannel(CHANNEL_ID, channelName, NotificationManager.IMPORTANCE_DEFAULT).apply {
            description = ContextCompat.getString(context, R.string.daily_notification_on)
        }
        notificationManager.createNotificationChannel(channel)
    }

    fun showNotification(title: String, text: String) {
        val intent = Intent(context, MainActivity::class.java)
        val pendingIntent = PendingIntentCompat.getActivity(context, 0, intent, PendingIntent.FLAG_ONE_SHOT, false)

        val notification = NotificationCompat.Builder(context, CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_notification)
                .setContentTitle(title)
                .setContentText(text)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setAutoCancel(true)
                .setContentIntent(pendingIntent)
                .build()

        if (ContextCompat.checkSelfPermission(
                context, Manifest.permission.POST_NOTIFICATIONS
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            notificationManager.notify(1, notification)
        }
    }
}