package com.funapp.retroui.features.home.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import com.funapp.retroui.core.design.components.controls.RetroChip
import com.funapp.retroui.core.design.components.controls.RetroChipVariant
import com.funapp.retroui.core.design.components.feedback.RetroProgressBar
import com.funapp.retroui.core.design.components.feedback.RetroProgressColor
import com.funapp.retroui.core.design.components.foundation.RetroText
import com.funapp.retroui.core.design.components.surfaces.RetroPanel
import com.funapp.retroui.core.design.theme.RetroTheme
import com.funapp.retroui.core.design.animation.retroEntrance
import org.jetbrains.compose.resources.stringResource
import retroui.shared.generated.resources.Res
import retroui.shared.generated.resources.home_daily_subtitle
import retroui.shared.generated.resources.home_daily_title
import retroui.shared.generated.resources.home_reward_xp
import retroui.shared.generated.resources.home_view_all
import retroui.shared.generated.resources.quest_login_today
import retroui.shared.generated.resources.quest_play_rounds
import retroui.shared.generated.resources.quest_win_battles

/**
 * Daily quests preview with per-quest progress and a shortcut to the quests
 * screen.
 */
@Composable
internal fun DailyQuests(onViewAll: () -> Unit, modifier: Modifier = Modifier) {
    RetroPanel(
        title = stringResource(Res.string.home_daily_title),
        subtitle = stringResource(Res.string.home_daily_subtitle),
        trailing = {
            RetroChip(
                text = stringResource(Res.string.home_view_all),
                onClick = onViewAll,
                variant = RetroChipVariant.Outline,
            )
        },
        modifier = modifier,
    ) {
        QuestRow(
            icon = Icons.Filled.Star,
            label = stringResource(Res.string.quest_win_battles),
            reward = 50,
            progress = 0.67f,
            modifier = Modifier.retroEntrance(delayMillis = 40),
        )
        Spacer(modifier = Modifier.height(RetroTheme.spacing.md))
        QuestRow(
            icon = Icons.Filled.PlayArrow,
            label = stringResource(Res.string.quest_play_rounds),
            reward = 30,
            progress = 0.60f,
            modifier = Modifier.retroEntrance(delayMillis = 80),
        )
        Spacer(modifier = Modifier.height(RetroTheme.spacing.md))
        QuestRow(
            icon = Icons.Filled.Check,
            label = stringResource(Res.string.quest_login_today),
            reward = 20,
            progress = 1f,
            modifier = Modifier.retroEntrance(delayMillis = 120),
        )
    }
}

@Composable
private fun QuestRow(
    icon: ImageVector,
    label: String,
    reward: Int,
    progress: Float,
    modifier: Modifier = Modifier,
) {
    Column(modifier = modifier) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = RetroTheme.colors.textSecondary,
                modifier = Modifier.size(RetroTheme.dimensions.iconSM),
            )
            Spacer(modifier = Modifier.width(RetroTheme.spacing.sm))
            RetroText(
                text = label,
                style = RetroTheme.typography.bodySmall,
                color = RetroTheme.colors.textPrimary,
                modifier = Modifier.weight(1f),
            )
            RewardChip(reward = reward)
        }
        Spacer(modifier = Modifier.height(RetroTheme.spacing.xs))
        RetroProgressBar(
            progress = progress,
            color = RetroProgressColor.Xp,
            height = RetroTheme.dimensions.progressBarThin,
        )
    }
}

@Composable
private fun RewardChip(reward: Int) {
    Box(
        modifier = Modifier
            .clip(RetroTheme.shapeTokens.badge)
            .background(RetroTheme.colors.primary)
            .padding(horizontal = RetroTheme.spacing.xs, vertical = RetroTheme.spacing.xxs),
    ) {
        RetroText(
            text = stringResource(Res.string.home_reward_xp, reward),
            style = RetroTheme.typography.caption,
            color = RetroTheme.colors.onPrimary,
        )
    }
}