package com.funapp.retroui.core.design.components.controls

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CornerBasedShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import com.funapp.retroui.core.design.components.foundation.RetroText
import com.funapp.retroui.core.design.components.foundation.retroHardShadow
import com.funapp.retroui.core.design.theme.RetroTheme

enum class RetroChipVariant { Filled, Outline }

/**
 * Compact arcade chip — small, outlined, square-ish corners.
 */
@Composable
fun RetroChip(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    variant: RetroChipVariant = RetroChipVariant.Filled,
    selected: Boolean = false,
    enabled: Boolean = true,
    leadingIcon: androidx.compose.ui.graphics.vector.ImageVector? = null,
) {
    val colors = RetroTheme.colors
    val shape: CornerBasedShape = RetroTheme.shapeTokens.chip

    val container = when {
        !enabled -> colors.surfaceMuted
        selected -> colors.primary
        variant == RetroChipVariant.Filled -> colors.surfaceVariant
        else -> colors.surface
    }
    val content = when {
        !enabled -> colors.textMuted
        selected -> colors.onPrimary
        else -> colors.textPrimary
    }

    Box(
        modifier = modifier
            .retroHardShadow(
                offsetX = if (selected) 1.dp else 2.dp,
                offsetY = if (selected) 1.dp else 2.dp,
                color = colors.outline,
                shape = shape,
            )
            .clip(shape)
            .background(container)
            .border(BorderStroke(RetroTheme.borders.default, colors.outlineStrong), shape)
            .clickable(
                enabled = enabled,
                interactionSource = remember { MutableInteractionSource() },
                indication = null,
            ) { onClick() }
            .alpha(if (enabled) 1f else 0.5f)
            .padding(horizontal = RetroTheme.spacing.md, vertical = RetroTheme.spacing.xs),
        contentAlignment = Alignment.Center,
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            if (leadingIcon != null) {
                Icon(
                    imageVector = leadingIcon,
                    contentDescription = null,
                    modifier = Modifier.padding(end = RetroTheme.spacing.xxs),
                    tint = content,
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