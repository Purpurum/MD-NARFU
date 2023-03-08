package com.example.pr4_8

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.SystemClock
import android.util.Log
import android.widget.Button
import android.widget.Toast

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val intentFilter = IntentFilter("com.example.myapp.ACTION_ALARM")
        val receiver = AlarmReceiver()
        registerReceiver(receiver, intentFilter)

        var alarmManager = baseContext.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        var intent = Intent(this, AlarmReceiver::class.java)
        var pendingIntent = PendingIntent.getBroadcast(baseContext, 1, intent, PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_UPDATE_CURRENT)

        val btn_understand = findViewById<Button>(R.id.btn_understand)
        val btn_enough = findViewById<Button>(R.id.btn_enough)

        btn_understand.setOnClickListener{
            alarmManager.setRepeating(AlarmManager.RTC_WAKEUP,SystemClock.elapsedRealtime() + 30000,30000,pendingIntent)
        }

        btn_enough.setOnClickListener {
            if(intent != null){
                alarmManager.cancel(pendingIntent)
            }
        }
    }
}