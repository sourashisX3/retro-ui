package com.funapp.retroui.features.auth.presentation.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MailOutline
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.funapp.retroui.core.design.components.controls.RetroButton
import com.funapp.retroui.core.design.components.controls.RetroButtonVariant
import com.funapp.retroui.core.design.theme.RetroTheme
import com.funapp.retroui.core.ui.placeholder.RetroPlaceholderScreen
import org.jetbrains.compose.resources.stringResource
import retroui.shared.generated.resources.Res
import retroui.shared.generated.resources.btn_send_link
import retroui.shared.generated.resources.common_back_to_login
import retroui.shared.generated.resources.screen_forgot_subtitle
import retroui.shared.generated.resources.screen_forgot_title

/**
 * Forgot-password placeholder. Rebuilt in the auth phase.
 */
@Composable
fun ForgotPasswordScreen(
    onGoLogin: () -> Unit,
    modifier: Modifier = Modifier,
) {
    RetroPlaceholderScreen(
        title = stringResource(Res.string.screen_forgot_title),
        subtitle = stringResource(Res.string.screen_forgot_subtitle),
        icon = Icons.Filled.MailOutline,
        modifier = modifier,
        action = {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(RetroTheme.spacing.sm),
            ) {
                RetroButton(
                    text = stringResource(Res.string.btn_send_link),
                    variant = RetroButtonVariant.Secondary,
                    onClick = onGoLogin,
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