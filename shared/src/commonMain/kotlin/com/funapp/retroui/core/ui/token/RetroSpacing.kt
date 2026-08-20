package com.funapp.retroui.core.ui.token

import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

/**
 * Semantic spacing scale based on a 4dp grid.
 *
 * The retro game-HUD layout is dense — components sit close together.
 * Feature code consumes these tokens; avoid raw values like 13.dp.
 */
data class RetroSpacing(
    val xxs: Dp = 2.dp,
    val xs: Dp = 4.dp,
    val sm: Dp = 8.dp,
    val md: Dp = 12.dp,
    val lg: Dp = 16.dp,
    val xl: Dp = 20.dp,
    val xxl: Dp = 24.dp,
    val xxxl: Dp = 32.dp,
    val x4: Dp = 40.dp,
    val x5: Dp = 48.dp,
    val x6: Dp = 64.dp,
)

val DefaultRetroSpacing = RetroSpacing()
