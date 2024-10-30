package com.example.testingkotlin

import android.app.Application
import android.util.Log
import com.clevertap.android.sdk.ActivityLifecycleCallback
import com.clevertap.android.sdk.CleverTapAPI
import com.clevertap.android.sdk.pushnotification.CTPushNotificationListener

class MyApplication : Application(), CTPushNotificationListener {

    override fun onCreate() {
        ActivityLifecycleCallback.register(this)
        super.onCreate()

        val cleverTapAPI = CleverTapAPI.getDefaultInstance(applicationContext)
        cleverTapAPI?.setCTPushNotificationListener(this)
    }

    override fun onNotificationClickedPayloadReceived(payload: HashMap<String, Any>) {
        Log.d("NotificationClick", "Notification clicked in killed state: $payload")
    }
}
