package com.funapp.retroui.features.battle.presentation.screens

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.funapp.retroui.core.design.components.controls.RetroButton
import com.funapp.retroui.core.design.components.controls.RetroButtonVariant
import com.funapp.retroui.core.ui.placeholder.RetroPlaceholderScreen

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
        title = "BATTLE ARENA",
        subtitle = "VS — prepare for the showdown. (Coming soon)",
        modifier = modifier,
        emoji = "⚔️",
        action = {
            RetroButton(
                text = "RETREAT TO MENU",
                variant = RetroButtonVariant.Outline,
                onClick = onGoHome,
            )
        },
    )
}