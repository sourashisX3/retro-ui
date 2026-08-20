package com.funapp.retroui.features.battle.data

import com.funapp.retroui.core.data.mock.MockChampion
import kotlinx.coroutines.delay

/**
 * Finds a live opponent for a battle.
 *
 * Backend phase: this interface is implemented by a Ktor-backed
 * `MatchmakingApiRepository` hitting a queue/matchmaking endpoint. The mock
 * implementation returns a random champion from the roster after a short
 * search delay so the UI flow is real end-to-end.
 */
interface MatchmakingRepository {
    suspend fun findOpponent(): MockChampion
}

class MockMatchmakingRepository(
    private val roster: List<MockChampion>,
    private val searchDelayMs: Long = 2500,
) : MatchmakingRepository {
    override suspend fun findOpponent(): MockChampion {
        delay(searchDelayMs)
        return roster.random()
    }
}