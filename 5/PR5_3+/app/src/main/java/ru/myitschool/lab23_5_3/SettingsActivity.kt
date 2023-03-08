package ru.myitschool.PR5_3

import android.content.Context.*
import android.content.SharedPreferences
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat.getSystemService
import androidx.preference.PreferenceFragmentCompat
import androidx.preference.PreferenceManager
import android.content.Context
import ru.myitschool.lab23.R

var pressureVar = ""
var pressureNum = 0.0

fun getPressure(context: Context): Double? {
    val sensorManager = context.getSystemService(Context.SENSOR_SERVICE) as SensorManager
    val pressureSensor = sensorManager.getDefaultSensor(Sensor.TYPE_PRESSURE)
    if (pressureSensor == null) { return 25.0 }
    val sensorEventListener = object : SensorEventListener {
        var pressure: Double? = 25.0

        override fun onSensorChanged(event: SensorEvent) {
            if (event.sensor.type == Sensor.TYPE_PRESSURE) {
                pressure = event.values[0].toDouble()
                sensorManager.unregisterListener(this)
            }
        }

        override fun onAccuracyChanged(sensor: Sensor, accuracy: Int) {
            // пустышка, без неё оно жалуется
        }
    }

    sensorManager.registerListener(sensorEventListener, pressureSensor, SensorManager.SENSOR_DELAY_GAME
    )

    Thread.sleep(1000)
    return sensorEventListener.pressure
}


class SettingsActivity : AppCompatActivity(), SharedPreferences.OnSharedPreferenceChangeListener {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.settings_activity)
        if (savedInstanceState == null) {
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.settings, SettingsFragment())
                .commit()
        }
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this)
        sharedPreferences.registerOnSharedPreferenceChangeListener(this)
    }

    class SettingsFragment : PreferenceFragmentCompat() {
        override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
            setPreferencesFromResource(R.xml.root_preferences, rootKey)
        }
    }

    override fun onSharedPreferenceChanged(sharedPreferences: SharedPreferences?, key: String?) {
        when (key) {
            "pressure_dropdown" ->{
                val pressure_dropdown = sharedPreferences!!.getString("pressure_dropdown", "")
                if(pressure_dropdown == "гПа"){
                    pressureVar = "гПа"
                    pressureNum = getPressure(this)?.times(1.00)!!
                }
                if(pressure_dropdown == "мм рт. ст."){
                    pressureVar = "мм рт. ст."
                    pressureNum = getPressure(this)?.times(0.750063755419211)!!
                }
            }
        }
    }

    override fun onDestroy() {
        val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this)
        sharedPreferences.unregisterOnSharedPreferenceChangeListener(this)
        super.onDestroy()
    }

    override fun onResume() {
        super.onResume()
        PreferenceManager.getDefaultSharedPreferences(this).registerOnSharedPreferenceChangeListener(this)
    }

    override fun onPause() {
        super.onPause()
        PreferenceManager.getDefaultSharedPreferences(this).unregisterOnSharedPreferenceChangeListener(this)
    }
}