package com.example.pr6_2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import java.net.HttpURLConnection
import java.net.URL
import javax.net.ssl.HttpsURLConnection

class MainActivity : AppCompatActivity() {
    lateinit var et_url: EditText
    lateinit var text: EditText
    lateinit var send: Button
    lateinit var result: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        et_url = findViewById(R.id.url)
        text = findViewById(R.id.text_to_send)
        send = findViewById(R.id.send)
        result =findViewById(R.id.result)
        send.setOnClickListener(View.OnClickListener {sendText()})
    }

    private fun sendText() {
        val  thread:Thread = object:Thread() {
            override fun run() {
                val url: URL = URL(et_url.text.toString() + "?text=" + text.text)
                val urlConnection:HttpURLConnection = url.openConnection() as HttpURLConnection
                try {
                    val data = urlConnection.inputStream.bufferedReader().readLine()
                    result.text = data
                } finally {
                    urlConnection.disconnect()
                }
            }
        }
        thread.start()
    }
}