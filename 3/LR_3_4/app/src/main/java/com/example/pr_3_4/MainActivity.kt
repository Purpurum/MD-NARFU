package com.example.pr_3_4

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
    fun openBrowser(view: View){
        startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("https://www.google.com")))
    }
    fun openMaps(view: View){
        startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("geo:")))
    }
    fun openPhone(view: View){
        startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("tel:")))
    }
    fun inputText(view: View) {
        val text: EditText = findViewById(R.id.Text)
        var flag = false
        var flag2 = false
        for (char in text.text) {
            if ((char in 'a'..'z') or (char in 'A'..'Z')) {
                flag = true
                break
            }
            if (char == '.') {
                flag2 = true
            }
        }
        when {
            flag -> {
                startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("https://${text.text}")))
            }
            flag2 -> {
                startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("geo:${text.text}")))
            }
            else -> {
                startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("tel:${text.text}")))
            }
        }
    }
}