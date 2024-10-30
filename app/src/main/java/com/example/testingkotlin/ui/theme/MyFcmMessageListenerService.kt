package com.example.testingkotlin

import android.app.NotificationChannel
import android.app.NotificationManager
import android.os.Build
import android.os.Bundle
import android.util.Log
import com.clevertap.android.sdk.CleverTapAPI
import com.clevertap.android.sdk.pushnotification.fcm.CTFcmMessageHandler
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

class MyFcmMessageListenerService : FirebaseMessagingService() {

    override fun onMessageReceived(message: RemoteMessage) {
        try {
            if (message.data.isNotEmpty()) {
                val extras = Bundle()
                for ((key, value) in message.data) {
                    extras.putString(key, value)
                }

                val info = CleverTapAPI.getNotificationInfo(extras)

                if (info.fromCleverTap) {
                    CTFcmMessageHandler().createNotification(applicationContext, message)
                } else {
                    // Handle notifications that are not from CleverTap
                }
            }
        } catch (t: Throwable) {
            Log.d("MYFCMLIST", "Error parsing FCM message", t)
        }
    }

    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                CHANNEL_ID, CHANNEL_NAME, NotificationManager.IMPORTANCE_DEFAULT
            ).apply {
                description = CHANNEL_DESC
            }

            val notificationManager = getSystemService(NotificationManager::class.java)
            notificationManager?.createNotificationChannel(channel)
        }
    }

    companion object {
        private const val CHANNEL_ID = "manishTest"
        private const val CHANNEL_NAME = "My Channel"
        private const val CHANNEL_DESC = "Channel Description"
    }
}
