package com.example.pr4_8

import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class Exercise : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_exercise)

        val num: Int = (0..5).random()
        val pictures = arrayListOf(
            R.drawable.kulis,
            R.drawable.moth,
            R.drawable.artist,
            R.drawable.curtain,
            R.drawable.shooter,
            R.drawable.viy
        )
        val exText = resources.getStringArray(R.array.exercises)

        val btn_ok = findViewById<Button>(R.id.btn_ok)
        val tv_exercText = findViewById<TextView>(R.id.tv_exercText)
        val iv_pict = findViewById<ImageView>(R.id.iv_pict)

        iv_pict.setImageResource(pictures[num])
        tv_exercText.text = exText[num]

        btn_ok.setOnClickListener{
            finish()
        }
    }
}