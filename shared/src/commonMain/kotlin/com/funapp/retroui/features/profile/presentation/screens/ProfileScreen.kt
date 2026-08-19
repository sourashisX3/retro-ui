package com.funapp.retroui.features.profile.presentation.screens

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
        title = "PROFILE",
        subtitle = "Player stats, badges and records. (Coming soon)",
        modifier = modifier,
        emoji = "🧑‍🚀",
        action = {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(RetroTheme.spacing.sm),
            ) {
                RetroButton(text = "SETTINGS", variant = RetroButtonVariant.Secondary, onClick = onGoSettings)
                RetroButton(
                    text = "BACK TO MENU",
                    variant = RetroButtonVariant.Outline,
                    onClick = onGoHome,
                )
            }
        },
    )
}