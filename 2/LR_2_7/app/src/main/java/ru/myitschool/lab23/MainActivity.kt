package ru.myitschool.lab23

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.CheckBox
import android.widget.Switch
import android.widget.TextView
import android.widget.ToggleButton
import com.google.android.material.chip.Chip
import org.w3c.dom.Text
import ru.myitschool.lab23.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var chip = findViewById<Chip>(R.id.chip_material)
        var cb = findViewById<CheckBox>(R.id.checkBox)
        var switch = findViewById<Switch>(R.id.switch1)
        var tgbt = findViewById<ToggleButton>(R.id.toggleButton)
        var bt = findViewById<Button>(R.id.button)
        var tw1 = findViewById<TextView>(R.id.textView)
        var tw2 = findViewById<TextView>(R.id.textView2)
        var tw3 = findViewById<TextView>(R.id.textView3)
        var tw4 = findViewById<TextView>(R.id.textView4)

        bt.setOnClickListener{
            if (chip.isChecked){
               tw1.setText("canonicalname1:true")
            }
            else {tw1.setText("canonicalname1:false")}

            if (cb.isChecked){
                tw2.setText("canonicalname2:true")
            }
            else {tw2.setText("canonicalname2:false")}

            if (switch.isChecked){
                tw3.setText("canonicalname3:true")
            }
            else {tw3.setText("canonicalname3:false")}

            if (tgbt.isChecked){
                tw4.setText("canonicalname4:true")
            }
            else {tw4.setText("canonicalname4:false")}
        }


    }
}