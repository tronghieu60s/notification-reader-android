package com.example.myapplication

import android.app.Notification
import android.content.Intent
import android.service.notification.NotificationListenerService
import android.service.notification.StatusBarNotification
import android.util.Log
import androidx.annotation.RequiresPermission
import android.Manifest

class NotificationListener : NotificationListenerService() {
    @RequiresPermission(Manifest.permission.BIND_NOTIFICATION_LISTENER_SERVICE)
    override fun onNotificationPosted(sbn: StatusBarNotification) {
        super.onNotificationPosted(sbn)
        val notification = sbn.notification
        val title = notification.extras.getString(Notification.EXTRA_TITLE)
        val text = notification.extras.getString(Notification.EXTRA_TEXT)

        // Broadcast the notification details
        val intent = Intent("android.intent.action.NOTIFICATION_RECEIVED")
        intent.putExtra("notification", notification)
        sendBroadcast(intent)

        Log.d("NotificationListener", "Title: $title, Text: $text")
    }

    override fun onNotificationRemoved(sbn: StatusBarNotification) {
        super.onNotificationRemoved(sbn)
        // Handle notification removal if needed
    }
}