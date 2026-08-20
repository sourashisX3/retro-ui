package com.funapp.retroui.core.ui.components.game

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CornerBasedShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.funapp.retroui.core.ui.components.foundation.RetroText
import com.funapp.retroui.core.ui.components.foundation.retroHardShadow
import com.funapp.retroui.core.ui.components.foundation.retroTactilePress
import com.funapp.retroui.core.ui.theme.RetroTheme
import com.funapp.retroui.core.utils.rememberRetroTapFeedback

/**
 * Empty / occupied deck slot — a square cell used to visualize a card in a
 * deck grid. When [empty] it renders with a lighter fill; otherwise the
 * caller provides the content (a mini card, icon or count).
 */
@Composable
fun RetroCardSlot(
    modifier: Modifier = Modifier,
    size: Dp = RetroTheme.dimensions.cardSlotSize,
    empty: Boolean = false,
    onClick: (() -> Unit)? = null,
    content: (@Composable () -> Unit)? = null,
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
            .size(size)
            .then(
                if (onClick != null) {
                    Modifier.retroTactilePress(
                        interactionSource = interactionSource,
                        shape = shape,
                        shadowColor = colors.shadow,
                        shadowX = 2.dp,
                        shadowY = 2.dp,
                    )
                } else {
                    Modifier.retroHardShadow(
                        offsetX = 2.dp,
                        offsetY = 2.dp,
                        color = colors.shadow,
                        shape = shape,
                    )
                },
            )
            .clip(shape)
            .background(if (empty) colors.surfaceMuted else colors.surface)
            .border(
                RetroTheme.borders.default,
                if (empty) colors.outline else colors.outlineStrong,
                shape,
            )
            .then(clickModifier)
            .alpha(if (empty) 0.7f else 1f),
        contentAlignment = Alignment.Center,
    ) {
        if (content != null) {
            content()
        } else if (empty) {
            RetroText(
                text = "+",
                style = RetroTheme.typography.heading,
                color = colors.textMuted,
            )
        }
    }
}

/**
 * Compact count label shown on a card slot (e.g. "x2").
 */
@Composable
fun RetroCardSlotCount(
    count: Int,
    modifier: Modifier = Modifier,
) {
    RetroText(
        text = "x$count",
        style = RetroTheme.typography.caption,
        color = RetroTheme.colors.textPrimary,
        modifier = modifier,
    )
}

