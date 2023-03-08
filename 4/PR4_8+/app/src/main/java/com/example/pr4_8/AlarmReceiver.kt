package com.example.pr4_8

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import android.widget.Toast

class AlarmReceiver:BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        val intent = Intent(context, Exercise::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        if (context != null) {
            context.startActivity(intent)
        }
    }
}