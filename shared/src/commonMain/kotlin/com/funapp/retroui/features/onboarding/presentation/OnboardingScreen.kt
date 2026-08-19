package com.funapp.retroui.features.onboarding.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.funapp.retroui.core.design.components.controls.RetroButton
import com.funapp.retroui.core.design.components.controls.RetroButtonVariant
import com.funapp.retroui.core.design.theme.RetroTheme
import com.funapp.retroui.core.ui.placeholder.RetroPlaceholderScreen
import org.jetbrains.compose.resources.stringResource
import retroui.shared.generated.resources.Res
import retroui.shared.generated.resources.btn_already_player
import retroui.shared.generated.resources.btn_start_playing
import retroui.shared.generated.resources.screen_onboarding_subtitle
import retroui.shared.generated.resources.screen_onboarding_title

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
        title = stringResource(Res.string.screen_onboarding_title),
        subtitle = stringResource(Res.string.screen_onboarding_subtitle),
        icon = Icons.Filled.Star,
        modifier = modifier,
        action = {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(RetroTheme.spacing.sm),
            ) {
                RetroButton(
                    text = stringResource(Res.string.btn_start_playing),
                    onClick = onGoHome,
                )
                RetroButton(
                    text = stringResource(Res.string.btn_already_player),
                    variant = RetroButtonVariant.Outline,
                    onClick = onGoLogin,
                )
            }
        },
    )
}