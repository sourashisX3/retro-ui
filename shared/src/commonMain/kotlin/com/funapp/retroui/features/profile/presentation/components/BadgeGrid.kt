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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.ThumbUp
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import com.funapp.retroui.core.design.components.foundation.RetroText
import com.funapp.retroui.core.design.theme.RetroTheme
import org.jetbrains.compose.resources.stringResource
import retroui.shared.generated.resources.Res
import retroui.shared.generated.resources.badge_champion
import retroui.shared.generated.resources.badge_collector
import retroui.shared.generated.resources.badge_first_blood
import retroui.shared.generated.resources.badge_hunter
import retroui.shared.generated.resources.badge_veteran
import retroui.shared.generated.resources.badge_win_streak

private data class Badge(
    val label: String,
    val icon: ImageVector,
)

@Composable
private fun mockBadges(): List<Badge> = listOf(
    Badge(stringResource(Res.string.badge_first_blood), Icons.Filled.Star),
    Badge(stringResource(Res.string.badge_win_streak), Icons.Filled.ThumbUp),
    Badge(stringResource(Res.string.badge_collector), Icons.Filled.Check),
    Badge(stringResource(Res.string.badge_veteran), Icons.Filled.Person),
    Badge(stringResource(Res.string.badge_champion), Icons.Filled.Star),
    Badge(stringResource(Res.string.badge_hunter), Icons.Filled.Search),
)

/**
 * Grid of earned badges — two rows of compact icon + label tiles.
 */
@Composable
internal fun BadgeGrid() {
    val badges = mockBadges()
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
                text = badge.label,
                style = RetroTheme.typography.caption,
                color = colors.textPrimary,
            )
        }
    }
}