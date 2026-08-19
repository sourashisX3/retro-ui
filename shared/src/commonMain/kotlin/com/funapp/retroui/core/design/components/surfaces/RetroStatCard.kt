package com.funapp.retroui.core.design.components.surfaces

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.Dp
import com.funapp.retroui.core.design.components.feedback.RetroProgressBar
import com.funapp.retroui.core.design.components.feedback.RetroProgressColor
import com.funapp.retroui.core.design.components.foundation.RetroText
import com.funapp.retroui.core.design.theme.RetroTheme

/**
 * Compact HUD stat card: label + pixel score + optional segmented progress
 * bar. Used for quick-battle previews, XP, stats, etc.
 */
@Composable
fun RetroStatCard(
    label: String,
    value: String,
    modifier: Modifier = Modifier,
    width: Dp = RetroTheme.dimensions.statCardWidth,
    progress: Float? = null,
    progressColor: RetroProgressColor = RetroProgressColor.Health,
    icon: ImageVector? = null,
) {
    val colors = RetroTheme.colors
    RetroCard(
        modifier = modifier.width(width),
        contentPadding = RetroTheme.spacing.md,
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                if (icon != null) {
                    Icon(
                        imageVector = icon,
                        contentDescription = null,
                        modifier = Modifier.width(RetroTheme.spacing.md),
                        tint = colors.textSecondary,
                    )
                }
                RetroText(
                    text = label,
                    style = RetroTheme.typography.caption,
                    color = colors.textSecondary,
                )
            }
            RetroText(
                text = value,
                style = RetroTheme.typography.score,
                color = colors.textPrimary,
            )
            if (progress != null) {
                RetroProgressBar(
                    progress = progress,
                    color = progressColor,
                    height = RetroTheme.dimensions.progressBarThin,
                )
            }
        }
    }
}
