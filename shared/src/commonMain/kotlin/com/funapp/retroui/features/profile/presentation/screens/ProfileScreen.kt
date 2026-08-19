package com.funapp.retroui.features.profile.presentation.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.funapp.retroui.core.design.components.controls.RetroButton
import com.funapp.retroui.core.design.components.controls.RetroButtonVariant
import com.funapp.retroui.core.design.theme.RetroTheme
import com.funapp.retroui.core.ui.placeholder.RetroPlaceholderScreen
import org.jetbrains.compose.resources.stringResource
import retroui.shared.generated.resources.Res
import retroui.shared.generated.resources.btn_settings
import retroui.shared.generated.resources.common_back_to_menu
import retroui.shared.generated.resources.screen_profile_subtitle
import retroui.shared.generated.resources.screen_profile_title

/**
 * Profile placeholder. Rebuilt in the profile phase with stats, badges
 * and player info.
 */
@Composable
fun ProfileScreen(
    onGoSettings: () -> Unit,
    onGoHome: () -> Unit,
    modifier: Modifier = Modifier,
) {
    RetroPlaceholderScreen(
        title = stringResource(Res.string.screen_profile_title),
        subtitle = stringResource(Res.string.screen_profile_subtitle),
        icon = Icons.Filled.Person,
        modifier = modifier,
        action = {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(RetroTheme.spacing.sm),
            ) {
                RetroButton(
                    text = stringResource(Res.string.btn_settings),
                    variant = RetroButtonVariant.Secondary,
                    onClick = onGoSettings,
                )
                RetroButton(
                    text = stringResource(Res.string.common_back_to_menu),
                    variant = RetroButtonVariant.Outline,
                    onClick = onGoHome,
                )
            }
        },
    )
}