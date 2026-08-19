package com.funapp.retroui.features.profile.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import com.funapp.retroui.core.ui.components.foundation.RetroText
import com.funapp.retroui.core.ui.theme.RetroTheme
import com.funapp.retroui.core.utils.asString
import com.funapp.retroui.features.profile.data.Badge
import com.funapp.retroui.features.profile.data.getMockBadges

/**
 * Grid of earned badges — two rows of compact icon + label tiles.
 */
@Composable
internal fun BadgeGrid() {
    val badges = getMockBadges()
    Column {
        badges.chunked(3).forEachIndexed { index, rowBadges ->
            if (index > 0) {
                Spacer(modifier = Modifier.height(RetroTheme.spacing.sm))
            }
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(RetroTheme.spacing.sm),
            ) {
                rowBadges.forEach { badge ->
                    BadgeCell(badge = badge, modifier = Modifier.weight(1f))
                }
                repeat(3 - rowBadges.size) {
                    Spacer(modifier = Modifier.weight(1f))
                }
            }
        }
    }
}

@Composable
private fun BadgeCell(
    badge: Badge,
    modifier: Modifier = Modifier,
) {
    val colors = RetroTheme.colors
    Box(
        modifier = modifier
            .clip(RetroTheme.shapeTokens.badge)
            .background(colors.surfaceVariant)
            .padding(vertical = RetroTheme.spacing.sm),
        contentAlignment = Alignment.Center,
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Icon(
                imageVector = badge.icon,
                contentDescription = null,
                tint = colors.accent,
                modifier = Modifier.size(RetroTheme.dimensions.iconMD),
            )
            Spacer(modifier = Modifier.height(RetroTheme.spacing.xxs))
            RetroText(
                text = badge.label.asString(),
                style = RetroTheme.typography.caption,
                color = colors.textPrimary,
            )
        }
    }
}