package com.funapp.retroui.core.design.components.feedback

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CornerBasedShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.funapp.retroui.core.design.components.foundation.RetroText
import com.funapp.retroui.core.design.theme.RetroTheme

enum class RetroProgressColor { Health, Energy, Xp, Danger, Info }

/**
 * Classic game-HUD progress bar.
 *
 * Dark outer outline, compact height, cream track, FLAT bright fill. No
 * gradients, no gloss, no glass. Optional [segments] draw vertical separators
 * inside the fill and [valueText] can be rendered in the center.
 */
@Composable
fun RetroProgressBar(
    progress: Float,
    modifier: Modifier = Modifier,
    color: RetroProgressColor = RetroProgressColor.Health,
    height: Dp = RetroTheme.dimensions.progressBarHeight,
    segments: Int = 0,
    showValue: Boolean = false,
    valueText: String? = null,
) {
    val colors = RetroTheme.colors
    val shape: CornerBasedShape = RoundedCornerShape(RetroTheme.shapes.sm)
    val clamped = progress.coerceIn(0f, 1f)
    val fillColor = when (color) {
        RetroProgressColor.Health -> colors.primary
        RetroProgressColor.Energy -> colors.secondary
        RetroProgressColor.Xp -> colors.accent
        RetroProgressColor.Danger -> colors.error
        RetroProgressColor.Info -> colors.info
    }

    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(height)
            .clip(shape)
            .background(colors.surfaceMuted)
            .border(BorderStroke(RetroTheme.borders.default, colors.outlineStrong), shape),
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth(clamped)
                .fillMaxHeight()
                .background(fillColor),
        )
        if (segments > 1) {
            SegmentLines(segments = segments, color = colors.outlineStrong)
        }
        if (showValue || valueText != null) {
            RetroText(
                text = valueText ?: "${(clamped * 100).toInt()}",
                style = RetroTheme.typography.caption,
                color = colors.textPrimary,
                modifier = Modifier.align(Alignment.Center),
            )
        }
    }
}

@Composable
private fun SegmentLines(segments: Int, color: Color) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .drawBehind {
                val count = segments - 1
                repeat(count) { index ->
                    val x = size.width * (index + 1) / segments
                    drawLine(
                        color = color,
                        start = Offset(x, 0f),
                        end = Offset(x, size.height),
                        strokeWidth = 2.dp.toPx(),
                        cap = StrokeCap.Square,
                    )
                }
            },
    )
}