package com.example.pr_4_11

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Bundle
import android.widget.RadioButton
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.*
import kotlin.concurrent.thread

class MainActivity : AppCompatActivity(), SensorEventListener {

    lateinit var job: Job

    lateinit var sensorManager: SensorManager
    var lightSensor: Sensor? = null
    var rotationVectorSensor: Sensor? = null
    var accelerometer: Sensor? = null
    var gravity: Sensor? = null

    //свет
    var lightLevel: Float? = null
    //Вращательные движения
    var rotationMatrix = FloatArray(9) // 3x3 матрица вращения
    var orientation = FloatArray(3)
    var scalarAngle: Float? = null
    //Акселлерометр
    var accels = FloatArray(3)
    //Гравитометр
    var gravs = FloatArray(3)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        sensorManager = getSystemService(SENSOR_SERVICE) as SensorManager
        lightSensor = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT)
        rotationVectorSensor = sensorManager.getDefaultSensor(Sensor.TYPE_ROTATION_VECTOR)
        accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)
        gravity = sensorManager.getDefaultSensor(Sensor.TYPE_GRAVITY)


        var rb1 = findViewById<RadioButton>(R.id.radioButton)
        var rb2 = findViewById<RadioButton>(R.id.radioButton2)
        var rb3 = findViewById<RadioButton>(R.id.radioButton3)
        var rb4 = findViewById<RadioButton>(R.id.radioButton4)
        var lbl = findViewById<TextView>(R.id.textView)

        rb1.setOnClickListener {
            if (::job.isInitialized) job.cancel()

            job = CoroutineScope(Dispatchers.Default).launch {
                while (true) {
                    withContext(Dispatchers.Main) {
                        lbl.text = "Уровень освещённости: $lightLevel"
                    }
                    delay(100)
                }
            }
        }
        rb2.setOnClickListener {
            if (::job.isInitialized){ job.cancel() }
            job = CoroutineScope(Dispatchers.Default).launch {
                while (true) {
                    withContext(Dispatchers.Main) {
                        lbl.text = "OX: ${orientation[0]}\nOY: ${orientation[1]}\nOZ: ${orientation[2]}\n" +
                                "Скалярная мера угла поворота: $scalarAngle"
                    }
                    delay(100)
                }
            }
        }
        rb3.setOnClickListener {
            if (::job.isInitialized){ job.cancel() }
            job = CoroutineScope(Dispatchers.Default).launch {
                while (true) {
                    withContext(Dispatchers.Main) {
                        lbl.text = "OX: ${accels[0]}\nOY: ${accels[1]}\nOZ: ${accels[2]}"
                    }
                    delay(100)
                }
            }
        }
        rb4.setOnClickListener {
            if (::job.isInitialized){ job.cancel() }
            job = CoroutineScope(Dispatchers.Default).launch {
                while (true) {
                    withContext(Dispatchers.Main) {
                        lbl.text = "OX: ${gravs[0]}\nOY: ${gravs[1]}\nOZ: ${gravs[2]}"
                    }
                    delay(100)
                }
            }
        }
    }
    override fun onResume() {
        super.onResume()
        sensorManager.registerListener(this, lightSensor, SensorManager.SENSOR_DELAY_NORMAL)
        sensorManager.registerListener(this, rotationVectorSensor, SensorManager.SENSOR_DELAY_NORMAL)
        sensorManager.registerListener(this, accelerometer, SensorManager.SENSOR_DELAY_NORMAL)
        sensorManager.registerListener(this, gravity, SensorManager.SENSOR_DELAY_NORMAL)
    }

    override fun onPause() {
        super.onPause()
        sensorManager.unregisterListener(this)
    }

    override fun onDestroy() {
        super.onDestroy()
        job.cancel()
    }

    override fun onSensorChanged(event: SensorEvent?) {
        if (event?.sensor?.type == Sensor.TYPE_LIGHT) {
            lightLevel = event.values[0]
        }
        if (event?.sensor?.type == Sensor.TYPE_ROTATION_VECTOR) {
            SensorManager.getRotationMatrixFromVector(rotationMatrix, event.values)
            SensorManager.getOrientation(rotationMatrix, orientation)
            scalarAngle = (orientation[0] * 180 / Math.PI).toFloat()
        }
        if (event?.sensor?.type == Sensor.TYPE_ACCELEROMETER) {
            accels = event.values
        }
        if (event?.sensor?.type == Sensor.TYPE_GRAVITY) {
            gravs = event.values
        }
    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {
        //абоба
    }
}