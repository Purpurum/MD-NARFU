package ru.myitschool.PR5_3

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.preference.PreferenceManager
import ru.myitschool.lab23.R
import ru.myitschool.PR5_3.pressureNum
import ru.myitschool.PR5_3.pressureVar

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val tv_pressure_unit = findViewById<TextView>(R.id.tv_pressure_unit)
        val tv_pressure = findViewById<TextView>(R.id.tv_pressure)
        if(pressureVar == "гПа"){
            tv_pressure_unit.text = "гПа"
            tv_pressure.text = pressureNum.toString()
        }
        if(pressureVar == "мм рт. ст."){
            tv_pressure_unit.text = "мм рт. ст."
            tv_pressure.text = pressureNum.toString()
        }
        Toast.makeText(applicationContext, "main создался", Toast.LENGTH_SHORT).show()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_settings -> {
                val intent = Intent(this, SettingsActivity::class.java)
                startActivity(intent)

                return true
            }
        }
        return super.onOptionsItemSelected(item)

    }
}