package com.funapp.retroui.features.leaderboard.presentation.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.funapp.retroui.core.ui.animation.RetroEntranceStyle
import com.funapp.retroui.core.ui.animation.retroEntrance
import com.funapp.retroui.core.ui.components.foundation.RetroText
import com.funapp.retroui.core.ui.components.foundation.retroHardShadow
import com.funapp.retroui.core.ui.components.surfaces.RetroScreen
import com.funapp.retroui.core.ui.icons.RetroIcons
import com.funapp.retroui.core.ui.icons.Trophy
import com.funapp.retroui.core.ui.theme.RetroTheme
import com.funapp.retroui.core.utils.asString
import com.funapp.retroui.features.leaderboard.data.LeaderboardEntry
import com.funapp.retroui.features.leaderboard.data.getMockLeaderboard
import org.jetbrains.compose.resources.stringResource
import retroui.shared.generated.resources.Res
import retroui.shared.generated.resources.lb_you
import retroui.shared.generated.resources.screen_leaderboard_subtitle
import retroui.shared.generated.resources.screen_leaderboard_title

/**
 * Arena leaderboard. Rank table with trophy-colored top-3 plates; the local
 * player's row is highlighted. Rows cascade in with a Pop entrance.
 */
@Composable
fun LeaderboardScreen(
    modifier: Modifier = Modifier,
) {
    val entries = remember { getMockLeaderboard() }

    RetroScreen(modifier = modifier) {
        item {
            Column(modifier = Modifier.fillMaxWidth()) {
                RetroText(
                    text = stringResource(Res.string.screen_leaderboard_title),
                    style = RetroTheme.typography.heading,
                    color = RetroTheme.colors.textPrimary,
                    modifier = Modifier.retroEntrance(style = RetroEntranceStyle.Stomp),
                )
                RetroText(
                    text = stringResource(Res.string.screen_leaderboard_subtitle),
                    style = RetroTheme.typography.caption,
                    color = RetroTheme.colors.textSecondary,
                    modifier = Modifier.retroEntrance(
                        style = RetroEntranceStyle.Stomp,
                        delayMillis = 80,
                    ),
                )
            }
        }
        items(entries.size, key = { entries[it].rank }) { index ->
            LeaderboardRow(
                entry = entries[index],
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = RetroTheme.spacing.md)
                    .retroEntrance(
                        style = RetroEntranceStyle.Pop,
                        delayMillis = index * 40,
                    ),
            )
        }
        item {
            Spacer(modifier = Modifier.height(RetroTheme.spacing.md))
        }
    }
}

@Composable
private fun LeaderboardRow(
    entry: LeaderboardEntry,
    modifier: Modifier = Modifier,
) {
    val colors = RetroTheme.colors
    val plateColor = when (entry.rank) {
        1 -> colors.secondary
        2 -> colors.info
        3 -> colors.warning
        else -> colors.surfaceVariant
    }
    val rowColor = if (entry.isYou) colors.primaryContainer else colors.surface

    Row(
        modifier = modifier
            .background(rowColor)
            .border(RetroTheme.borders.thin, colors.outline, RetroTheme.shapeTokens.chip)
            .padding(RetroTheme.spacing.sm),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        RetroText(
            text = entry.rank.toString(),
            style = RetroTheme.typography.score,
            color = if (entry.isYou) colors.onPrimaryContainer else colors.textPrimary,
            modifier = Modifier.width(32.dp),
        )
        Box(
            modifier = Modifier
                .size(32.dp)
                .retroHardShadow(
                    offsetX = 2.dp,
                    offsetY = 2.dp,
                    color = colors.outline,
                    shape = CircleShape,
                )
                .clip(CircleShape)
                .background(plateColor)
                .border(RetroTheme.borders.default, colors.outlineStrong, CircleShape),
            contentAlignment = Alignment.Center,
        ) {
            Icon(
                imageVector = entry.icon,
                contentDescription = null,
                tint = colors.onSurfaceVariant,
                modifier = Modifier.size(18.dp),
            )
        }
        Spacer(modifier = Modifier.width(RetroTheme.spacing.sm))
        Row(
            modifier = Modifier.weight(1f),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            RetroText(
                text = entry.name.asString(),
                style = RetroTheme.typography.title,
                color = if (entry.isYou) colors.onPrimaryContainer else colors.textPrimary,
            )
            if (entry.isYou) {
                Spacer(modifier = Modifier.width(RetroTheme.spacing.xs))
                YouChip()
            }
        }
        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(
                imageVector = RetroIcons.Trophy,
                contentDescription = null,
                tint = if (entry.isYou) colors.onPrimaryContainer else colors.secondary,
                modifier = Modifier.size(16.dp),
            )
            Spacer(modifier = Modifier.width(RetroTheme.spacing.xxs))
            RetroText(
                text = entry.trophies.toString(),
                style = RetroTheme.typography.label,
                color = if (entry.isYou) colors.onPrimaryContainer else colors.textSecondary,
            )
        }
    }
}

@Composable
private fun YouChip() {
    RetroText(
        text = stringResource(Res.string.lb_you),
        style = RetroTheme.typography.caption,
        color = RetroTheme.colors.primaryContainer,
        modifier = Modifier
            .clip(RetroTheme.shapeTokens.chip)
            .background(RetroTheme.colors.onPrimaryContainer)
            .padding(
                horizontal = RetroTheme.spacing.xs,
                vertical = 1.dp,
            ),
    )
}