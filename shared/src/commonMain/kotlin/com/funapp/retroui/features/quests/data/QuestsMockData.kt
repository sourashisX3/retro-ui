package com.funapp.retroui.features.quests.data
import com.funapp.retroui.core.ui.icons.RetroIcons

import com.funapp.retroui.core.ui.icons.Check
import com.funapp.retroui.core.ui.icons.PlayArrow
import com.funapp.retroui.core.ui.icons.Star
import com.funapp.retroui.core.ui.icons.ThumbUp
import androidx.compose.ui.graphics.vector.ImageVector
import com.funapp.retroui.core.utils.UiText
import com.funapp.retroui.core.utils.UiText.ResId
import retroui.shared.generated.resources.Res
import retroui.shared.generated.resources.quest_collect_cards
import retroui.shared.generated.resources.quest_login_today
import retroui.shared.generated.resources.quest_play_rounds
import retroui.shared.generated.resources.quest_reach_rank
import retroui.shared.generated.resources.quest_win_10
import retroui.shared.generated.resources.quest_win_battles

data class QuestItem(
    val icon: ImageVector,
    val label: UiText,
    val reward: Int,
    val progress: Float,
    val claimable: Boolean,
)

internal fun getMockDailyQuests(): List<QuestItem> = listOf(
    QuestItem(
        icon = RetroIcons.Star,
        label = ResId(Res.string.quest_win_battles),
        reward = 50,
        progress = 0.67f,
        claimable = false,
    ),
    QuestItem(
        icon = RetroIcons.PlayArrow,
        label = ResId(Res.string.quest_play_rounds),
        reward = 30,
        progress = 1f,
        claimable = true,
    ),
    QuestItem(
        icon = RetroIcons.Check,
        label = ResId(Res.string.quest_login_today),
        reward = 20,
        progress = 1f,
        claimable = true,
    ),
)

internal fun getMockWeeklyQuests(): List<QuestItem> = listOf(
    QuestItem(
        icon = RetroIcons.ThumbUp,
        label = ResId(Res.string.quest_win_10),
        reward = 150,
        progress = 0.3f,
        claimable = false,
    ),
    QuestItem(
        icon = RetroIcons.Star,
        label = ResId(Res.string.quest_reach_rank),
        reward = 200,
        progress = 0.0f,
        claimable = false,
    ),
    QuestItem(
        icon = RetroIcons.ThumbUp,
        label = ResId(Res.string.quest_collect_cards),
        reward = 100,
        progress = 0.5f,
        claimable = false,
    ),
)
