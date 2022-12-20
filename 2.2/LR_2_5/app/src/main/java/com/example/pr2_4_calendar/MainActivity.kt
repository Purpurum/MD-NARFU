package com.example.pr2_4_calendar

import android.os.Bundle
import android.view.ViewGroup
import android.widget.*
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var CalView: CalendarView = findViewById(R.id.calendarView)
        var dateedit: EditText = findViewById(R.id.vdata)
        var bt : Button = findViewById(R.id.button)
        var dtexted : EditText = findViewById(R.id.vdata)
        var ttexted : EditText = findViewById(R.id.vtime)
        var ztexted : EditText = findViewById(R.id.post)
        var ntexted : EditText = findViewById(R.id.vtitle)
        var rbg : RadioButton = findViewById(R.id.rbgood)
        var rbb : RadioButton = findViewById(R.id.rbbad)
        var pict : ImageView = findViewById(R.id.imageView)


        CalView.setOnDateChangeListener { view, year, month, dayOfMonth -> val Date = (dayOfMonth.toString() + "." + (month + 1) + "." + year)
            dateedit.setText(Date)
        }
        ztexted.setOnClickListener { ztexted.text.clear() }
        bt.setOnClickListener {
            var totext: String = ("Записано!\nСобытие: " + ntexted.text + "\nДата: " + dtexted.text + "\nВремя: " + ttexted.text + "\nЗаметки: " + ztexted.text)
            val toast = Toast.makeText(this, "Text" , Toast.LENGTH_SHORT)
//the default toast view group is a relativelayout
            val toastLayout = toast.getView() as ViewGroup
            val toastTV = toastLayout.getChildAt(0) as TextView
            toastTV.setTextSize(30f)
            toast.show()
            dtexted.text.clear()
            ttexted.text.clear()
            ztexted.setText("Заметки")
            ntexted.text.clear()
            dateedit.text.clear()

        }
        rbg.setOnClickListener{
            pict.setImageResource(R.drawable.cheerful)
        }
        rbb.setOnClickListener{
            pict.setImageResource(R.drawable.cry)
        }
    }
}