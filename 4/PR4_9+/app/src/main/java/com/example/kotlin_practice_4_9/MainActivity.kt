package com.example.kotlin_practice_4_9

import android.content.pm.ActivityInfo
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.work.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LOCKED

        val btn_startprocess = findViewById<Button>(R.id.btn_startprocess)
        val btn_clicker = findViewById<Button>(R.id.btn_clicker)

        var count: Int = 0
        var data: Data

        val process1 = OneTimeWorkRequest.Builder(TextWorker::class.java).build()
        val process2 = OneTimeWorkRequest.Builder(LongWorker::class.java).build()

        btn_startprocess.setOnClickListener{
            WorkManager.getInstance(this)
                .beginWith(process1)
                .then(process2)
                .enqueue()
        }

        btn_clicker.setOnClickListener{
            count++
            data = workDataOf("click" to count)
            btn_clicker.text = "набираем очки! ($count)"
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        WorkManager.getInstance(baseContext).cancelAllWork()
    }
}









