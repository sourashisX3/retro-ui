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
 * Login placeholder. Rebuilt in the auth phase with credentials fields.
 */
@Composable
fun LoginScreen(
    onGoHome: () -> Unit,
    onGoRegister: () -> Unit,
    onGoForgotPassword: () -> Unit,
    modifier: Modifier = Modifier,
) {
    RetroPlaceholderScreen(
        title = "LOGIN",
        subtitle = "Sign in to continue your quest. (Coming soon)",
        modifier = modifier,
        emoji = "🔑",
        action = {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(RetroTheme.spacing.sm),
            ) {
                RetroButton(text = "ENTER THE ARENA", onClick = onGoHome)
                RetroButton(
                    text = "NEW PLAYER",
                    variant = RetroButtonVariant.Secondary,
                    onClick = onGoRegister,
                )
                RetroButton(
                    text = "FORGOT PASSWORD",
                    variant = RetroButtonVariant.Outline,
                    onClick = onGoForgotPassword,
                )
            }
        },
    )
}