package com.funapp.retroui.features.quests.presentation.screens

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.funapp.retroui.core.design.components.controls.RetroButton
import com.funapp.retroui.core.design.components.controls.RetroButtonVariant
import com.funapp.retroui.core.ui.placeholder.RetroPlaceholderScreen

/**
 * Quests placeholder. Rebuilt in the quests phase with daily/weekly lists
 * and progress bars.
 */
@Composable
fun QuestsScreen(
    onGoHome: () -> Unit,
    modifier: Modifier = Modifier,
) {
    RetroPlaceholderScreen(
        title = "QUESTS",
        subtitle = "Complete quests to earn rewards. (Coming soon)",
        modifier = modifier,
        emoji = "🏆",
        action = {
            RetroButton(
                text = "BACK TO MENU",
                variant = RetroButtonVariant.Outline,
                onClick = onGoHome,
            )
        },
    )
}