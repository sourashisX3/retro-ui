package com.funapp.retroui.features.leaderboard.data

import com.funapp.retroui.core.ui.icons.RetroIcons
import com.funapp.retroui.core.ui.icons.Crown
import com.funapp.retroui.core.ui.icons.Eye
import com.funapp.retroui.core.ui.icons.Face
import com.funapp.retroui.core.ui.icons.Favorite
import com.funapp.retroui.core.ui.icons.Flag
import com.funapp.retroui.core.ui.icons.Gamepad
import com.funapp.retroui.core.ui.icons.Joystick
import com.funapp.retroui.core.ui.icons.Shield
import com.funapp.retroui.core.ui.icons.Smile
import com.funapp.retroui.core.ui.icons.Star
import com.funapp.retroui.core.ui.icons.Sword
import com.funapp.retroui.core.ui.icons.Warning
import com.funapp.retroui.core.utils.UiText
import com.funapp.retroui.core.utils.UiText.ResId
import androidx.compose.ui.graphics.vector.ImageVector
import retroui.shared.generated.resources.Res
import retroui.shared.generated.resources.lb_name_01
import retroui.shared.generated.resources.lb_name_02
import retroui.shared.generated.resources.lb_name_03
import retroui.shared.generated.resources.lb_name_04
import retroui.shared.generated.resources.lb_name_05
import retroui.shared.generated.resources.lb_name_06
import retroui.shared.generated.resources.lb_name_07
import retroui.shared.generated.resources.lb_name_08
import retroui.shared.generated.resources.lb_name_09
import retroui.shared.generated.resources.lb_name_10
import retroui.shared.generated.resources.lb_name_11
import retroui.shared.generated.resources.lb_name_12

data class LeaderboardEntry(
    val rank: Int,
    val name: UiText,
    val icon: ImageVector,
    val trophies: Int,
    val isYou: Boolean = false,
)

/**
 * Arena leaderboard — the top fighters of the season.
 *
 * Backend phase: implemented by a Ktor-backed `LeaderboardApiRepository`
 * hitting a rankings endpoint. The mock returns a static table with the
 * local player at rank 4.
 */
interface LeaderboardRepository {
    suspend fun getLeaderboard(): List<LeaderboardEntry>
}

class MockLeaderboardRepository : LeaderboardRepository {
    override suspend fun getLeaderboard(): List<LeaderboardEntry> = getMockLeaderboard()
}

internal fun getMockLeaderboard(): List<LeaderboardEntry> = listOf(
    LeaderboardEntry(1, ResId(Res.string.lb_name_01), RetroIcons.Star, 2480),
    LeaderboardEntry(2, ResId(Res.string.lb_name_02), RetroIcons.Crown, 2210),
    LeaderboardEntry(3, ResId(Res.string.lb_name_03), RetroIcons.Shield, 1985),
    LeaderboardEntry(4, ResId(Res.string.lb_name_04), RetroIcons.Sword, 1760, isYou = true),
    LeaderboardEntry(5, ResId(Res.string.lb_name_05), RetroIcons.Face, 1540),
    LeaderboardEntry(6, ResId(Res.string.lb_name_06), RetroIcons.Favorite, 1325),
    LeaderboardEntry(7, ResId(Res.string.lb_name_07), RetroIcons.Warning, 1190),
    LeaderboardEntry(8, ResId(Res.string.lb_name_08), RetroIcons.Gamepad, 1045),
    LeaderboardEntry(9, ResId(Res.string.lb_name_09), RetroIcons.Smile, 920),
    LeaderboardEntry(10, ResId(Res.string.lb_name_10), RetroIcons.Flag, 805),
    LeaderboardEntry(11, ResId(Res.string.lb_name_11), RetroIcons.Eye, 690),
    LeaderboardEntry(12, ResId(Res.string.lb_name_12), RetroIcons.Joystick, 575),
)