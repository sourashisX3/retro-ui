package com.funapp.retroui.features.quests.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import com.funapp.retroui.core.design.components.controls.RetroChip
import com.funapp.retroui.core.design.components.feedback.RetroProgressBar
import com.funapp.retroui.core.design.components.feedback.RetroProgressColor
import com.funapp.retroui.core.design.components.feedback.RetroStatusLabel
import com.funapp.retroui.core.design.components.foundation.RetroText
import com.funapp.retroui.core.design.components.surfaces.RetroCard
import com.funapp.retroui.core.design.theme.RetroTheme
import org.jetbrains.compose.resources.stringResource
import retroui.shared.generated.resources.Res
import retroui.shared.generated.resources.quest_reward_xp
import retroui.shared.generated.resources.quests_claim
import retroui.shared.generated.resources.quests_in_progress

/**
 * One quest card: icon, label, progress bar, reward and a CLAIM / IN
 * PROGRESS status action.
 */
@Composable
internal fun QuestRow(
    icon: ImageVector,
    label: String,
    reward: Int,
    progress: Float,
    claimable: Boolean,
    onClaim: () -> Unit,
) {
    val colors = RetroTheme.colors
    RetroCard(contentPadding = RetroTheme.spacing.md) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = colors.textSecondary,
                modifier = Modifier.size(RetroTheme.dimensions.iconSM),
            )
            Spacer(modifier = Modifier.width(RetroTheme.spacing.sm))
            Column(modifier = Modifier.weight(1f)) {
                RetroText(
                    text = label,
                    style = RetroTheme.typography.bodySmall,
                    color = colors.textPrimary,
                )
                Spacer(modifier = Modifier.height(RetroTheme.spacing.xs))
                RetroProgressBar(
                    progress = progress,
                    color = RetroProgressColor.Xp,
                    height = RetroTheme.dimensions.progressBarThin,
                )
            }
            Spacer(modifier = Modifier.width(RetroTheme.spacing.sm))
            if (claimable) {
                RetroChip(
                    text = stringResource(Res.string.quests_claim),
                    onClick = onClaim,
                    selected = true,
                )
            } else {
                RetroStatusLabel(
                    text = stringResource(Res.string.quests_in_progress),
                    dotColor = colors.textMuted,
                    container = colors.surfaceVariant,
                )
            }
        }
        Spacer(modifier = Modifier.height(RetroTheme.spacing.sm))
        Box(
            modifier = Modifier
                .clip(RetroTheme.shapeTokens.badge)
                .background(colors.primary)
                .padding(horizontal = RetroTheme.spacing.xs, vertical = RetroTheme.spacing.xxs),
        ) {
            RetroText(
                text = stringResource(Res.string.quest_reward_xp, reward),
                style = RetroTheme.typography.caption,
                color = colors.onPrimary,
            )
        }
    }
}