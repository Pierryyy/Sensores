package br.com.qi.sensores

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class TemperaturaActivity : AppCompatActivity(), SensorEventListener {

    private lateinit var sensorManager: SensorManager
    private lateinit var ambientTemperatureSensor: Sensor

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_temperatura)

        sensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager
        ambientTemperatureSensor = sensorManager.getDefaultSensor(Sensor.TYPE_AMBIENT_TEMPERATURE)
    }

    override fun onResume() {
        super.onResume()
        sensorManager.registerListener(this, ambientTemperatureSensor,
            SensorManager.SENSOR_DELAY_NORMAL )
    }

    override fun onPause() {
        super.onPause()
        sensorManager.unregisterListener(this)
    }

    override fun onAccuracyChanged(sensor: Sensor, accuracy: Int) {// passa a precisão

    }

    override fun onSensorChanged(event: SensorEvent?) {
        val centimetros = event?.values!![0]
        val textView = findViewById<TextView>(R.id.textView)
        textView.text = "$centimetros °C"

    }
}