package com.uszkaisandor.bored.leisure.presentation.home

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.platform.LocalContext
import kotlin.math.sqrt

private const val SHAKE_G_FORCE_THRESHOLD = 2.7f
private const val SHAKE_DEBOUNCE_MILLIS = 800L

/**
 * Fires [onShake] when the device is given a deliberate shake — the app's
 * signature "bored? shake it" gesture for rerolling the activity. Registers an
 * accelerometer listener while composed and cleans it up on dispose.
 */
@Composable
fun rememberShakeDetector(
    enabled: Boolean = true,
    onShake: () -> Unit,
) {
    val context = LocalContext.current
    val currentOnShake by rememberUpdatedState(onShake)

    DisposableEffect(context, enabled) {
        val sensorManager = context.getSystemService(Context.SENSOR_SERVICE) as? SensorManager
        val accelerometer = sensorManager?.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)
        if (!enabled || sensorManager == null || accelerometer == null) {
            return@DisposableEffect onDispose { }
        }

        var lastShakeAt = 0L
        val listener = object : SensorEventListener {
            override fun onSensorChanged(event: SensorEvent) {
                val (x, y, z) = event.values
                val gForce = sqrt(x * x + y * y + z * z) / SensorManager.GRAVITY_EARTH
                if (gForce < SHAKE_G_FORCE_THRESHOLD) return
                val now = System.currentTimeMillis()
                if (now - lastShakeAt < SHAKE_DEBOUNCE_MILLIS) return
                lastShakeAt = now
                currentOnShake()
            }

            override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) = Unit
        }

        sensorManager.registerListener(listener, accelerometer, SensorManager.SENSOR_DELAY_GAME)
        onDispose { sensorManager.unregisterListener(listener) }
    }
}
