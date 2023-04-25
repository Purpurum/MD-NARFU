package com.example.pr_4_10

import android.annotation.SuppressLint
import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*

class MainActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val spinner = findViewById<Spinner>(R.id.spinner)
        val items = resources.getStringArray(R.array.type_sensors)
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, items)
        spinner.adapter = adapter
        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View?,
                position: Int,
                id: Long
            ) {
                if (spinner.selectedItem == "Датчики окружающей среды") {
                    val sensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager
                    val magneticFieldSensor =
                        sensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD)
                    val magneticFieldSensorName =
                        if (magneticFieldSensor != null) magneticFieldSensor.name else "null"
                    val lightSensor = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT)
                    val lightSensorName = if (lightSensor != null) lightSensor.name else "null"
                    val pressureSensor = sensorManager.getDefaultSensor(Sensor.TYPE_PRESSURE)
                    val pressureSensorName =
                        if (pressureSensor != null) pressureSensor.name else "null"
                    val humiditySensor =
                        sensorManager.getDefaultSensor(Sensor.TYPE_RELATIVE_HUMIDITY)
                    val humiditySensorName =
                        if (humiditySensor != null) humiditySensor.name else "null"
                    val temperatureSensor =
                        sensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD)
                    val temperatureSensorName =
                        if (temperatureSensor != null) temperatureSensor.name else "null"
                    var arr = arrayOf(
                        "Магнитное поле - ${magneticFieldSensorName}",
                        "Освещённость - ${lightSensorName}",
                        "Давление - ${pressureSensorName}",
                        "Относительная влажность - ${humiditySensorName}",
                        "Температура - ${temperatureSensorName}")
                    val sensorListText = arr.joinToString(separator = "\n")
                    var label = findViewById<TextView>(R.id.tw)
                    label.text = sensorListText
                } else if (spinner.selectedItem == "Датчики положения") {
                    val sensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager
                    val accelerationSensor =
                        sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)
                    val accelerationSensorName =
                        if (accelerationSensor != null) accelerationSensor.name else "null"
                    val gyroSensor = sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE)
                    val gyroSensorName = if (gyroSensor != null) gyroSensor.name else "null"
                    val proximitySensor = sensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY)
                    val proximitySensorName =
                        if (proximitySensor != null) proximitySensor.name else "null"
                    val gravitySensor = sensorManager.getDefaultSensor(Sensor.TYPE_GRAVITY)
                    val gravitySensorName =
                        if (gravitySensor != null) gravitySensor.name else "null"
                    val accLimitedSensor =
                        sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER_LIMITED_AXES)
                    val accLimitedSensorName =
                        if (accLimitedSensor != null) accLimitedSensor.name else "null"
                    val rotGeoSensor =
                        sensorManager.getDefaultSensor(Sensor.TYPE_GEOMAGNETIC_ROTATION_VECTOR)
                    val rotGeoSensorName = if (rotGeoSensor != null) rotGeoSensor.name else "null"
                    val rotVectSensor = sensorManager.getDefaultSensor(Sensor.TYPE_ROTATION_VECTOR)
                    val rotVectSensorName =
                        if (rotVectSensor != null) rotVectSensor.name else "null"
                    val gyroUncSensor =
                        sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE_UNCALIBRATED)
                    val gyroUncSensorName =
                        if (gyroUncSensor != null) gyroUncSensor.name else "null"
                    val sigMotSensor =
                        sensorManager.getDefaultSensor(Sensor.TYPE_SIGNIFICANT_MOTION)
                    val sigMotSensorName = if (sigMotSensor != null) sigMotSensor.name else "null"
                    val stepSensor = sensorManager.getDefaultSensor(Sensor.TYPE_STEP_DETECTOR)
                    val stepSensorName = if (stepSensor != null) stepSensor.name else "null"
                    val stepCountSensor = sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER)
                    val stepCountSensorName =
                        if (stepCountSensor != null) stepCountSensor.name else "null"
                    val motionSensor = sensorManager.getDefaultSensor(Sensor.TYPE_MOTION_DETECT)
                    val motionSensorName = if (motionSensor != null) motionSensor.name else "null"
                    var arr = arrayOf(
                        "Акселерометр - ${accelerationSensorName}",
                        "Гироскоп - ${gyroSensorName}",
                        "Приближение - ${proximitySensorName}",
                        "Гравитация - ${gravitySensorName}",
                        "Ускорение прямолинейного движения - ${accLimitedSensorName}",
                        "Вектор вращения с отклонениями по осям - ${rotGeoSensorName}",
                        "Вектор вращения без геомагнитного влияния - ${rotVectSensorName}",
                        "Некалиброванный гироскоп - ${gyroUncSensorName}",
                        "Значительные колебания - ${sigMotSensorName}",
                        "Одиночный шаг - ${stepSensorName}",
                        "Количество шагов - ${stepCountSensorName}",
                        "Движение - ${motionSensorName}"
                    )
                    val sensorListText = arr.joinToString(separator = "\n")
                    var label = findViewById<TextView>(R.id.tw)
                    label.text = sensorListText
                } else if (spinner.selectedItem == "Датчики состояния человека") {
                    val sensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager
                    val heartBeatSensor = sensorManager.getDefaultSensor(Sensor.TYPE_HEART_BEAT)
                    val heartBeatSensorName =
                        if (heartBeatSensor != null) heartBeatSensor.name else "null"
                    val heartRateSensor = sensorManager.getDefaultSensor(Sensor.TYPE_HEART_RATE)
                    val heartRateSensorName =
                        if (heartRateSensor != null) heartRateSensor.name else "null"
                    val lowLatencyOffBodySensor =
                        sensorManager.getDefaultSensor(Sensor.TYPE_LOW_LATENCY_OFFBODY_DETECT)
                    val lowLatencyOffBodySensorName =
                        if (lowLatencyOffBodySensor != null) lowLatencyOffBodySensor.name else "null"
                    var arr = arrayOf(
                        "Мониторинг пульса - ${heartBeatSensorName}",
                        "ЧСС - ${heartRateSensorName}",
                        "Удаление устройства от человека - ${lowLatencyOffBodySensorName}")
                    val sensorListText = arr.joinToString(separator = "\n")
                    var label = findViewById<TextView>(R.id.tw)
                    label.text = sensorListText
                }
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                TODO("Not yet implemented")
            }
        }
    }
}