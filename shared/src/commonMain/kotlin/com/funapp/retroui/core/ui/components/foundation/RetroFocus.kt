package com.funapp.retroui.core.ui.components.foundation

import androidx.compose.foundation.shape.CornerBasedShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.funapp.retroui.core.ui.theme.RetroTheme

/**
 * Draws an offset, chunky focus indicator around a component when it
 * receives keyboard / talk-back focus.
 *
 * Must be applied LAST in the modifier chain (after [androidx.compose.foundation.clickable]),
 * so the ring is measured against the component's final bounds and painted on
 * top of its shadow and background.
 */
@Composable
fun Modifier.retroFocusRing(
    color: Color = RetroTheme.colors.decorPrimary,
    offset: Dp = 4.dp,
    thickness: Dp = 3.dp,
): Modifier {
    var focused by remember { mutableStateOf(false) }
    return onFocusChanged { focused = it.isFocused }
        .drawWithContent {
            drawContent()
            if (focused) {
                val strokeWidth = thickness.toPx()
                val ringSize = Size(size.width + offset.toPx() + strokeWidth, size.height + offset.toPx() + strokeWidth)
                val topLeft = Offset(-(offset.toPx() + strokeWidth) / 2, -(offset.toPx() + strokeWidth) / 2)
                drawRoundRect(
                    color = color,
                    topLeft = topLeft,
                    size = ringSize,
                    cornerRadius = CornerRadius(6.dp.toPx()),
                    style = Stroke(width = strokeWidth),
                )
            }
        }
}