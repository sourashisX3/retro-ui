package com.funapp.retroui.core.design.components.foundation

import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.drawscope.translate
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

/**
 * HARD OFFSET SHADOW.
 *
 * The signature look of the retro kit: a solid, hard-edged shadow that steps
 * down-right behind the component. There is NO blur and NO soft Material glow.
 *
 * When the component is "pressed", callers collapse [offsetX]/[offsetY] to
 * zero so the control visually sinks into the page.
 *
 * Must be applied BEFORE `.background(...)` / `.border(...)` in the chain so
 * the shadow draws behind the fill.
 */
fun Modifier.retroHardShadow(
    offsetX: Dp = 3.dp,
    offsetY: Dp = 4.dp,
    color: Color = Color(0xFF171717),
    shape: Shape = RectangleShape,
): Modifier = drawWithCache {
    val dx = offsetX.toPx()
    val dy = offsetY.toPx()
    val outline = shape.createOutline(size, layoutDirection, this)
    onDrawBehind {
        translate(left = dx, top = dy) {
            when (outline) {
                is Outline.Rectangle -> {
                    val rect = outline.rect
                    drawRect(
                        color = color,
                        topLeft = Offset(rect.left, rect.top),
                        size = Size(rect.width, rect.height),
                    )
                }
                is Outline.Rounded -> {
                    val rr = outline.roundRect
                    drawRoundRect(
                        color = color,
                        topLeft = Offset(rr.left, rr.top),
                        size = Size(rr.width, rr.height),
                        cornerRadius = rr.topLeftCornerRadius,
                    )
                }
                is Outline.Generic -> drawPath(outline.path, color)
            }
        }
    }
}