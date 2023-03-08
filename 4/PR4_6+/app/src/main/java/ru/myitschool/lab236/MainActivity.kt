package ru.myitschool.PR4_6

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Build
import android.os.Bundle
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import ru.myitschool.lab23.R

class MainActivity : AppCompatActivity() {

    lateinit var receiver: BroadcastReceiver

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        makeBroadcastReceiver()
    }

    override fun onDestroy() {
        unregisterReceiver(receiver)
        super.onDestroy()
    }

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    fun makeBroadcastReceiver(){
        val filter = IntentFilter()
        val tv_status_text = findViewById<TextView>(R.id.status_text)

        filter.addAction(Intent.ACTION_AIRPLANE_MODE_CHANGED)
        filter.addAction(Intent.ACTION_APPLICATION_RESTRICTIONS_CHANGED)
        filter.addAction(Intent.ACTION_APPLICATION_LOCALE_CHANGED)
        filter.addAction(Intent.ACTION_ASSIST)
        filter.addAction(Intent.ACTION_BATTERY_CHANGED)
        filter.addAction(Intent.ACTION_BATTERY_LOW)
        filter.addAction(Intent.ACTION_BATTERY_OKAY)
        filter.addAction(Intent.ACTION_CALL)
        filter.addAction(Intent.ACTION_DATE_CHANGED)
        filter.addAction(Intent.ACTION_DEFAULT)
        filter.addAction(Intent.ACTION_HEADSET_PLUG)
        filter.addAction(Intent.ACTION_LOCALE_CHANGED)
        filter.addAction(Intent.ACTION_TIME_TICK)

        receiver = object : BroadcastReceiver() {
            override fun onReceive(context: Context?, intent: Intent?) {
                val strs = intent?.action?.split("action.")?.toTypedArray()
                val action = "ACTION_${strs!![1]}"
                tv_status_text.text = action
            }
        }
        registerReceiver(receiver, filter)
    }
}