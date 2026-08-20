package com.funapp.retroui.features.profile.presentation.screens
import com.funapp.retroui.core.ui.icons.RetroIcons

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import com.funapp.retroui.core.ui.icons.PlayArrow
import com.funapp.retroui.core.ui.icons.Star
import com.funapp.retroui.core.ui.icons.ThumbUp
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.funapp.retroui.core.ui.animation.RetroEntranceStyle
import com.funapp.retroui.core.ui.animation.retroEntrance
import com.funapp.retroui.core.ui.components.feedback.RetroProgressColor
import com.funapp.retroui.core.ui.components.foundation.RetroText
import com.funapp.retroui.core.ui.components.surfaces.RetroScreen
import com.funapp.retroui.core.ui.components.surfaces.RetroSection
import com.funapp.retroui.core.ui.components.surfaces.RetroStatCard
import com.funapp.retroui.core.ui.theme.RetroTheme
import com.funapp.retroui.core.utils.asString
import com.funapp.retroui.features.profile.data.getMockProfileRecords
import com.funapp.retroui.features.profile.data.getMockProfileStats
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
    val profileStats = remember { getMockProfileStats() }
    val profileRecords = remember { getMockProfileRecords() }

    RetroScreen(modifier = modifier) {
        item {
            Column(modifier = Modifier.retroEntrance(delayMillis = 0)) {
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
        }
        item {
            Spacer(modifier = Modifier.height(RetroTheme.spacing.lg))
        }
        item {
            ProfileBanner(modifier = Modifier.retroEntrance(delayMillis = 60))
        }
        item {
            Spacer(modifier = Modifier.height(RetroTheme.spacing.lg))
        }
        item {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .retroEntrance(style = RetroEntranceStyle.Pop, delayMillis = 120),
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {
                RetroStatCard(
                    label = stringResource(Res.string.profile_stat_wins),
                    value = profileStats.wins.asString(),
                    icon = RetroIcons.Star,
                    progress = 0.69f,
                    progressColor = RetroProgressColor.Health,
                    width = 100.dp,
                )
                RetroStatCard(
                    label = stringResource(Res.string.profile_stat_losses),
                    value = profileStats.losses.asString(),
                    icon = RetroIcons.PlayArrow,
                    progress = 0.31f,
                    progressColor = RetroProgressColor.Danger,
                    width = 100.dp,
                )
                RetroStatCard(
                    label = stringResource(Res.string.profile_stat_winrate),
                    value = profileStats.winrate.asString(),
                    icon = RetroIcons.ThumbUp,
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
            RetroSection(
                title = stringResource(Res.string.profile_badges_title),
                modifier = Modifier.retroEntrance(style = RetroEntranceStyle.Stomp, delayMillis = 180),
            ) {
                BadgeGrid()
            }
        }
        item {
            Spacer(modifier = Modifier.height(RetroTheme.spacing.lg))
        }
        item {
            RetroSection(
                title = stringResource(Res.string.profile_records_title),
                modifier = Modifier.retroEntrance(delayMillis = 240),
            ) {
                RecordRow(
                    label = stringResource(Res.string.profile_record_streak),
                    value = profileRecords.streak.asString(),
                )
                Spacer(modifier = Modifier.height(RetroTheme.spacing.sm))
                RecordRow(
                    label = stringResource(Res.string.profile_record_damage),
                    value = profileRecords.damage.asString(),
                )
                Spacer(modifier = Modifier.height(RetroTheme.spacing.sm))
                RecordRow(
                    label = stringResource(Res.string.profile_record_favorite),
                    value = profileRecords.favorite.asString(),
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