package com.funapp.retroui.core.ui.components.controls
import com.funapp.retroui.core.ui.icons.RetroIcons

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerBasedShape
import androidx.compose.foundation.shape.RoundedCornerShape
import com.funapp.retroui.core.ui.icons.Check
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.funapp.retroui.core.ui.components.foundation.RetroText
import com.funapp.retroui.core.ui.theme.RetroTheme
import com.funapp.retroui.core.utils.rememberRetroTapFeedback
import com.funapp.retroui.core.ui.animation.retroPopPress

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
    val shape = RoundedCornerShape(100.dp)
    val tap = rememberRetroTapFeedback()
    val interactionSource = remember { MutableInteractionSource() }

    Box(
        modifier = modifier
            .size(width = 48.dp, height = 26.dp)
            .retroPopPress(interactionSource)
            .clip(shape)
            .background(trackColor)
            .border(BorderStroke(RetroTheme.borders.default, colors.outlineStrong), shape)
            .clickable(
                enabled = enabled,
                interactionSource = interactionSource,
                indication = null,
            ) {
                tap.play()
                onCheckedChange(!checked)
            }
            .alpha(if (enabled) 1f else 0.5f),
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(start = 5.dp, end = 5.dp, top = 4.dp, bottom = 4.dp),
            contentAlignment = if (checked) Alignment.CenterEnd else Alignment.CenterStart,
        ) {
            Box(
                modifier = Modifier
                    .size(18.dp)
                    .clip(CircleShape)
                    .background(knobColor)
                    .border(RetroTheme.borders.default, colors.outlineStrong, CircleShape),
            )
        }
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
    val tap = rememberRetroTapFeedback()
    val interactionSource = remember { MutableInteractionSource() }
    Row(
        modifier = modifier.clickable(
            enabled = enabled,
            interactionSource = interactionSource,
            indication = null,
        ) {
            tap.play()
            onCheckedChange(!checked)
        },
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Box(
            modifier = Modifier
                .size(22.dp)
                .retroPopPress(interactionSource)
                .clip(shape)
                .background(if (checked) colors.primary else colors.surface)
                .border(BorderStroke(RetroTheme.borders.default, colors.outlineStrong), shape),
            contentAlignment = Alignment.Center,
        ) {
            if (checked) {
                Icon(
                    imageVector = RetroIcons.Check,
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
    val tap = rememberRetroTapFeedback()
    val interactionSource = remember { MutableInteractionSource() }
    Row(
        modifier = modifier.clickable(
            enabled = enabled,
            interactionSource = interactionSource,
            indication = null,
        ) {
            tap.play()
            onSelect()
        },
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Box(
            modifier = Modifier
                .size(22.dp)
                .retroPopPress(interactionSource)
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

