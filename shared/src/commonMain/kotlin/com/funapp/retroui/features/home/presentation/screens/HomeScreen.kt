package com.funapp.retroui.features.home.presentation.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.funapp.retroui.core.design.components.controls.RetroButton
import com.funapp.retroui.core.design.components.controls.RetroButtonVariant
import com.funapp.retroui.core.design.theme.RetroTheme
import com.funapp.retroui.core.ui.placeholder.RetroPlaceholderScreen

/**
 * Home placeholder. Rebuilt in the home phase with player summary,
 * daily quests and the battle CTA.
 */
@Composable
fun HomeScreen(
    onGoBattle: () -> Unit,
    onGoCollection: () -> Unit,
    onGoQuests: () -> Unit,
    onGoProfile: () -> Unit,
    onGoSettings: () -> Unit,
    modifier: Modifier = Modifier,
) {
    RetroPlaceholderScreen(
        title = "MAIN MENU",
        subtitle = "Choose your next quest. (Coming soon)",
        modifier = modifier,
        emoji = "🏠",
        action = {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(RetroTheme.spacing.sm),
            ) {
                RetroButton(text = "⚔  START BATTLE", onClick = onGoBattle)
                RetroButton(
                    text = "COLLECTION",
                    variant = RetroButtonVariant.Secondary,
                    onClick = onGoCollection,
                )
                RetroButton(text = "QUESTS", variant = RetroButtonVariant.Accent, onClick = onGoQuests)
                RetroButton(text = "PROFILE", variant = RetroButtonVariant.Outline, onClick = onGoProfile)
                RetroButton(text = "SETTINGS", variant = RetroButtonVariant.Outline, onClick = onGoSettings)
            }
        },
    )
}