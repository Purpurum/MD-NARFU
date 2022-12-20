package ru.myitschool.lab23

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val viewModel: MyViewModel = ViewModelProvider(this).get(MyViewModel::class.java)

        var bt: Button = findViewById(R.id.get_random_num)
        var tbmu : EditText = findViewById(R.id.mean_val)
        var tbsig : EditText = findViewById(R.id.variance_value)
        var txt : TextView = findViewById(R.id.random_number_result)
        txt.setText(viewModel.result)
        bt.setOnClickListener{
            viewModel.buildstring(tbmu.text.toString(), tbsig.text.toString())
            txt.setText(viewModel.result)
        }

    }
}