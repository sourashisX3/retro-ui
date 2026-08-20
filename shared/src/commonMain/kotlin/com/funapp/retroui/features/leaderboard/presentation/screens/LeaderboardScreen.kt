package com.funapp.retroui.features.leaderboard.presentation.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.funapp.retroui.core.ui.animation.RetroEntranceStyle
import com.funapp.retroui.core.ui.animation.retroEntrance
import com.funapp.retroui.core.ui.components.feedback.RetroShine
import com.funapp.retroui.core.ui.components.foundation.RetroText
import com.funapp.retroui.core.ui.components.foundation.retroHardShadow
import com.funapp.retroui.core.ui.components.surfaces.RetroScreen
import com.funapp.retroui.core.ui.icons.RetroIcons
import com.funapp.retroui.core.ui.icons.Trophy
import com.funapp.retroui.core.ui.theme.RetroTheme
import com.funapp.retroui.core.utils.asString
import com.funapp.retroui.core.di.LocalAppContainer
import com.funapp.retroui.features.leaderboard.data.LeaderboardEntry
import com.funapp.retroui.features.leaderboard.data.LeaderboardRepository
import org.jetbrains.compose.resources.stringResource
import retroui.shared.generated.resources.Res
import retroui.shared.generated.resources.lb_you
import retroui.shared.generated.resources.screen_leaderboard_subtitle
import retroui.shared.generated.resources.screen_leaderboard_title

/**
 * Arena leaderboard — modern podium layout: the top three stand on gold /
 * silver / bronze blocks, everyone else lists in compact rows. The local
 * player's row is highlighted wherever they rank.
 */
@Composable
fun LeaderboardScreen(
    modifier: Modifier = Modifier,
    repository: LeaderboardRepository = LocalAppContainer.current.leaderboardRepository,
) {
    var entries by remember { mutableStateOf<List<LeaderboardEntry>>(emptyList()) }
    LaunchedEffect(repository) {
        entries = repository.getLeaderboard()
    }

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
        if (entries.isNotEmpty()) {
            val podium = listOf(
                entries.getOrNull(1),
                entries.getOrNull(0),
                entries.getOrNull(2),
            )
            item {
                PodiumRow(
                    podium = podium,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = RetroTheme.spacing.lg)
                        .retroEntrance(style = RetroEntranceStyle.Stomp, delayMillis = 120),
                )
            }
        }
        items(entries.size, key = { entries[it].rank }) { index ->
            CompactRankRow(
                entry = entries[index],
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = RetroTheme.spacing.sm)
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

/** Gold / silver / bronze blocks: 2nd - 1st - 3rd, with the winner raised. */
@Composable
private fun PodiumRow(
    podium: List<LeaderboardEntry?>,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(RetroTheme.spacing.sm),
        verticalAlignment = Alignment.Bottom,
    ) {
        podium.forEachIndexed { index, entry ->
            if (entry == null) {
                Spacer(modifier = Modifier.weight(1f))
            } else {
                PodiumColumn(
                    entry = entry,
                    modifier = Modifier.weight(1f),
                )
            }
        }
    }
}

@Composable
private fun PodiumColumn(
    entry: LeaderboardEntry,
    modifier: Modifier = Modifier,
) {
    val colors = RetroTheme.colors
    val podiumColor = when (entry.rank) {
        1 -> colors.secondary
        2 -> colors.info
        3 -> colors.warning
        else -> colors.surfaceVariant
    }
    val onPodium = when (entry.rank) {
        1 -> colors.onSecondary
        2 -> colors.onInfo
        3 -> colors.onWarning
        else -> colors.onSurfaceVariant
    }
    val blockHeight = when (entry.rank) {
        1 -> 84.dp
        2 -> 60.dp
        else -> 44.dp
    }

    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        RetroText(
            text = entry.name.asString(),
            style = RetroTheme.typography.caption,
            color = colors.textSecondary,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            textAlign = androidx.compose.ui.text.style.TextAlign.Center,
            modifier = Modifier.fillMaxWidth(),
        )
        Spacer(modifier = Modifier.height(RetroTheme.spacing.xxs))
        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(
                imageVector = RetroIcons.Trophy,
                contentDescription = null,
                tint = colors.textMuted,
                modifier = Modifier.size(12.dp),
            )
            Spacer(modifier = Modifier.width(RetroTheme.spacing.xxs))
            RetroText(
                text = entry.trophies.toString(),
                style = RetroTheme.typography.caption,
                color = colors.textMuted,
            )
        }
        Spacer(modifier = Modifier.height(RetroTheme.spacing.sm))
        Box(
            modifier = Modifier
                .size(48.dp)
                .retroHardShadow(
                    offsetX = 2.dp,
                    offsetY = 3.dp,
                    color = colors.shadow,
                    shape = CircleShape,
                )
                .clip(CircleShape)
                .background(colors.surface)
                .border(RetroTheme.borders.default, podiumColor, CircleShape),
            contentAlignment = Alignment.Center,
        ) {
            Icon(
                imageVector = entry.icon,
                contentDescription = null,
                tint = podiumColor,
                modifier = Modifier.size(26.dp),
            )
        }
        Spacer(modifier = Modifier.height(RetroTheme.spacing.sm))
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(blockHeight)
                .retroHardShadow(
                    offsetX = 2.dp,
                    offsetY = 3.dp,
                    color = colors.shadow,
                )
                .background(podiumColor)
                .border(RetroTheme.borders.default, colors.outlineStrong),
            contentAlignment = Alignment.Center,
        ) {
            RetroText(
                text = entry.rank.toString(),
                style = RetroTheme.typography.score,
                color = onPodium,
            )
        }
    }
}

/** Compact list row for ranks 4+: small avatar, small name, trophy count. */
@Composable
private fun CompactRankRow(
    entry: LeaderboardEntry,
    modifier: Modifier = Modifier,
) {
    val colors = RetroTheme.colors
    val rowColor = if (entry.isYou) colors.primaryContainer else colors.surface

    Box(modifier = modifier) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .retroHardShadow(
                    offsetX = 2.dp,
                    offsetY = 2.dp,
                    color = colors.shadow,
                    shape = RetroTheme.shapeTokens.chip,
                )
                .background(rowColor)
                .border(RetroTheme.borders.default, colors.outlineStrong, RetroTheme.shapeTokens.chip)
                .padding(horizontal = RetroTheme.spacing.sm, vertical = RetroTheme.spacing.xs),
            verticalAlignment = Alignment.CenterVertically,
        ) {
        RetroText(
            text = entry.rank.toString(),
            style = RetroTheme.typography.caption,
            color = if (entry.isYou) {
                colors.onPrimaryContainer
            } else {
                when (entry.rank) {
                    1 -> colors.secondary
                    2 -> colors.info
                    3 -> colors.warning
                    else -> colors.textSecondary
                }
            },
            modifier = Modifier.width(24.dp),
        )
        Box(
            modifier = Modifier
                .size(24.dp)
                .retroHardShadow(
                    offsetX = 1.dp,
                    offsetY = 2.dp,
                    color = colors.shadow,
                    shape = CircleShape,
                )
                .clip(CircleShape)
                .background(colors.surfaceVariant)
                .border(RetroTheme.borders.thin, colors.outlineStrong, CircleShape),
            contentAlignment = Alignment.Center,
        ) {
            Icon(
                imageVector = entry.icon,
                contentDescription = null,
                tint = colors.onSurfaceVariant,
                modifier = Modifier.size(14.dp),
            )
        }
        Spacer(modifier = Modifier.width(RetroTheme.spacing.sm))
        RetroText(
            text = entry.name.asString(),
            style = RetroTheme.typography.bodySmall,
            color = if (entry.isYou) colors.onPrimaryContainer else colors.textPrimary,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier.weight(1f),
        )
        if (entry.isYou) {
            Spacer(modifier = Modifier.width(RetroTheme.spacing.xs))
            RetroText(
                text = stringResource(Res.string.lb_you),
                style = RetroTheme.typography.caption,
                color = colors.primaryContainer,
                modifier = Modifier
                    .clip(RetroTheme.shapeTokens.chip)
                    .background(colors.onPrimaryContainer)
                    .padding(
                        horizontal = RetroTheme.spacing.xs,
                        vertical = 1.dp,
                    ),
            )
        }
        Spacer(modifier = Modifier.width(RetroTheme.spacing.sm))
        Icon(
            imageVector = RetroIcons.Trophy,
            contentDescription = null,
            tint = colors.secondary,
            modifier = Modifier.size(14.dp),
        )
        Spacer(modifier = Modifier.width(RetroTheme.spacing.xxs))
        RetroText(
            text = entry.trophies.toString(),
            style = RetroTheme.typography.caption,
            color = if (entry.isYou) colors.onPrimaryContainer else colors.textSecondary,
        )
        }
        if (entry.isYou) {
            RetroShine(shape = RetroTheme.shapeTokens.chip)
        }
    }
}