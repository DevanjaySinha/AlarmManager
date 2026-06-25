package com.example.alarmmanager

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            ReminderScreen()
        }
    }

    @Composable
    fun ReminderScreen() {

        var message by remember { mutableStateOf("") }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(20.dp),
            verticalArrangement = Arrangement.Center
        ) {

            Text(
                text = "Reminder App",
                style = MaterialTheme.typography.headlineMedium
            )

            Spacer(modifier = Modifier.height(20.dp))

            OutlinedTextField(
                value = message,
                onValueChange = { message = it },
                label = { Text("Reminder Message") }
            )

            Spacer(modifier = Modifier.height(20.dp))

            Button(
                onClick = {

                    val intent =
                        Intent(this@MainActivity, AlarmReceiver::class.java)

                    intent.putExtra("msg", message)

                    val pendingIntent =
                        PendingIntent.getBroadcast(
                            this@MainActivity,
                            0,
                            intent,
                            PendingIntent.FLAG_IMMUTABLE
                        )

                    val alarmManager =
                        getSystemService(ALARM_SERVICE) as AlarmManager

                    alarmManager.set(
                        AlarmManager.RTC_WAKEUP,
                        System.currentTimeMillis() + 10000,
                        pendingIntent
                    )

                    Toast.makeText(
                        this@MainActivity,
                        "Reminder set for 10 seconds",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            ) {
                Text("Set Reminder")
            }
        }
    }
}