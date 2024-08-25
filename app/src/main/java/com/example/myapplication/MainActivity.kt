package com.example.myapplication

import android.app.Notification
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.annotation.RequiresApi

class MainActivity : AppCompatActivity() {

    private val notificationReceiver = object : BroadcastReceiver() {
        @RequiresApi(Build.VERSION_CODES.TIRAMISU)
        override fun onReceive(context: Context?, intent: Intent?) {
            val notification = intent?.getParcelableExtra<Notification>("notification", Notification::class.java)
            val title = notification?.extras?.getString(Notification.EXTRA_TITLE)
            val text = notification?.extras?.getString(Notification.EXTRA_TEXT)

            Log.d("MainActivity", "Received Notification - Title: $title, Text: $text")
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.AppTheme);
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)  // Ensure this file exists in your layout directory

        Log.d("MainActivity", "Show UI")
        // Register the receiver for handling notifications
        val filter = IntentFilter("android.intent.action.NOTIFICATION_RECEIVED")
        registerReceiver(notificationReceiver, filter)

        Log.d("MainActivity", "Check if notification access is granted")
        // Check if notification access is granted
        if (!isNotificationServiceEnabled()) {
            requestNotificationAccess()
        } else {
            // Notification access already granted, you can proceed
            Log.d("MainActivity", "Notification access is already enabled")
        }
    }

    private fun requestNotificationAccess() {
        // Direct user to the notification access settings
        val intent = Intent(android.provider.Settings.ACTION_NOTIFICATION_LISTENER_SETTINGS)
        startActivity(intent)
    }

    private fun isNotificationServiceEnabled(): Boolean {
        val enabledListeners = android.provider.Settings.Secure.getString(contentResolver, "enabled_notification_listeners")
        val packageName = packageName
        return enabledListeners != null && enabledListeners.contains(packageName)
    }
}