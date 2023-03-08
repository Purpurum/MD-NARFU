package ru.myitschool.lab23

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var tb1: EditText = findViewById(R.id.side_a)
        var tb2: EditText = findViewById(R.id.side_b)
        var tb3: EditText = findViewById(R.id.side_c)
        var spinner: Spinner = findViewById(R.id.spinner)
        var btn: Button = findViewById(R.id.calculate)
        var res: TextView = findViewById(R.id.solution)
        var resu: String = ""




        btn.setOnClickListener{
        var n1: Double = tb1.text.toString().toDouble()
        var n2: Double = tb2.text.toString().toDouble()
        var n3: Double = tb3.text.toString().toDouble()
            if (n1 < 1000000000000 && n2 < 1000000000000 && n2 < 1000000000000) {
                when (spinner.selectedItem) {
                    "Сумма длин сторон" -> resu = (n1 * 4 + n2 * 4 + n3 * 4).toString()
                    "Длина диагонали" -> resu = (n1 * n1 + n2 * n2 + n3 * n3).toString()
                    "Площадь полной поверхности" -> resu =
                        (2 * (n1 * n2 + n2 * n3 + n3 * n1)).toString()
                    "Объём параллелепипеда" -> resu = (n1 * n2 * n3).toString()
                }
                res.setText(resu)
            }
            else {
                Toast.makeText(this, "Числа слишком большие!", Toast.LENGTH_SHORT).show()

            }
        }


    }
}


