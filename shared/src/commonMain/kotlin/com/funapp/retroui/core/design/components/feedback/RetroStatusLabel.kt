package com.funapp.retroui.core.design.components.feedback

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerBasedShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.funapp.retroui.core.design.components.foundation.RetroText
import com.funapp.retroui.core.design.components.foundation.retroHardShadow
import com.funapp.retroui.core.design.theme.RetroTheme

/**
 * Compact arcade status widget, e.g. `LIVE ●`.
 *
 * Cream surface + ink outline + bold pixel label + optional colored dot.
 */
@Composable
fun RetroStatusLabel(
    text: String,
    modifier: Modifier = Modifier,
    dotColor: Color? = null,
    container: Color = RetroTheme.colors.surface,
    content: Color = RetroTheme.colors.textPrimary,
) {
    val shape: CornerBasedShape = RetroTheme.shapeTokens.badge
    Box(
        modifier = modifier
            .retroHardShadow(
                offsetX = 2.dp,
                offsetY = 2.dp,
                color = RetroTheme.colors.outline,
                shape = shape,
            )
            .clip(shape)
            .background(container)
            .border(BorderStroke(RetroTheme.borders.default, RetroTheme.colors.outlineStrong), shape)
            .padding(horizontal = RetroTheme.spacing.md, vertical = RetroTheme.spacing.xs),
        contentAlignment = Alignment.Center,
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            if (dotColor != null) {
                Box(
                    modifier = Modifier
                        .padding(end = RetroTheme.spacing.xs)
                        .size(8.dp)
                        .clip(CircleShape)
                        .background(dotColor)
                        .border(RetroTheme.borders.thin, RetroTheme.colors.outlineStrong, CircleShape),
                )
            }
            RetroText(
                text = text,
                style = RetroTheme.typography.caption,
                color = content,
            )
        }
    }
}