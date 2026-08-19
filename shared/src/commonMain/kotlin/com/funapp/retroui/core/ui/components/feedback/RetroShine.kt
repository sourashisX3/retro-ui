package com.funapp.retroui.core.ui.components.feedback

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.keyframes
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CornerBasedShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

/**
 * Soft glass / light sheen overlay.
 *
 * A wide, softly-fading diagonal band sweeps from the top-left to the
 * bottom-right over the element it is stacked on, then pauses and loops.
 * Place it inside a [androidx.compose.foundation.layout.Box] on top of the
 * element to highlight:
 *
 * ```
 * Box {
 *     RetroButton(...)
 *     RetroShine(shape = RetroTheme.shapeTokens.button)
 * }
 * ```
 *
 * Reusable for any highlight need (CTAs, freshly-unlocked cards, active
 * slots). Tune [bandFraction] / [peakAlpha] for subtlety; disable with
 * [enabled].
 */
@Composable
fun RetroShine(
    modifier: Modifier = Modifier,
    shape: CornerBasedShape,
    bandFraction: Float = 0.45f,
    peakAlpha: Float = 0.20f,
    rotation: Float = 22f,
    sweepMillis: Int = 2000,
    holdMillis: Int = 1200,
    enabled: Boolean = true,
) {
    if (!enabled) return

    val shine = rememberInfiniteTransition(label = "retroShine")
    val offset by shine.animateFloat(
        initialValue = -1.6f,
        targetValue = 1.6f,
        animationSpec = infiniteRepeatable(
            animation = keyframes {
                durationMillis = sweepMillis + holdMillis
                -1.6f at 0 with LinearEasing
                1.6f at sweepMillis with LinearEasing
                1.6f at sweepMillis + holdMillis
            },
            repeatMode = RepeatMode.Restart,
        ),
        label = "retroShineOffset",
    )
    var widthPx by remember { mutableStateOf(0) }

    Box(
        modifier = modifier
            .fillMaxSize()
            .onSizeChanged { widthPx = it.width }
            .clip(shape),
    ) {
        Box(
            modifier = Modifier
                .align(Alignment.Center)
                .fillMaxHeight(2f)
                .width(dpFraction(widthPx, bandFraction))
                .graphicsLayer {
                    translationX = widthPx * offset
                    rotationZ = rotation
                }
                .background(
                    brush = Brush.horizontalGradient(
                        colors = listOf(
                            Color.Transparent,
                            Color.White.copy(alpha = peakAlpha),
                            Color.Transparent,
                        ),
                    ),
                ),
        )
    }
}

private fun dpFraction(widthPx: Int, fraction: Float): Dp {
    return (widthPx * fraction).toFloat().dp
}