package com.example.alarmmanager

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent

class AlarmReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {

        val msg = intent.getStringExtra("msg")

        val i = Intent(context, ReminderActivity::class.java)

        i.putExtra("msg", msg)
        i.flags = Intent.FLAG_ACTIVITY_NEW_TASK

        context.startActivity(i)
    }
}