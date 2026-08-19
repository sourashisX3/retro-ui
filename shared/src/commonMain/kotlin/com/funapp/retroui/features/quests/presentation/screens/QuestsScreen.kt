package com.funapp.retroui.features.quests.presentation.screens

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.ThumbUp
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.funapp.retroui.core.design.components.foundation.RetroText
import com.funapp.retroui.core.design.components.surfaces.RetroScreen
import com.funapp.retroui.core.design.components.surfaces.RetroSection
import com.funapp.retroui.core.design.theme.RetroTheme
import com.funapp.retroui.features.quests.presentation.components.QuestRow
import org.jetbrains.compose.resources.stringResource
import retroui.shared.generated.resources.Res
import retroui.shared.generated.resources.quest_collect_cards
import retroui.shared.generated.resources.quest_login_today
import retroui.shared.generated.resources.quest_play_rounds
import retroui.shared.generated.resources.quest_reach_rank
import retroui.shared.generated.resources.quest_win_10
import retroui.shared.generated.resources.quest_win_battles
import retroui.shared.generated.resources.quests_daily_title
import retroui.shared.generated.resources.quests_weekly_title
import retroui.shared.generated.resources.screen_quests_subtitle
import retroui.shared.generated.resources.screen_quests_title

/**
 * Quests. Daily and weekly quest lists with progress and claim actions.
 */
@Composable
fun QuestsScreen(
    onGoHome: () -> Unit,
    modifier: Modifier = Modifier,
) {
    RetroScreen(modifier = modifier) {
        item {
            RetroText(
                text = stringResource(Res.string.screen_quests_title),
                style = RetroTheme.typography.heading,
                color = RetroTheme.colors.textPrimary,
            )
            RetroText(
                text = stringResource(Res.string.screen_quests_subtitle),
                style = RetroTheme.typography.caption,
                color = RetroTheme.colors.textMuted,
            )
        }
        item {
            Spacer(modifier = Modifier.height(RetroTheme.spacing.lg))
        }
        item {
            RetroSection(title = stringResource(Res.string.quests_daily_title)) {
                QuestRow(
                    icon = Icons.Filled.Star,
                    label = stringResource(Res.string.quest_win_battles),
                    reward = 50,
                    progress = 0.67f,
                    claimable = false,
                    onClaim = {},
                )
                Spacer(modifier = Modifier.height(RetroTheme.spacing.sm))
                QuestRow(
                    icon = Icons.Filled.PlayArrow,
                    label = stringResource(Res.string.quest_play_rounds),
                    reward = 30,
                    progress = 1f,
                    claimable = true,
                    onClaim = {},
                )
                Spacer(modifier = Modifier.height(RetroTheme.spacing.sm))
                QuestRow(
                    icon = Icons.Filled.Check,
                    label = stringResource(Res.string.quest_login_today),
                    reward = 20,
                    progress = 1f,
                    claimable = true,
                    onClaim = {},
                )
            }
        }
        item {
            Spacer(modifier = Modifier.height(RetroTheme.spacing.lg))
        }
        item {
            RetroSection(title = stringResource(Res.string.quests_weekly_title)) {
                QuestRow(
                    icon = Icons.Filled.ThumbUp,
                    label = stringResource(Res.string.quest_win_10),
                    reward = 150,
                    progress = 0.3f,
                    claimable = false,
                    onClaim = {},
                )
                Spacer(modifier = Modifier.height(RetroTheme.spacing.sm))
                QuestRow(
                    icon = Icons.Filled.Star,
                    label = stringResource(Res.string.quest_reach_rank),
                    reward = 200,
                    progress = 0.0f,
                    claimable = false,
                    onClaim = {},
                )
                Spacer(modifier = Modifier.height(RetroTheme.spacing.sm))
                QuestRow(
                    icon = Icons.Filled.ThumbUp,
                    label = stringResource(Res.string.quest_collect_cards),
                    reward = 100,
                    progress = 0.5f,
                    claimable = false,
                    onClaim = {},
                )
            }
        }
    }
}