package com.funapp.retroui.features.profile.data
import com.funapp.retroui.core.ui.icons.RetroIcons

import com.funapp.retroui.core.ui.icons.Check
import com.funapp.retroui.core.ui.icons.Person
import com.funapp.retroui.core.ui.icons.Search
import com.funapp.retroui.core.ui.icons.Star
import com.funapp.retroui.core.ui.icons.ThumbUp
import androidx.compose.ui.graphics.vector.ImageVector
import com.funapp.retroui.core.utils.UiText
import com.funapp.retroui.core.utils.UiText.ResId
import retroui.shared.generated.resources.Res
import retroui.shared.generated.resources.badge_champion
import retroui.shared.generated.resources.badge_collector
import retroui.shared.generated.resources.badge_first_blood
import retroui.shared.generated.resources.badge_hunter
import retroui.shared.generated.resources.badge_veteran
import retroui.shared.generated.resources.badge_win_streak
import retroui.shared.generated.resources.profile_value_damage
import retroui.shared.generated.resources.profile_value_favorite
import retroui.shared.generated.resources.profile_value_losses
import retroui.shared.generated.resources.profile_value_streak
import retroui.shared.generated.resources.profile_value_winrate
import retroui.shared.generated.resources.profile_value_wins

internal data class Badge(
    val label: UiText,
    val icon: ImageVector,
)

internal fun getMockBadges(): List<Badge> = listOf(
    Badge(ResId(Res.string.badge_first_blood), RetroIcons.Star),
    Badge(ResId(Res.string.badge_win_streak), RetroIcons.ThumbUp),
    Badge(ResId(Res.string.badge_collector), RetroIcons.Check),
    Badge(ResId(Res.string.badge_veteran), RetroIcons.Person),
    Badge(ResId(Res.string.badge_champion), RetroIcons.Star),
    Badge(ResId(Res.string.badge_hunter), RetroIcons.Search),
)

data class ProfileStats(
    val wins: UiText,
    val losses: UiText,
    val winrate: UiText,
)

internal fun getMockProfileStats(): ProfileStats = ProfileStats(
    wins = ResId(Res.string.profile_value_wins),
    losses = ResId(Res.string.profile_value_losses),
    winrate = ResId(Res.string.profile_value_winrate),
)

data class ProfileRecords(
    val streak: UiText,
    val damage: UiText,
    val favorite: UiText,
)

internal fun getMockProfileRecords(): ProfileRecords = ProfileRecords(
    streak = ResId(Res.string.profile_value_streak),
    damage = ResId(Res.string.profile_value_damage),
    favorite = ResId(Res.string.profile_value_favorite),
)
