package com.funapp.retroui.features.home.presentation.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Face
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.funapp.retroui.core.design.components.feedback.RetroProgressBar
import com.funapp.retroui.core.design.components.feedback.RetroProgressColor
import com.funapp.retroui.core.design.components.feedback.RetroStatusLabel
import com.funapp.retroui.core.design.components.foundation.RetroText
import com.funapp.retroui.core.design.components.game.RetroAvatar
import com.funapp.retroui.core.design.components.surfaces.RetroCard
import com.funapp.retroui.core.design.theme.RetroTheme
import org.jetbrains.compose.resources.stringResource
import retroui.shared.generated.resources.Res
import retroui.shared.generated.resources.home_rank_value
import retroui.shared.generated.resources.home_xp_label
import retroui.shared.generated.resources.home_xp_value
import retroui.shared.generated.resources.player_display_name

/**
 * Player summary banner: avatar, name, rank chip and XP progress.
 * Tapping the card opens the profile.
 */
@Composable
internal fun PlayerBanner(onClick: () -> Unit, modifier: Modifier = Modifier) {
    RetroCard(modifier = modifier, onClick = onClick) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            RetroAvatar(
                size = RetroTheme.dimensions.avatarLG,
                icon = Icons.Filled.Face,
                circle = true,
            )
            Spacer(modifier = Modifier.width(RetroTheme.spacing.sm))
            Column(modifier = Modifier.weight(1f)) {
                RetroText(
                    text = stringResource(Res.string.player_display_name),
                    style = RetroTheme.typography.title,
                    color = RetroTheme.colors.textPrimary,
                )
                Spacer(modifier = Modifier.height(RetroTheme.spacing.xxs))
                RetroStatusLabel(
                    text = stringResource(Res.string.home_rank_value),
                    dotColor = RetroTheme.colors.secondary,
                )
            }
        }
        Spacer(modifier = Modifier.height(RetroTheme.spacing.md))
        Row(verticalAlignment = Alignment.CenterVertically) {
            RetroText(
                text = stringResource(Res.string.home_xp_label),
                style = RetroTheme.typography.caption,
                color = RetroTheme.colors.textSecondary,
            )
            Spacer(modifier = Modifier.weight(1f))
            RetroText(
                text = stringResource(Res.string.home_xp_value),
                style = RetroTheme.typography.label,
                color = RetroTheme.colors.textPrimary,
            )
        }
        Spacer(modifier = Modifier.height(RetroTheme.spacing.xs))
        RetroProgressBar(
            progress = 0.72f,
            color = RetroProgressColor.Xp,
        )
    }
}