package com.funapp.retroui.features.battle.presentation.screens

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.funapp.retroui.core.design.components.controls.RetroButton
import com.funapp.retroui.core.design.components.controls.RetroButtonVariant
import com.funapp.retroui.core.ui.placeholder.RetroPlaceholderScreen
import org.jetbrains.compose.resources.stringResource
import retroui.shared.generated.resources.Res
import retroui.shared.generated.resources.btn_retreat
import retroui.shared.generated.resources.screen_battle_subtitle
import retroui.shared.generated.resources.screen_battle_title

/**
 * Battle placeholder. Rebuilt in the battle phase with the arena, hands
 * and the live battle log.
 */
@Composable
fun BattleScreen(
    onGoHome: () -> Unit,
    modifier: Modifier = Modifier,
) {
    RetroPlaceholderScreen(
        title = stringResource(Res.string.screen_battle_title),
        subtitle = stringResource(Res.string.screen_battle_subtitle),
        icon = Icons.Filled.PlayArrow,
        modifier = modifier,
        action = {
            RetroButton(
                text = stringResource(Res.string.btn_retreat),
                variant = RetroButtonVariant.Outline,
                onClick = onGoHome,
            )
        },
    )
}