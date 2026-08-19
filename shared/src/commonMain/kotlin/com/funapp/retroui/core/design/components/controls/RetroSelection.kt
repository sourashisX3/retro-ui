package com.funapp.retroui.core.design.components.controls

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerBasedShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.funapp.retroui.core.design.components.foundation.RetroText
import com.funapp.retroui.core.design.theme.RetroTheme

/**
 * Retro selection controls: hard-inked, square-ish, flat fills.
 */

@Composable
fun RetroSwitch(
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
) {
    val colors = RetroTheme.colors
    val trackColor = if (checked) colors.primary else colors.surfaceMuted
    val knobColor = if (checked) colors.onPrimary else colors.textMuted
    val shape = RoundedCornerShape(4.dp)

    Box(
        modifier = modifier
            .size(width = 48.dp, height = 26.dp)
            .clip(shape)
            .background(trackColor)
            .border(BorderStroke(RetroTheme.borders.default, colors.outlineStrong), shape)
            .clickable(
                enabled = enabled,
                interactionSource = remember { MutableInteractionSource() },
                indication = null,
            ) { onCheckedChange(!checked) }
            .alpha(if (enabled) 1f else 0.5f)
            .padding(2.dp),
    ) {
        Box(
            modifier = Modifier
                .align(if (checked) Alignment.CenterEnd else Alignment.CenterStart)
                .size(18.dp)
                .clip(CircleShape)
                .background(knobColor)
                .border(RetroTheme.borders.default, colors.outlineStrong, CircleShape),
        )
    }
}

@Composable
fun RetroCheckbox(
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    modifier: Modifier = Modifier,
    label: String? = null,
    enabled: Boolean = true,
) {
    val colors = RetroTheme.colors
    val shape: CornerBasedShape = RoundedCornerShape(3.dp)
    Row(
        modifier = modifier.clickable(
            enabled = enabled,
            interactionSource = remember { MutableInteractionSource() },
            indication = null,
        ) { onCheckedChange(!checked) },
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Box(
            modifier = Modifier
                .size(22.dp)
                .clip(shape)
                .background(if (checked) colors.primary else colors.surface)
                .border(BorderStroke(RetroTheme.borders.default, colors.outlineStrong), shape),
            contentAlignment = Alignment.Center,
        ) {
            if (checked) {
                Icon(
                    imageVector = Icons.Filled.Check,
                    contentDescription = null,
                    modifier = Modifier.size(16.dp),
                    tint = colors.onPrimary,
                )
            }
        }
        if (label != null) {
            RetroText(
                text = label,
                style = RetroTheme.typography.bodySmall,
                color = colors.textPrimary,
                modifier = Modifier.padding(start = RetroTheme.spacing.sm),
            )
        }
    }
}

@Composable
fun RetroRadio(
    selected: Boolean,
    onSelect: () -> Unit,
    modifier: Modifier = Modifier,
    label: String? = null,
    enabled: Boolean = true,
) {
    val colors = RetroTheme.colors
    Row(
        modifier = modifier.clickable(
            enabled = enabled,
            interactionSource = remember { MutableInteractionSource() },
            indication = null,
        ) { onSelect() },
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Box(
            modifier = Modifier
                .size(22.dp)
                .clip(CircleShape)
                .background(if (selected) colors.primary else colors.surface)
                .border(BorderStroke(RetroTheme.borders.default, colors.outlineStrong), CircleShape),
            contentAlignment = Alignment.Center,
        ) {
            if (selected) {
                Box(
                    modifier = Modifier
                        .size(10.dp)
                        .clip(CircleShape)
                        .background(colors.onPrimary),
                )
            }
        }
        if (label != null) {
            RetroText(
                text = label,
                style = RetroTheme.typography.bodySmall,
                color = colors.textPrimary,
                modifier = Modifier.padding(start = RetroTheme.spacing.sm),
            )
        }
    }
}