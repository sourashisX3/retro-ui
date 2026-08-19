package com.funapp.retroui.features.splash.presentation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.funapp.retroui.core.design.components.controls.RetroButton
import com.funapp.retroui.core.design.components.controls.RetroButtonVariant
import com.funapp.retroui.core.ui.placeholder.RetroPlaceholderScreen

/**
 * Splash placeholder. Rebuilt in the splash phase with the animated
 * [RetroDuelLogo] and auto-navigation to onboarding/home.
 */
@Composable
fun SplashScreen(
    onGoDesignSystem: () -> Unit,
    modifier: Modifier = Modifier,
) {
    RetroPlaceholderScreen(
        title = "RETRO DUEL",
        subtitle = "Insert coin to start…",
        modifier = modifier,
        emoji = "🕹️",
        action = {
            RetroButton(
                text = "DESIGN SYSTEM",
                variant = RetroButtonVariant.Outline,
                onClick = onGoDesignSystem,
            )
        },
    )
}