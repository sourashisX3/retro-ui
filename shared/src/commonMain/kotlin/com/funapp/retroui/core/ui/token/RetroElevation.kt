package com.funapp.retroui.core.ui.token

import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

/**
 * Elevation levels.
 *
 * The retro kit does NOT use traditional soft Material shadows. Components
 * cast a HARD OFFSET shadow (0 blur) drawn by the [retroShadow] modifier.
 * These values define how far the hard shadow steps out of the component.
 */
data class RetroElevation(
    val none: Dp = 0.dp,
    val low: Dp = 3.dp,
    val medium: Dp = 4.dp,
    val high: Dp = 6.dp,
)

val DefaultRetroElevation = RetroElevation()
