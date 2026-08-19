package com.funapp.retroui.core.ui.components.branding

import androidx.compose.foundation.Canvas
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path

/**
 * Small pixel-art decorations (star, sparkle, bolt) used by the logo, splash
 * and game screens. Drawn with `Canvas` — no image assets required.
 */

/** Four-point pixel star. */
@Composable
fun RetroStar(
    modifier: Modifier = Modifier,
    color: Color,
) {
    Canvas(modifier = modifier) {
        val cx = size.width / 2f
        val cy = size.height / 2f
        val r = size.width * 0.35f
        val path = Path().apply {
            moveTo(cx, 0f)
            lineTo(cx + r * 0.5f, cy - r * 0.5f)
            lineTo(size.width, cy)
            lineTo(cx + r * 0.5f, cy + r * 0.5f)
            lineTo(cx, size.height)
            lineTo(cx - r * 0.5f, cy + r * 0.5f)
            lineTo(0f, cy)
            lineTo(cx - r * 0.5f, cy - r * 0.5f)
            close()
        }
        drawPath(path, color)
    }
}

/** Four-point sparkle (cross with slim arms). */
@Composable
fun RetroSparkle(
    modifier: Modifier = Modifier,
    color: Color,
) {
    Canvas(modifier = modifier) {
        val c = size.width / 2f
        val arm = size.width * 0.2f
        drawRect(
            color = color,
            topLeft = Offset(c - arm / 2f, 0f),
            size = Size(arm, size.height),
        )
        drawRect(
            color = color,
            topLeft = Offset(0f, c - arm / 2f),
            size = Size(size.width, arm),
        )
    }
}

/** Pixel lightning bolt. */
@Composable
fun RetroBolt(
    modifier: Modifier = Modifier,
    color: Color,
) {
    Canvas(modifier = modifier) {
        val w = size.width
        val h = size.height
        val path = Path().apply {
            moveTo(w * 0.25f, 0f)
            lineTo(0f, h * 0.58f)
            lineTo(w * 0.42f, h * 0.58f)
            lineTo(w * 0.36f, h)
            lineTo(w, h * 0.4f)
            lineTo(w * 0.55f, h * 0.4f)
            lineTo(w * 0.62f, 0f)
            close()
        }
        drawPath(path, color)
    }
}
