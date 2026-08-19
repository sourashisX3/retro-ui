package com.funapp.retroui.core.ui.components.surfaces

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CornerBasedShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.funapp.retroui.core.ui.components.foundation.RetroDivider
import com.funapp.retroui.core.ui.components.foundation.RetroText
import com.funapp.retroui.core.ui.components.foundation.retroHardShadow
import com.funapp.retroui.core.ui.components.foundation.retroTactilePress
import com.funapp.retroui.core.ui.theme.RetroTheme
import com.funapp.retroui.core.utils.rememberRetroTapFeedback

/**
 * Physical paper/game card.
 *
 * Cream/off-white surface + dark ink outline + HARD OFFSET shadow + compact
 * radius. Optional [header] strip gets a slightly different cream tone and a
 * dark separator — like a game card with a printed header band.
 */
@Composable
fun RetroCard(
    modifier: Modifier = Modifier,
    onClick: (() -> Unit)? = null,
    header: (@Composable () -> Unit)? = null,
    contentPadding: Dp = RetroTheme.spacing.lg,
    content: @Composable ColumnScope.() -> Unit,
) {
    val colors = RetroTheme.colors
    val shape: CornerBasedShape = RetroTheme.shapeTokens.card
    val tap = rememberRetroTapFeedback()
    val interactionSource = remember { MutableInteractionSource() }

    val clickModifier = if (onClick != null) {
        Modifier.clickable(
            interactionSource = interactionSource,
            indication = null,
        ) {
            tap.play()
            onClick()
        }
    } else Modifier

    Box(
        modifier = modifier
            .then(
                if (onClick != null) {
                    Modifier.retroTactilePress(interactionSource, shape, colors.outline)
                } else {
                    Modifier.retroHardShadow(
                        offsetX = 3.dp,
                        offsetY = 4.dp,
                        color = colors.outline,
                        shape = shape,
                    )
                },
            )
            .then(clickModifier)
            .clip(shape)
            .background(colors.surface)
            .border(BorderStroke(RetroTheme.borders.default, colors.outlineStrong), shape),
    ) {
        Column {
            if (header != null) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(colors.surfaceVariant)
                        .padding(horizontal = contentPadding, vertical = RetroTheme.spacing.md),
                ) {
                    header()
                }
                RetroDivider(
                    thickness = 2.dp,
                    modifier = Modifier.padding(horizontal = 0.dp),
                )
            }
            Column(
                modifier = Modifier
                    .padding(contentPadding),
                content = content,
            )
        }
    }
}

/** Standard game-card header: bold title, optional subtitle + trailing action. */
@Composable
fun RetroCardHeader(
    title: String,
    modifier: Modifier = Modifier,
    subtitle: String? = null,
    trailing: @Composable (() -> Unit)? = null,
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        verticalAlignment = androidx.compose.ui.Alignment.CenterVertically,
    ) {
        Column(Modifier.weight(1f)) {
            RetroText(
                text = title,
                style = RetroTheme.typography.title,
                color = RetroTheme.colors.textPrimary,
            )
            if (subtitle != null) {
                Spacer(modifier = Modifier.width(0.dp))
                RetroText(
                    text = subtitle,
                    style = RetroTheme.typography.caption,
                    color = RetroTheme.colors.textMuted,
                    modifier = Modifier.padding(top = RetroTheme.spacing.xxs),
                )
            }
        }
        if (trailing != null) trailing()
    }
}

