package com.funapp.retroui.features.battle.presentation.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Warning
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.funapp.retroui.core.design.animation.retroEntrance
import com.funapp.retroui.core.design.components.controls.RetroButton
import com.funapp.retroui.core.design.components.controls.RetroButtonVariant
import com.funapp.retroui.core.design.components.foundation.RetroText
import com.funapp.retroui.core.design.components.game.RetroBattleLog
import com.funapp.retroui.core.design.components.game.RetroBattleLogEntry
import com.funapp.retroui.core.design.components.game.RetroBattleLogType
import com.funapp.retroui.core.design.components.surfaces.RetroCharacterCard
import com.funapp.retroui.core.design.components.surfaces.RetroDialog
import com.funapp.retroui.core.design.components.surfaces.RetroDialogVariant
import com.funapp.retroui.core.design.components.surfaces.RetroScreenStatic
import com.funapp.retroui.core.design.theme.RetroTheme
import com.funapp.retroui.features.battle.presentation.components.HandRow
import com.funapp.retroui.features.battle.presentation.components.VsBadge
import org.jetbrains.compose.resources.stringResource
import retroui.shared.generated.resources.Res
import retroui.shared.generated.resources.battle_attack
import retroui.shared.generated.resources.battle_defend
import retroui.shared.generated.resources.battle_log_damage
import retroui.shared.generated.resources.battle_log_heal
import retroui.shared.generated.resources.battle_log_shield
import retroui.shared.generated.resources.battle_log_start
import retroui.shared.generated.resources.battle_opponent_level
import retroui.shared.generated.resources.battle_opponent_name
import retroui.shared.generated.resources.battle_player_level
import retroui.shared.generated.resources.battle_round
import retroui.shared.generated.resources.btn_confirm_retreat
import retroui.shared.generated.resources.btn_retreat
import retroui.shared.generated.resources.dialog_cancel
import retroui.shared.generated.resources.player_display_name
import retroui.shared.generated.resources.retreat_confirm_body
import retroui.shared.generated.resources.retreat_confirm_title
import retroui.shared.generated.resources.screen_battle_title

/**
 * Battle arena. Fixed (non-scrolling) layout with both fighters, the live
 * battle log, the player hand and the action bar.
 */
@Composable
fun BattleScreen(
    onGoHome: () -> Unit,
    modifier: Modifier = Modifier,
) {
    var showRetreatDialog by remember { mutableStateOf(false) }

    RetroScreenStatic(modifier = modifier) {
        Column {
            Row(
                modifier = Modifier.retroEntrance(delayMillis = 0),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Column(modifier = Modifier.weight(1f)) {
                    RetroText(
                        text = stringResource(Res.string.screen_battle_title),
                        style = RetroTheme.typography.heading,
                        color = RetroTheme.colors.textPrimary,
                    )
                    RetroText(
                        text = stringResource(Res.string.battle_round, 1),
                        style = RetroTheme.typography.caption,
                        color = RetroTheme.colors.textMuted,
                    )
                }
                RetroButton(
                    text = stringResource(Res.string.btn_retreat),
                    variant = RetroButtonVariant.Outline,
                    onClick = { showRetreatDialog = true },
                    small = true,
                )
            }
            Spacer(modifier = Modifier.height(RetroTheme.spacing.lg))
            RetroCharacterCard(
                name = stringResource(Res.string.battle_opponent_name),
                level = stringResource(Res.string.battle_opponent_level),
                avatarIcon = Icons.Filled.PlayArrow,
                avatarColor = RetroTheme.colors.surfaceVariant,
                hp = 0.82f,
                shield = 0.4f,
                hpText = "82",
                modifier = Modifier.retroEntrance(delayMillis = 60, fromY = -12.dp),
            )
            Spacer(modifier = Modifier.height(RetroTheme.spacing.sm))
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .retroEntrance(delayMillis = 140),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                VsBadge()
            }
            Spacer(modifier = Modifier.height(RetroTheme.spacing.sm))
            RetroCharacterCard(
                name = stringResource(Res.string.player_display_name),
                level = stringResource(Res.string.battle_player_level),
                avatarIcon = Icons.Filled.PlayArrow,
                avatarColor = RetroTheme.colors.primaryContainer,
                hp = 0.65f,
                hpText = "65",
                modifier = Modifier.retroEntrance(delayMillis = 200, fromY = 12.dp),
            )
            Spacer(modifier = Modifier.height(RetroTheme.spacing.lg))
            RetroBattleLog(
                entries = battleLogEntries(),
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .height(120.dp)
                    .retroEntrance(delayMillis = 260),
            )
            Spacer(modifier = Modifier.height(RetroTheme.spacing.lg))
            HandRow(modifier = Modifier.retroEntrance(delayMillis = 320))
            Spacer(modifier = Modifier.height(RetroTheme.spacing.lg))
            Row(
                modifier = Modifier.retroEntrance(delayMillis = 380),
                horizontalArrangement = Arrangement.spacedBy(RetroTheme.spacing.sm),
            ) {
                RetroButton(
                    text = stringResource(Res.string.battle_defend),
                    variant = RetroButtonVariant.Secondary,
                    onClick = {},
                    modifier = Modifier.weight(1f),
                )
                RetroButton(
                    text = stringResource(Res.string.battle_attack),
                    leadingIcon = Icons.Filled.PlayArrow,
                    onClick = {},
                    modifier = Modifier.weight(1f),
                )
            }
        }
    }

    RetroDialog(
        visible = showRetreatDialog,
        onDismiss = { showRetreatDialog = false },
        title = stringResource(Res.string.retreat_confirm_title),
        message = stringResource(Res.string.retreat_confirm_body),
        icon = Icons.Filled.Warning,
        variant = RetroDialogVariant.Danger,
        confirmText = stringResource(Res.string.btn_confirm_retreat),
        onConfirm = {
            showRetreatDialog = false
            onGoHome()
        },
        dismissText = stringResource(Res.string.dialog_cancel),
    )
}

@Composable
private fun battleLogEntries(): List<RetroBattleLogEntry> = listOf(
    RetroBattleLogEntry(
        text = stringResource(Res.string.battle_log_start),
        type = RetroBattleLogType.System,
    ),
    RetroBattleLogEntry(
        text = stringResource(Res.string.battle_log_damage),
        type = RetroBattleLogType.Damage,
    ),
    RetroBattleLogEntry(
        text = stringResource(Res.string.battle_log_heal),
        type = RetroBattleLogType.Heal,
    ),
    RetroBattleLogEntry(
        text = stringResource(Res.string.battle_log_shield),
        type = RetroBattleLogType.Shield,
    ),
)