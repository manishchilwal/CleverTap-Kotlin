package com.example.testingkotlin

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.clevertap.android.sdk.CleverTapAPI
import com.example.testingkotlin.ui.theme.TestingKotlinTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TestingKotlinTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }

        CleverTapAPI.setDebugLevel(CleverTapAPI.LogLevel.VERBOSE)
        var cleverTapDefaultInstance: CleverTapAPI? = null
        cleverTapDefaultInstance = CleverTapAPI.getDefaultInstance(applicationContext)
    }

    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            var cleverTapDefaultInstance: CleverTapAPI? = null
            cleverTapDefaultInstance = CleverTapAPI.getDefaultInstance(applicationContext)
            cleverTapDefaultInstance?.pushNotificationClickedEvent(intent?.extras)
            Log.d("NotificationClick", "Notification clicked in foreground or background state")
        }
    }
}



@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    TestingKotlinTheme {
        Greeting("Android")
    }
}