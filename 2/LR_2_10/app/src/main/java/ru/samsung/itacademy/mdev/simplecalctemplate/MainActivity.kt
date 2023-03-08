package ru.samsung.itacademy.mdev.simplecalctemplate

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.lang.Exception


class MainActivity : AppCompatActivity(){


    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val etNum1: EditText = findViewById(R.id.etNum1)
        val etNum2: EditText = findViewById(R.id.etNum2)

        val btnAdd: Button = findViewById(R.id.btnAdd)
        val btnSub: Button = findViewById(R.id.btnSub)
        val btnMult: Button = findViewById(R.id.btnMult)
        val btnDiv: Button = findViewById(R.id.btnDiv)

        val tvResult: TextView = findViewById(R.id.tvResult)

        var calculator = Calculator()

        btnAdd.setOnClickListener {
            try {
                val a: Int = etNum1.text.toString().toInt()
                var b: Int = etNum2.text.toString().toInt()
                tvResult.text = calculator.add(a, b).toString()
            }catch (e: Exception){
                e.printStackTrace()
            }

        }

        btnSub.setOnClickListener {
            try {
                val a: Int = etNum1.text.toString().toInt()
                var b: Int = etNum2.text.toString().toInt()
                tvResult.text = calculator.subtract(a, b).toString()
            }catch (e: Exception){
                e.printStackTrace()
            }
        }

        btnDiv.setOnClickListener {
            try {
                val a: Int = etNum1.text.toString().toInt()
                var b: Int = etNum2.text.toString().toInt()
                tvResult.text = calculator.divide(a, b).toString()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }


        btnMult.setOnClickListener {
            try {
                val a: Int = etNum1.text.toString().toInt()
                var b: Int = etNum2.text.toString().toInt()
                tvResult.text = calculator.multiply(a, b).toString()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }

    }

}