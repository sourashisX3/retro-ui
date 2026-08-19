package com.funapp.retroui.core.design.components.game

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CornerBasedShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.funapp.retroui.core.design.components.foundation.RetroText
import com.funapp.retroui.core.design.components.foundation.retroHardShadow
import com.funapp.retroui.core.design.theme.RetroColors
import com.funapp.retroui.core.design.theme.RetroTheme
import com.funapp.retroui.core.feedback.rememberRetroTapFeedback
import com.funapp.retroui.core.design.animation.retroPressFeedback

/** Collectible-card rarity tiers. Colors come from the semantic palette. */
enum class RetroCardRarity { Common, Rare, Epic, Legendary }

/** Semantic rarity accent color. Shared across cards and feature tiles. */
internal fun RetroCardRarity.rarityColor(colors: RetroColors): Color = when (this) {
    RetroCardRarity.Common -> colors.surfaceMuted
    RetroCardRarity.Rare -> colors.info
    RetroCardRarity.Epic -> colors.accent
    RetroCardRarity.Legendary -> colors.secondary
}

/**
 * The collectible game card — the hero element of the design system.
 *
 * Thick ink outline + hard shadow + rarity accent + pixel title + artwork
 * slot + cost badge + type chip + optional stat footer. All visuals come from
 * tokens; only the [artworkIcon] is content.
 */
@Composable
fun RetroGameCard(
    title: String,
    cost: String,
    type: String,
    modifier: Modifier = Modifier,
    width: Dp = RetroTheme.dimensions.gameCardWidth,
    height: Dp = RetroTheme.dimensions.gameCardHeight,
    artworkIcon: ImageVector? = null,
    description: String? = null,
    rarity: RetroCardRarity = RetroCardRarity.Common,
    footer: String? = null,
    selected: Boolean = false,
    enabled: Boolean = true,
    onClick: (() -> Unit)? = null,
) {
    val colors = RetroTheme.colors
    val shape: CornerBasedShape = RetroTheme.shapeTokens.card
    val accent = rarity.rarityColor(colors)
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
            .width(width)
            .height(height)
            .then(
                Modifier
                    .retroHardShadow(
                        offsetX = 3.dp,
                        offsetY = 4.dp,
                        color = colors.outline,
                        shape = shape,
                    )
                    .clip(shape)
                    .background(colors.surface)
                    .border(
                        BorderStroke(RetroTheme.borders.default, colors.outlineStrong),
                        shape,
                    )
            )
            .then(
                if (selected) {
                    Modifier.border(BorderStroke(RetroTheme.borders.strong, accent), shape)
                } else Modifier
            )
            .then(if (onClick != null) Modifier.retroPressFeedback(interactionSource) else Modifier)
            .then(clickModifier)
            .alpha(if (enabled) 1f else 0.5f)
            .padding(RetroTheme.spacing.sm),
    ) {
        Column(modifier = Modifier.fillMaxWidth()) {
            // Cost + type header
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                CostBadge(cost = cost, accent = accent)
                Spacer(modifier = Modifier.width(RetroTheme.spacing.xs))
                TypeChip(type = type, accent = accent, modifier = Modifier.weight(1f))
            }
            Spacer(modifier = Modifier.height(RetroTheme.spacing.sm))

            // Artwork slot
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .clip(RetroTheme.shapeTokens.chip)
                    .background(colors.surfaceVariant)
                    .border(
                        RetroTheme.borders.thin,
                        accent,
                        RetroTheme.shapeTokens.chip,
                    ),
                contentAlignment = Alignment.Center,
            ) {
                if (artworkIcon != null) {
                    Icon(
                        imageVector = artworkIcon,
                        contentDescription = null,
                        tint = colors.textSecondary,
                        modifier = Modifier.size(RetroTheme.dimensions.iconXL),
                    )
                }
            }

            Spacer(modifier = Modifier.height(RetroTheme.spacing.sm))

            // Title + optional description
            RetroText(
                text = title,
                style = RetroTheme.typography.label,
                color = colors.textPrimary,
                textAlign = TextAlign.Center,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
            )
            if (description != null) {
                RetroText(
                    text = description,
                    style = RetroTheme.typography.caption,
                    color = colors.textMuted,
                    textAlign = TextAlign.Center,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier.padding(top = RetroTheme.spacing.xxs),
                )
            }

            if (footer != null) {
                Spacer(modifier = Modifier.height(RetroTheme.spacing.sm))
                RetroText(
                    text = footer,
                    style = RetroTheme.typography.caption,
                    color = colors.onPrimary,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RetroTheme.shapeTokens.badge)
                        .background(colors.primary)
                        .padding(vertical = RetroTheme.spacing.xxs),
                )
            }
        }
    }
}

@Composable
private fun CostBadge(cost: String, accent: Color) {
    Box(
        modifier = Modifier
            .clip(RetroTheme.shapeTokens.badge)
            .background(RetroTheme.colors.secondary)
            .border(
                RetroTheme.borders.thin,
                RetroTheme.colors.outlineStrong,
                RetroTheme.shapeTokens.badge,
            )
            .padding(horizontal = RetroTheme.spacing.xs, vertical = RetroTheme.spacing.xxs),
    ) {
        RetroText(
            text = cost,
            style = RetroTheme.typography.label,
            color = RetroTheme.colors.onSecondary,
        )
    }
}

@Composable
private fun TypeChip(type: String, accent: Color, modifier: Modifier = Modifier) {
    RetroText(
        text = type,
        style = RetroTheme.typography.caption,
        color = RetroTheme.colors.onAccent,
        textAlign = TextAlign.Center,
        maxLines = 1,
        overflow = TextOverflow.Ellipsis,
        modifier = modifier
            .clip(RetroTheme.shapeTokens.badge)
            .background(accent)
            .padding(horizontal = RetroTheme.spacing.xs, vertical = RetroTheme.spacing.xxs),
    )
}
