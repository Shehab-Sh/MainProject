package com.example.myapplication

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import android.os.Looper
import androidx.work.Worker
import androidx.work.WorkerParameters
import android.os.Handler
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat

class WorkerM(context: Context, workerParams: WorkerParameters) : androidx.work.Worker(context, workerParams) {
        override fun doWork(): Result {
            showNotification("Welcome", "Welcome!")
            return Result.success()
        }

        @SuppressLint("MissingPermission")
        private fun showNotification(title: String, message: String) {
            val notificationId = 1
            val channelId = "welcome_channel"

            // Create a notification channel for Android O and above
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                val channel = NotificationChannel(
                    channelId,
                    "Welcome Notifications",
                    NotificationManager.IMPORTANCE_DEFAULT
                ).apply {
                    description = "Channel for Welcome Notifications"
                }
                val notificationManager: NotificationManager =
                    applicationContext.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
                notificationManager.createNotificationChannel(channel)
            }
            // Create the notification
            val builder = NotificationCompat.Builder(applicationContext, channelId)
                .setSmallIcon(R.drawable.ageicon) // Replace with your own icon
                .setContentTitle(title)
                .setContentText(message)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setAutoCancel(true)

            // Show the notification
            with(NotificationManagerCompat.from(applicationContext)) {
                notify(notificationId, builder.build())
            }
        }
    }
