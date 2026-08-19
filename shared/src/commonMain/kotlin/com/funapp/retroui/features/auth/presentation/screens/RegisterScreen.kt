package com.funapp.retroui.features.auth.presentation.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.funapp.retroui.core.design.components.controls.RetroButton
import com.funapp.retroui.core.design.components.controls.RetroButtonVariant
import com.funapp.retroui.core.design.theme.RetroTheme
import com.funapp.retroui.core.ui.placeholder.RetroPlaceholderScreen
import org.jetbrains.compose.resources.stringResource
import retroui.shared.generated.resources.Res
import retroui.shared.generated.resources.btn_start_quest
import retroui.shared.generated.resources.common_back_to_login
import retroui.shared.generated.resources.screen_register_subtitle
import retroui.shared.generated.resources.screen_register_title

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
        title = stringResource(Res.string.screen_register_title),
        subtitle = stringResource(Res.string.screen_register_subtitle),
        icon = Icons.Filled.AccountCircle,
        modifier = modifier,
        action = {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(RetroTheme.spacing.sm),
            ) {
                RetroButton(
                    text = stringResource(Res.string.btn_start_quest),
                    onClick = onGoHome,
                )
                RetroButton(
                    text = stringResource(Res.string.common_back_to_login),
                    variant = RetroButtonVariant.Outline,
                    onClick = onGoLogin,
                )
            }
        },
    )
}