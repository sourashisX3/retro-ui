package com.funapp.retroui.core.design.components.branding

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.offset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import kotlin.math.roundToInt

/**
 * Pixel text with the signature retro treatment: thick ink outline + hard
 * offset shadow. Rendered with layered [Text] (stroke behind, fill on top) so
 * it works with any [TextStyle] — used by the logo and app icon.
 */
@Composable
internal fun RetroOutlineText(
    text: String,
    style: TextStyle,
    fill: Color,
    outline: Color,
    shadow: Color,
    outlineWidth: Dp = 2.dp,
    modifier: Modifier = Modifier,
) {
    val density = LocalDensity.current
    val strokePx = with(density) { outlineWidth.toPx() }
    val shadowX = with(density) { (outlineWidth * 0.8f).toPx() }
    val shadowY = with(density) { (outlineWidth * 1.2f).toPx() }

    Box(modifier = modifier, contentAlignment = Alignment.Center) {
        Text(
            text = text,
            style = style.copy(color = shadow, drawStyle = Stroke(width = strokePx * 2f)),
            modifier = Modifier.offset { IntOffset(shadowX.roundToInt(), shadowY.roundToInt()) },
        )
        Text(
            text = text,
            style = style.copy(color = outline, drawStyle = Stroke(width = strokePx * 2f)),
        )
        Text(
            text = text,
            style = style.copy(color = fill),
        )
    }
}