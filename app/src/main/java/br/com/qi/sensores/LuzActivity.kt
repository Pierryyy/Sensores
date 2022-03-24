package br.com.qi.sensores

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class LuzActivity: AppCompatActivity() {
    var mLuz : Sensor? = null
    var mSensorManager : SensorManager? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_luz)

        mSensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager
        mLuz = mSensorManager?.getDefaultSensor(Sensor.TYPE_LIGHT)

        mSensorManager?.registerListener(LuzSensor(),mLuz,SensorManager.SENSOR_DELAY_UI)

    }

    internal inner class LuzSensor : SensorEventListener {
        override fun onAccuracyChanged(sensor: Sensor, accuracy: Int) {}

        override fun onSensorChanged(event: SensorEvent) {
            val vl = event.values[0]

            Log.i("SENSOR_LUZ", "VALOR DO SENSOR = "+ vl)

            if (vl < 10) {
                val telaLuz = findViewById<TextView>(R.id.luz)
                telaLuz.setText("ESCURO")
                Log.i("SENSOR_LUZ", "VALOR DO SENSOR = ESCURO")
            } else {
                val telaLuz = findViewById<TextView>(R.id.luz)
                telaLuz.setText("CLARO")
                Log.i("SENSOR_LUZ", "VALOR DO SENSOR = CLARO")
            }
        }
    }
}
