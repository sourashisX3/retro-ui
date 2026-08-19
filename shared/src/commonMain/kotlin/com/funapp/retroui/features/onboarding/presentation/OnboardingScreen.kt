package com.funapp.retroui.features.onboarding.presentation

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
 * Onboarding placeholder. Rebuilt in the onboarding phase with the pager.
 */
@Composable
fun OnboardingScreen(
    onGoHome: () -> Unit,
    onGoLogin: () -> Unit,
    modifier: Modifier = Modifier,
) {
    RetroPlaceholderScreen(
        title = "ONBOARDING",
        subtitle = "Welcome, player. Your quest awaits. (Coming soon)",
        modifier = modifier,
        emoji = "🎮",
        action = {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(RetroTheme.spacing.sm),
            ) {
                RetroButton(text = "START PLAYING", onClick = onGoHome)
                RetroButton(
                    text = "ALREADY PLAYER",
                    variant = RetroButtonVariant.Outline,
                    onClick = onGoLogin,
                )
            }
        },
    )
}