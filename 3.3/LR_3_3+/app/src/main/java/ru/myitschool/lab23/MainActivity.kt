package ru.myitschool.lab23

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.widget.Toast
import ru.myitschool.lab23.databinding.ActivityMainBinding
import kotlin.random.Random



class MainActivity : AppCompatActivity() {
    var num1 = 0
    var num2 = 0
    var num3 = 0
    private var binding: ActivityMainBinding? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding!!.root)

        binding!!.content.getRandomNums.setOnClickListener{
            num1 = binding!!.content.shapeParam.text.toString().toInt()
            num2 = binding!!.content.sizeParam.text.toString().toInt()
            num3 = binding!!.content.rateParam.text.toString().toInt()

            val intent = Intent(this, SecondActivity::class.java)
            intent.putExtra("num1", num1)
            intent.putExtra("num2", num2)
            intent.putExtra("num3", num3)

            startActivity(intent)
        }


        //binding!!.content.tv.text = "ass"
    }
}