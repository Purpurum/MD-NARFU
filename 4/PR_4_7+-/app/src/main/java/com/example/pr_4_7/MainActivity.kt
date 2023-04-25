package com.example.pr_4_7

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    val broadcastIntent = Intent("ATTENDANCE")
    val presentStudents = mutableListOf<String>()
    var broadcastReceiver = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val checkAttendanceButton = findViewById<Button>(R.id.button)
        checkAttendanceButton.setOnClickListener {
            sendBroadcast(broadcastIntent)
        }
        val broadcastReceiver = object : BroadcastReceiver() {
            override fun onReceive(context: Context?, intent: Intent?) {
                if (intent?.action == "ATTENDANCE") {
                    presentStudents.add("Коля")
                    presentStudents.add("Вася")
                    presentStudents.add("Женя")
                    presentStudents.sort()
                    showPresentStudents()
                }
            }
        }
        val filter = IntentFilter("ATTENDANCE")
        registerReceiver(broadcastReceiver, filter)
    }


    override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver(broadcastReceiver)
    }

    private fun showPresentStudents() {
        val presentStudentsString = presentStudents.joinToString(separator = "\n")
        Toast.makeText(this, presentStudentsString, Toast.LENGTH_LONG).show()
    }
}