package com.funapp.retroui.features.auth.presentation.screens

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
 * Register placeholder. Rebuilt in the auth phase.
 */
@Composable
fun RegisterScreen(
    onGoHome: () -> Unit,
    onGoLogin: () -> Unit,
    modifier: Modifier = Modifier,
) {
    RetroPlaceholderScreen(
        title = "CREATE PLAYER",
        subtitle = "Forge your hero and join the arena. (Coming soon)",
        modifier = modifier,
        emoji = "🛡️",
        action = {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(RetroTheme.spacing.sm),
            ) {
                RetroButton(text = "START QUEST", onClick = onGoHome)
                RetroButton(
                    text = "BACK TO LOGIN",
                    variant = RetroButtonVariant.Outline,
                    onClick = onGoLogin,
                )
            }
        },
    )
}