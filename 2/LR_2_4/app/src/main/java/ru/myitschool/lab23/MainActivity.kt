package ru.myitschool.lab23

import android.os.Bundle
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.doAfterTextChanged
import ru.myitschool.lab23.databinding.ActivityMainBinding
import kotlin.math.pow


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private var lower = 0
    private var upper = 0

    var et_inch: EditText = findViewById(R.id.et_inch)
    var et_yard: EditText = findViewById(R.id.et_yard)
    var et_feet: EditText = findViewById(R.id.et_foot)
    var et_mile: EditText = findViewById(R.id.et_mile)
    var et_yottametre: EditText = findViewById(R.id.et_yottametre)
    var et_zettametre: EditText = findViewById(R.id.et_zettametre)
    var et_exametre: EditText = findViewById(R.id.et_exametre)
    var et_petametre: EditText = findViewById(R.id.et_petametre)
    var et_terametre: EditText = findViewById(R.id.et_terametre)
    var et_gigametre: EditText = findViewById(R.id.et_gigametre)
    var et_megametre: EditText = findViewById(R.id.et_megametre)
    var et_kilometre: EditText = findViewById(R.id.et_kilometre)
    var et_hectometre: EditText = findViewById(R.id.et_hectometre)
    var et_decametre: EditText = findViewById(R.id.et_decametre)
    var et_metre: EditText = findViewById(R.id.et_metre)
    var et_decimetre: EditText = findViewById(R.id.et_decimetre)
    var et_centimetre: EditText = findViewById(R.id.et_centimetre)
    var et_millimetre: EditText = findViewById(R.id.et_millimetre)
    var et_micrometre: EditText = findViewById(R.id.et_micrometre)
    var et_nanometre: EditText = findViewById(R.id.et_nanometre)
    var et_picometre: EditText = findViewById(R.id.et_picometre)
    var et_femtometre: EditText = findViewById(R.id.et_femtometre)
    var et_attometre: EditText = findViewById(R.id.et_attometre)
    var et_zeptometre: EditText = findViewById(R.id.et_zeptometre)
    var et_yoctometre: EditText = findViewById(R.id.et_yoctometre)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(R.layout.activity_main)

        lower =
            if (intent.extras?.get("lower") != null) intent.extras?.get("lower") as Int else 0
        upper =
            if (intent.extras?.get("upper") != null) intent.extras?.get("upper") as Int else 1



            et_metre.doAfterTextChanged {
                var mt : Double = et_metre.text.toString().toDouble()
                et_inch.setText((mt*39.3701).toString())
                et_yard.setText((mt*1.09361).toString())
                et_feet.setText((mt*3.28084).toString())
                et_mile.setText((mt*0.000621371).toString())
                et_yottametre.setText((mt*10.0.pow(-24)).toString())
                et_zettametre.setText((mt*10.0.pow(-21)).toString())
                et_exametre.setText((mt*10.0.pow(-18)).toString())
                et_petametre.setText((mt*10.0.pow(-15)).toString())
                et_terametre.setText((mt*10.0.pow(-12)).toString())
                et_gigametre.setText((mt*10.0.pow(-9)).toString())
                et_megametre.setText((mt*10.0.pow(-6)).toString())
                et_kilometre.setText((mt*10.0.pow(-3)).toString())
                et_hectometre.setText((mt*10.0.pow(-2)).toString())
                et_decametre.setText((mt*10.0.pow(-1)).toString())
                et_decimetre.setText((mt*10.0.pow(1)).toString())
                et_centimetre.setText((mt*10.0.pow(2)).toString())
                et_millimetre.setText((mt*10.0.pow(3)).toString())
                et_micrometre.setText((mt*10.0.pow(6)).toString())
                et_nanometre.setText((mt*10.0.pow(9)).toString())
                et_picometre.setText((mt*10.0.pow(12)).toString())
                et_femtometre.setText((mt*10.0.pow(15)).toString())
                et_attometre.setText((mt*10.0.pow(18)).toString())
                et_zeptometre.setText((mt*10.0.pow(21)).toString())
                et_yoctometre.setText((mt*10.0.pow(24)).toString())
            }

    }
}

