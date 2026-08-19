package com.funapp.retroui.core.ui.components.feedback

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CornerBasedShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.clipPath
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.size
import androidx.compose.ui.unit.dp
import com.funapp.retroui.core.ui.components.foundation.retroHardShadow
import com.funapp.retroui.core.ui.theme.RetroTheme

/**
 * Game dialogue speech bubble — cream box + ink outline + hard shadow + a
 * small triangular pointer (default pointing down).
 */
@Composable
fun SpeechBubble(
    modifier: Modifier = Modifier,
    showPointer: Boolean = true,
    content: @Composable () -> Unit,
) {
    val colors = RetroTheme.colors
    val shape: CornerBasedShape = RetroTheme.shapeTokens.card

    Column(modifier = modifier) {
        Box(
            modifier = Modifier
                .retroHardShadow(
                    offsetX = 3.dp,
                    offsetY = 4.dp,
                    color = colors.outline,
                    shape = shape,
                )
                .clip(shape)
                .background(colors.surface)
                .border(BorderStroke(RetroTheme.borders.default, colors.outlineStrong), shape)
                .padding(RetroTheme.spacing.lg),
            contentAlignment = Alignment.Center,
        ) {
            content()
        }
        if (showPointer) {
            Pointer(color = colors.surface)
        }
    }
}

@Composable
private fun Pointer(color: androidx.compose.ui.graphics.Color) {
    Canvas(
        modifier = Modifier
            .padding(top = 0.dp)
            .size(width = 28.dp, height = 12.dp),
    ) {
        drawPointer(size.width, size.height, color)
    }
}

private fun DrawScope.drawPointer(width: Float, height: Float, color: androidx.compose.ui.graphics.Color) {
    val path = Path().apply {
        moveTo(0f, 0f)
        lineTo(width, 0f)
        lineTo(width / 2f, height)
        close()
    }
    clipPath(path) {
        drawRect(color = color)
    }
    drawPath(
        path = path,
        color = androidx.compose.ui.graphics.Color(0xFF171717),
        style = androidx.compose.ui.graphics.drawscope.Stroke(width = 2.dp.toPx()),
    )
}
