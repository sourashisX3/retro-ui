package com.funapp.retroui.core.ui.components.foundation

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.interaction.InteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
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
import androidx.compose.foundation.layout.offset
import com.funapp.retroui.core.ui.token.RetroMotion

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
 *
 * Default [color] is the ink-equivalent of `RetroTheme.colors.shadow`
 * (near-black in both light and dark) — composable callers may pass the
 * semantic shadow color explicitly.
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

/**
 * TACTILE PRESS for hard-shadow surfaces.
 *
 * The signature press of the retro kit (see [RetroButton]): while pressed the
 * hard shadow [shadowX]/[shadowY] collapse to zero and the surface sinks
 * [sinkY] into the page (120ms tween), so it feels like physically pressing
 * an arcade control. Wire the SAME [InteractionSource] that backs the
 * component's `clickable`.
 *
 * Replaces the static `retroHardShadow(...)` call at the same chain position
 * — must be applied BEFORE `.background(...)` / `.border(...)`.
 *
 * Rule: surfaces WITH a hard shadow use this; shadowless compact controls
 * use `retroPopPress`.
 */
@Composable
fun Modifier.retroTactilePress(
    interactionSource: InteractionSource,
    shape: Shape,
    shadowColor: Color,
    shadowX: Dp = 3.dp,
    shadowY: Dp = 4.dp,
    sinkY: Dp = 2.dp,
): Modifier {
    val pressed by interactionSource.collectIsPressedAsState()
    val animX by animateDpAsState(
        targetValue = if (pressed) 0.dp else shadowX,
        animationSpec = tween(RetroMotion.FastMs),
        label = "retroTactileShadowX",
    )
    val animY by animateDpAsState(
        targetValue = if (pressed) 0.dp else shadowY,
        animationSpec = tween(RetroMotion.FastMs),
        label = "retroTactileShadowY",
    )
    val sink by animateDpAsState(
        targetValue = if (pressed) sinkY else 0.dp,
        animationSpec = tween(RetroMotion.FastMs),
        label = "retroTactileSink",
    )
    return this
        .offset(y = sink)
        .retroHardShadow(
            offsetX = animX,
            offsetY = animY,
            color = shadowColor,
            shape = shape,
        )
}
