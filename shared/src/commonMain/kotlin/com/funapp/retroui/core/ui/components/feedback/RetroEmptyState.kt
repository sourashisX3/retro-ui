package com.funapp.retroui.core.ui.components.feedback
import com.funapp.retroui.core.ui.icons.RetroIcons

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import com.funapp.retroui.core.ui.icons.Star
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.funapp.retroui.core.ui.components.foundation.RetroText
import com.funapp.retroui.core.ui.theme.RetroTheme

/**
 * Empty-state block: retro icon tile + pixel title + optional subtitle and an
 * optional action slot (usually a [RetroButton]).
 */
@Composable
fun RetroEmptyState(
    title: String,
    modifier: Modifier = Modifier,
    subtitle: String? = null,
    icon: ImageVector? = RetroIcons.Star,
    action: (@Composable () -> Unit)? = null,
) {
    val colors = RetroTheme.colors
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(RetroTheme.spacing.xxl),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        if (icon != null) {
            val tileShape = RetroTheme.shapeTokens.chip
            Box(
                modifier = Modifier
                    .size(72.dp)
                    .clip(tileShape)
                    .background(colors.surfaceMuted)
                    .border(BorderStroke(RetroTheme.borders.default, colors.outlineStrong), tileShape),
                contentAlignment = Alignment.Center,
            ) {
                Icon(
                    imageVector = icon,
                    contentDescription = null,
                    tint = colors.textSecondary,
                    modifier = Modifier.size(RetroTheme.dimensions.iconLG),
                )
            }
        }
        Spacer(modifier = Modifier.height(RetroTheme.spacing.md))
        RetroText(
            text = title,
            style = RetroTheme.typography.heading,
            color = colors.textPrimary,
            textAlign = TextAlign.Center,
        )
        if (subtitle != null) {
            Spacer(modifier = Modifier.height(RetroTheme.spacing.sm))
            RetroText(
                text = subtitle,
                style = RetroTheme.typography.bodySmall,
                color = colors.textMuted,
                textAlign = TextAlign.Center,
            )
        }
        if (action != null) {
            Spacer(modifier = Modifier.height(RetroTheme.spacing.lg))
            action()
        }
    }
}
