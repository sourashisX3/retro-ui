package com.funapp.retroui.features.profile.presentation.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.ThumbUp
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.funapp.retroui.core.design.components.feedback.RetroProgressColor
import com.funapp.retroui.core.design.components.foundation.RetroText
import com.funapp.retroui.core.design.components.surfaces.RetroScreen
import com.funapp.retroui.core.design.components.surfaces.RetroSection
import com.funapp.retroui.core.design.components.surfaces.RetroStatCard
import com.funapp.retroui.core.design.theme.RetroTheme
import com.funapp.retroui.features.profile.presentation.components.BadgeGrid
import com.funapp.retroui.features.profile.presentation.components.ProfileBanner
import org.jetbrains.compose.resources.stringResource
import retroui.shared.generated.resources.Res
import retroui.shared.generated.resources.profile_badges_title
import retroui.shared.generated.resources.profile_record_damage
import retroui.shared.generated.resources.profile_record_favorite
import retroui.shared.generated.resources.profile_record_streak
import retroui.shared.generated.resources.profile_records_title
import retroui.shared.generated.resources.profile_stat_losses
import retroui.shared.generated.resources.profile_stat_winrate
import retroui.shared.generated.resources.profile_stat_wins
import retroui.shared.generated.resources.profile_value_damage
import retroui.shared.generated.resources.profile_value_favorite
import retroui.shared.generated.resources.profile_value_streak
import retroui.shared.generated.resources.profile_value_wins
import retroui.shared.generated.resources.profile_value_losses
import retroui.shared.generated.resources.profile_value_winrate
import retroui.shared.generated.resources.screen_profile_subtitle
import retroui.shared.generated.resources.screen_profile_title

/**
 * Profile. Player banner, combat stats, badges and record rows.
 */
@Composable
fun ProfileScreen(
    onGoSettings: () -> Unit,
    onGoHome: () -> Unit,
    modifier: Modifier = Modifier,
) {
    RetroScreen(modifier = modifier) {
        item {
            RetroText(
                text = stringResource(Res.string.screen_profile_title),
                style = RetroTheme.typography.heading,
                color = RetroTheme.colors.textPrimary,
            )
            RetroText(
                text = stringResource(Res.string.screen_profile_subtitle),
                style = RetroTheme.typography.caption,
                color = RetroTheme.colors.textMuted,
            )
        }
        item {
            Spacer(modifier = Modifier.height(RetroTheme.spacing.lg))
        }
        item {
            ProfileBanner()
        }
        item {
            Spacer(modifier = Modifier.height(RetroTheme.spacing.lg))
        }
        item {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {
                RetroStatCard(
                    label = stringResource(Res.string.profile_stat_wins),
                    value = stringResource(Res.string.profile_value_wins),
                    icon = Icons.Filled.Star,
                    progress = 0.69f,
                    progressColor = RetroProgressColor.Health,
                    width = 100.dp,
                )
                RetroStatCard(
                    label = stringResource(Res.string.profile_stat_losses),
                    value = stringResource(Res.string.profile_value_losses),
                    icon = Icons.Filled.PlayArrow,
                    progress = 0.31f,
                    progressColor = RetroProgressColor.Danger,
                    width = 100.dp,
                )
                RetroStatCard(
                    label = stringResource(Res.string.profile_stat_winrate),
                    value = stringResource(Res.string.profile_value_winrate),
                    icon = Icons.Filled.ThumbUp,
                    progress = 0.69f,
                    progressColor = RetroProgressColor.Info,
                    width = 100.dp,
                )
            }
        }
        item {
            Spacer(modifier = Modifier.height(RetroTheme.spacing.lg))
        }
        item {
            RetroSection(title = stringResource(Res.string.profile_badges_title)) {
                BadgeGrid()
            }
        }
        item {
            Spacer(modifier = Modifier.height(RetroTheme.spacing.lg))
        }
        item {
            RetroSection(title = stringResource(Res.string.profile_records_title)) {
                RecordRow(
                    label = stringResource(Res.string.profile_record_streak),
                    value = stringResource(Res.string.profile_value_streak),
                )
                Spacer(modifier = Modifier.height(RetroTheme.spacing.sm))
                RecordRow(
                    label = stringResource(Res.string.profile_record_damage),
                    value = stringResource(Res.string.profile_value_damage),
                )
                Spacer(modifier = Modifier.height(RetroTheme.spacing.sm))
                RecordRow(
                    label = stringResource(Res.string.profile_record_favorite),
                    value = stringResource(Res.string.profile_value_favorite),
                )
            }
        }
    }
}

@Composable
private fun RecordRow(
    label: String,
    value: String,
) {
    Row(modifier = Modifier.fillMaxWidth()) {
        RetroText(
            text = label,
            style = RetroTheme.typography.bodySmall,
            color = RetroTheme.colors.textSecondary,
            modifier = Modifier.weight(1f),
        )
        Spacer(modifier = Modifier.width(RetroTheme.spacing.sm))
        RetroText(
            text = value,
            style = RetroTheme.typography.label,
            color = RetroTheme.colors.textPrimary,
        )
    }
}