package com.funapp.retroui.core.ui.sensors

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext

/** Maximum card tilt in degrees - medium lean, never a 90-degree flip. */
private const val MAX_TILT_DEGREES = 20f

/** Low-pass factor applied to the raw angular velocity for smooth motion. */
private const val FILTER_ALPHA = 0.3f

/** Leaky-integration decay: slowly returns to flat when the device is still. */
private const val DRIFT_DECAY = 0.995f

private const val RAD_TO_DEG = 180f / kotlin.math.PI.toFloat()

@Composable
actual fun rememberGyroTilt(enabled: Boolean): GyroTiltState {
    val context = LocalContext.current
    val sensorManager = remember(context) {
        context.getSystemService(Context.SENSOR_SERVICE) as SensorManager
    }
    val sensor = remember(sensorManager) {
        sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE)
    }
    if (!enabled || sensor == null) return GyroTiltState.Disabled

    val tiltX = remember { mutableFloatStateOf(0f) }
    val tiltY = remember { mutableFloatStateOf(0f) }
    val filteredX = remember { mutableFloatStateOf(0f) }
    val filteredY = remember { mutableFloatStateOf(0f) }

    DisposableEffect(sensorManager, sensor) {
        var lastNanos = 0L
        val listener = object : SensorEventListener {
            override fun onSensorChanged(event: SensorEvent) {
                val now = event.timestamp
                if (lastNanos == 0L) {
                    lastNanos = now
                    return
                }
                val dtSeconds = (now - lastNanos).coerceIn(1_000_000L, 200_000_000L) / 1e9f
                lastNanos = now

                val rawX = event.values[0]
                val rawY = event.values[1]
                filteredX.floatValue += FILTER_ALPHA * (rawX - filteredX.floatValue)
                filteredY.floatValue += FILTER_ALPHA * (rawY - filteredY.floatValue)

                tiltX.floatValue = (tiltX.floatValue * DRIFT_DECAY + filteredX.floatValue * dtSeconds * RAD_TO_DEG)
                    .coerceIn(-MAX_TILT_DEGREES, MAX_TILT_DEGREES)
                tiltY.floatValue = (tiltY.floatValue * DRIFT_DECAY + filteredY.floatValue * dtSeconds * RAD_TO_DEG)
                    .coerceIn(-MAX_TILT_DEGREES, MAX_TILT_DEGREES)
            }

            override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) = Unit
        }
        sensorManager.registerListener(listener, sensor, SensorManager.SENSOR_DELAY_GAME)
        onDispose { sensorManager.unregisterListener(listener) }
    }

    return GyroTiltState(available = true, tiltX = tiltX, tiltY = tiltY)
}