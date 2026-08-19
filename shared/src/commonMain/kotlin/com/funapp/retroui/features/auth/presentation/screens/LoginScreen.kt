package com.funapp.retroui.features.auth.presentation.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.funapp.retroui.core.design.components.controls.RetroButton
import com.funapp.retroui.core.design.components.controls.RetroButtonVariant
import com.funapp.retroui.core.design.theme.RetroTheme
import com.funapp.retroui.core.ui.placeholder.RetroPlaceholderScreen
import org.jetbrains.compose.resources.stringResource
import retroui.shared.generated.resources.Res
import retroui.shared.generated.resources.btn_enter_arena
import retroui.shared.generated.resources.common_forgot_password
import retroui.shared.generated.resources.common_new_player
import retroui.shared.generated.resources.screen_login_subtitle
import retroui.shared.generated.resources.screen_login_title

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
        title = stringResource(Res.string.screen_login_title),
        subtitle = stringResource(Res.string.screen_login_subtitle),
        icon = Icons.Filled.Lock,
        modifier = modifier,
        action = {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(RetroTheme.spacing.sm),
            ) {
                RetroButton(
                    text = stringResource(Res.string.btn_enter_arena),
                    onClick = onGoHome,
                )
                RetroButton(
                    text = stringResource(Res.string.common_new_player),
                    variant = RetroButtonVariant.Secondary,
                    onClick = onGoRegister,
                )
                RetroButton(
                    text = stringResource(Res.string.common_forgot_password),
                    variant = RetroButtonVariant.Outline,
                    onClick = onGoForgotPassword,
                )
            }
        },
    )
}