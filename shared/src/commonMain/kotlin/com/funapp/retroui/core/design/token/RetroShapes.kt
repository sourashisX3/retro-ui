package com.funapp.retroui.core.design.token

import androidx.compose.foundation.shape.CornerBasedShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

/**
 * Shape / radius system.
 *
 * The retro kit is SQUARED / slightly-rounded — not modern pill-heavy UI.
 * Most components are rectangular with subtly softened corners.
 */
data class RetroShapes(
    val xs: Dp = 2.dp,
    val sm: Dp = 4.dp,
    val md: Dp = 6.dp,
    val lg: Dp = 8.dp,
    val xl: Dp = 10.dp,
    val xxl: Dp = 12.dp,
    val full: Dp = 100.dp,
)

data class RetroShapeTokens(
    val button: CornerBasedShape = RoundedCornerShape(6.dp),
    val buttonPill: CornerBasedShape = RoundedCornerShape(100.dp),
    val card: CornerBasedShape = RoundedCornerShape(8.dp),
    val cardLarge: CornerBasedShape = RoundedCornerShape(10.dp),
    val chip: CornerBasedShape = RoundedCornerShape(4.dp),
    val input: CornerBasedShape = RoundedCornerShape(6.dp),
    val dialog: CornerBasedShape = RoundedCornerShape(10.dp),
    val sheet: CornerBasedShape = RoundedCornerShape(12.dp),
    val badge: CornerBasedShape = RoundedCornerShape(4.dp),
)

val DefaultRetroShapes = RetroShapes()
val DefaultRetroShapeTokens = RetroShapeTokens()