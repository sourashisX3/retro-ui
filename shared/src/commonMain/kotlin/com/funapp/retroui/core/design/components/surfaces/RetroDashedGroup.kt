package com.funapp.retroui.core.design.components.surfaces

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.RoundRect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.funapp.retroui.core.design.theme.RetroTheme

/**
 * Draws a clearly visible dashed ink outline around the content box.
 */
fun Modifier.dashedBorder(
    color: Color,
    strokeWidth: Dp = 2.dp,
    cornerRadius: Dp = 8.dp,
    dash: Dp = 12.dp,
    gap: Dp = 8.dp,
): Modifier = drawWithCache {
    val path = Path().apply {
        addRoundRect(
            RoundRect(
                rect = androidx.compose.ui.geometry.Rect(
                    left = strokeWidth.toPx() / 2,
                    top = strokeWidth.toPx() / 2,
                    right = size.width - strokeWidth.toPx() / 2,
                    bottom = size.height - strokeWidth.toPx() / 2,
                ),
                cornerRadius = CornerRadius(cornerRadius.toPx()),
            ),
        )
    }
    onDrawWithContent {
        drawContent()
        drawPath(
            path = path,
            color = color,
            style = Stroke(
                width = strokeWidth.toPx(),
                pathEffect = PathEffect.dashPathEffect(
                    floatArrayOf(dash.toPx(), gap.toPx()),
                ),
                cap = StrokeCap.Square,
            ),
        )
    }
}

/**
 * Dashing grouping container — the "dashed box" used to group related
 * controls, settings sections and game panels.
 */
@Composable
fun RetroDashedGroup(
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues = PaddingValues(RetroTheme.spacing.lg),
    content: @Composable ColumnScope.() -> Unit,
) {
    Column(
        modifier = modifier.dashedBorder(
            color = RetroTheme.colors.outline,
            strokeWidth = 2.dp,
            cornerRadius = RetroTheme.shapes.lg,
        ).padding(contentPadding),
        content = content,
    )
}