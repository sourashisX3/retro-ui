package com.funapp.retroui.core.ui.token

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

/**
 * Border / outline width scale.
 *
 * The retro look is defined by a thick dark ink outline on every component.
 * Components MUST consume these tokens — never scatter raw widths like
 * `2.dp` across feature code.
 */
data class RetroBorders(
    val thin: Dp = 1.dp,
    val default: Dp = 2.dp,
    val strong: Dp = 3.dp,
    val hero: Dp = 4.dp,
)

val DefaultRetroBorders = RetroBorders()

/**
 * Applies the standard retro ink outline. Apply AFTER `.background(...)` and
 * BEFORE `.clickable(...)` in the chain so the stroke sits on top of the fill.
 */
fun Modifier.retroOutline(
    width: Dp,
    color: Color,
    shape: Shape = RectangleShape,
): Modifier = border(BorderStroke(width, color), shape)
