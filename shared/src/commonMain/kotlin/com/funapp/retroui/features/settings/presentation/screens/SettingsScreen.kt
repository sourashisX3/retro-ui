package com.funapp.retroui.features.settings.presentation.screens

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.funapp.retroui.core.design.components.controls.RetroButton
import com.funapp.retroui.core.design.components.controls.RetroButtonVariant
import com.funapp.retroui.core.ui.placeholder.RetroPlaceholderScreen

/**
 * Settings placeholder. Rebuilt in the settings phase with sound, haptics
 * and theming toggles.
 */
@Composable
fun SettingsScreen(
    onGoHome: () -> Unit,
    modifier: Modifier = Modifier,
) {
    RetroPlaceholderScreen(
        title = "SETTINGS",
        subtitle = "Tweak your retro experience. (Coming soon)",
        modifier = modifier,
        emoji = "🎛️",
        action = {
            RetroButton(
                text = "BACK TO MENU",
                variant = RetroButtonVariant.Outline,
                onClick = onGoHome,
            )
        },
    )
}