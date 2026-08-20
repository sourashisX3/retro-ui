package com.funapp.retroui.features.auth.presentation.screens
import com.funapp.retroui.core.ui.icons.RetroIcons

import androidx.compose.foundation.layout.fillMaxWidth
import com.funapp.retroui.core.ui.icons.MailOutline
import com.funapp.retroui.core.ui.icons.Person
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.funapp.retroui.core.ui.components.controls.RetroButton
import com.funapp.retroui.core.ui.components.controls.RetroButtonVariant
import com.funapp.retroui.core.ui.components.controls.RetroTextField
import com.funapp.retroui.features.auth.presentation.components.AuthScreenLayout
import org.jetbrains.compose.resources.stringResource
import retroui.shared.generated.resources.Res
import retroui.shared.generated.resources.btn_send_link
import retroui.shared.generated.resources.common_back_to_login
import retroui.shared.generated.resources.field_username
import retroui.shared.generated.resources.placeholder_username
import retroui.shared.generated.resources.screen_forgot_subtitle
import retroui.shared.generated.resources.screen_forgot_title

/**
 * Forgot password: identify the account by username, then SEND LINK returns
 * to the arena with the auth stack cleared; BACK TO LOGIN goes to sign-in.
 */
@Composable
fun ForgotPasswordScreen(
    onGoLogin: () -> Unit,
    onGoHome: () -> Unit,
    modifier: Modifier = Modifier,
) {
    var username by remember { mutableStateOf("") }

    AuthScreenLayout(
        icon = RetroIcons.MailOutline,
        title = stringResource(Res.string.screen_forgot_title),
        subtitle = stringResource(Res.string.screen_forgot_subtitle),
        modifier = modifier,
        content = {
            RetroTextField(
                value = username,
                onValueChange = { username = it },
                label = stringResource(Res.string.field_username),
                placeholder = stringResource(Res.string.placeholder_username),
                leadingIcon = RetroIcons.Person,
            )
        },
        actions = {
            RetroButton(
                text = stringResource(Res.string.btn_send_link),
                variant = RetroButtonVariant.Secondary,
                onClick = onGoHome,
                modifier = Modifier.fillMaxWidth(),
            )
            RetroButton(
                text = stringResource(Res.string.common_back_to_login),
                variant = RetroButtonVariant.Outline,
                onClick = onGoLogin,
                modifier = Modifier.fillMaxWidth(),
            )
        },
    )
}
