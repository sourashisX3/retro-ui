package com.funapp.retroui.features.settings.presentation.screens

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.funapp.retroui.core.design.components.controls.RetroButton
import com.funapp.retroui.core.design.components.controls.RetroButtonVariant
import com.funapp.retroui.core.ui.placeholder.RetroPlaceholderScreen
import org.jetbrains.compose.resources.stringResource
import retroui.shared.generated.resources.Res
import retroui.shared.generated.resources.common_back_to_menu
import retroui.shared.generated.resources.screen_settings_subtitle
import retroui.shared.generated.resources.screen_settings_title

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
        title = stringResource(Res.string.screen_settings_title),
        subtitle = stringResource(Res.string.screen_settings_subtitle),
        icon = Icons.Filled.Settings,
        modifier = modifier,
        action = {
            RetroButton(
                text = stringResource(Res.string.common_back_to_menu),
                variant = RetroButtonVariant.Outline,
                onClick = onGoHome,
            )
        },
    )
}