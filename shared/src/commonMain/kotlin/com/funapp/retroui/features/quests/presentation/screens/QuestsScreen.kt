package com.funapp.retroui.features.quests.presentation.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import com.funapp.retroui.core.ui.icons.Check
import com.funapp.retroui.core.ui.icons.PlayArrow
import com.funapp.retroui.core.ui.icons.Star
import com.funapp.retroui.core.ui.icons.ThumbUp
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import com.funapp.retroui.core.ui.animation.RetroEntranceStyle
import com.funapp.retroui.core.ui.animation.retroCascade
import com.funapp.retroui.core.ui.animation.retroEntrance
import com.funapp.retroui.core.ui.components.foundation.RetroText
import com.funapp.retroui.core.ui.components.surfaces.RetroScreen
import com.funapp.retroui.core.ui.components.surfaces.RetroSection
import com.funapp.retroui.core.ui.theme.RetroTheme
import com.funapp.retroui.core.utils.asString
import com.funapp.retroui.features.quests.data.getMockDailyQuests
import com.funapp.retroui.features.quests.data.getMockWeeklyQuests
import com.funapp.retroui.features.quests.presentation.components.QuestRow
import org.jetbrains.compose.resources.stringResource
import retroui.shared.generated.resources.Res
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
    val dailyQuests = remember { getMockDailyQuests() }
    val weeklyQuests = remember { getMockWeeklyQuests() }

    RetroScreen(modifier = modifier) {
        item {
            Column(modifier = Modifier.retroEntrance(delayMillis = 0)) {
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
        }
        item {
            Spacer(modifier = Modifier.height(RetroTheme.spacing.lg))
        }
        item {
            RetroSection(
                title = stringResource(Res.string.quests_daily_title),
                modifier = Modifier.retroEntrance(delayMillis = 60),
            ) {
                dailyQuests.forEachIndexed { index, quest ->
                    if (index > 0) {
                        Spacer(modifier = Modifier.height(RetroTheme.spacing.sm))
                    }
                    QuestRow(icon = quest.icon,label = quest.label.asString(),reward = quest.reward,progress = quest.progress,claimable = quest.claimable,onClaim = {},modifier = Modifier.retroEntrance(style = RetroEntranceStyle.Pop, delayMillis = 60 + retroCascade(index, stepMs = 40)),)
                }
            }
        }
        item {
            Spacer(modifier = Modifier.height(RetroTheme.spacing.lg))
        }
        item {
            RetroSection(
                title = stringResource(Res.string.quests_weekly_title),
                modifier = Modifier.retroEntrance(delayMillis = 120),
            ) {
                weeklyQuests.forEachIndexed { index, quest ->
                    if (index > 0) {
                        Spacer(modifier = Modifier.height(RetroTheme.spacing.sm))
                    }
                    QuestRow(icon = quest.icon,label = quest.label.asString(),reward = quest.reward,progress = quest.progress,claimable = quest.claimable,onClaim = {},modifier = Modifier.retroEntrance(style = RetroEntranceStyle.Pop, delayMillis = 120 + retroCascade(index, stepMs = 40)),)
                }
            }
        }
    }
}