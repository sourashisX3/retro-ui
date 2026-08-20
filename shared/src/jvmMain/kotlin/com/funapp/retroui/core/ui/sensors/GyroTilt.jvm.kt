package com.funapp.retroui.core.ui.sensors

import androidx.compose.runtime.Composable

@Composable
actual fun rememberGyroTilt(enabled: Boolean): GyroTiltState = GyroTiltState.Disabled