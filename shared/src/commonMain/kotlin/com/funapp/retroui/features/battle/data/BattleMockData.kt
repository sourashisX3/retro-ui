package com.funapp.retroui.features.battle.data

import com.funapp.retroui.core.ui.components.game.RetroBattleLogType
import com.funapp.retroui.core.utils.UiText
import com.funapp.retroui.core.utils.UiText.ResId
import retroui.shared.generated.resources.Res
import retroui.shared.generated.resources.battle_log_damage
import retroui.shared.generated.resources.battle_log_heal
import retroui.shared.generated.resources.battle_log_shield
import retroui.shared.generated.resources.battle_log_start

data class BattleLogMockEntry(
    val text: UiText,
    val type: RetroBattleLogType,
)

internal fun getMockBattleLogEntries(): List<BattleLogMockEntry> = listOf(
    BattleLogMockEntry(
        text = ResId(Res.string.battle_log_start),
        type = RetroBattleLogType.System,
    ),
    BattleLogMockEntry(
        text = ResId(Res.string.battle_log_damage),
        type = RetroBattleLogType.Damage,
    ),
    BattleLogMockEntry(
        text = ResId(Res.string.battle_log_heal),
        type = RetroBattleLogType.Heal,
    ),
    BattleLogMockEntry(
        text = ResId(Res.string.battle_log_shield),
        type = RetroBattleLogType.Shield,
    ),
)