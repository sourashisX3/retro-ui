package com.funapp.retroui.core.ui.sensors

import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer

/**
 * Live device tilt sourced from the gyroscope.
 *
 * [available] is false on devices and targets without a gyroscope, in which
 * case cards simply stay flat. [tiltX]/[tiltY] hold the smoothed device tilt
 * angles in degrees (pitch and roll). They are exposed as [State] so consumers
 * read them inside a `graphicsLayer` block: sensor updates then only trigger
 * redraws, never recomposition of the whole screen.
 */
class GyroTiltState(
    val available: Boolean,
    val tiltX: State<Float>,
    val tiltY: State<Float>,
) {
    companion object {
        /** Tilt source for devices/targets without a gyroscope: always flat. */
        val Disabled = GyroTiltState(
            available = false,
            tiltX = mutableFloatStateOf(0f),
            tiltY = mutableFloatStateOf(0f),
        )
    }
}

/** Resolves the platform tilt source. No-op (flat) on targets without a gyro. */
@Composable
expect fun rememberGyroTilt(): GyroTiltState

/**
 * Tilts the content like a card held in hand against the device tilt.
 * A no-op when the gyroscope is unavailable.
 */
fun Modifier.gyroTilt(state: GyroTiltState): Modifier =
    if (state.available) {
        graphicsLayer {
            rotationX = -state.tiltX.value
            rotationY = -state.tiltY.value
            cameraDistance = 16f * density
        }
    } else {
        this
    }