package com.funapp.retroui.core.di

import com.funapp.retroui.core.ui.theme.SettingsRepository
import com.funapp.retroui.features.battle.data.MatchmakingRepository
import com.funapp.retroui.features.leaderboard.data.LeaderboardRepository

/**
 * Manual dependency container — no Hilt (Hilt is Android-only and would
 * break the KMP shared module). Created by the root [com.funapp.retroui.App]
 * and handed out via [LocalAppContainer].
 *
 * Grows as features are implemented: repositories, local/remote data
 * sources, mappers and use cases are registered here. The backend phase
 * swaps the mock repositories for Ktor-backed implementations without
 * touching any screen — screens only see the interfaces.
 */
class AppContainer(
    val settingsRepository: SettingsRepository,
    val matchmakingRepository: MatchmakingRepository,
    val leaderboardRepository: LeaderboardRepository,
) {

    val json = kotlinx.serialization.json.Json {
        ignoreUnknownKeys = true
        encodeDefaults = true
    }
}
