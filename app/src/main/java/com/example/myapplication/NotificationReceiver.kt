package com.example.myapplication

import android.app.Notification
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log

class NotificationReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        // Handle the notification event here
        val notification = intent.getParcelableExtra<Notification>("notification")
        val notificationTitle = notification?.extras?.getString(Notification.EXTRA_TITLE)
        val notificationText = notification?.extras?.getString(Notification.EXTRA_TEXT)

        // Do something with the notification information
        Log.d("NotificationReceiver", "Title: $notificationTitle, Text: $notificationText")
    }
}