package com.funapp.retroui.features.auth.presentation.screens
import com.funapp.retroui.core.ui.icons.RetroIcons

import androidx.compose.foundation.text.KeyboardOptions
import com.funapp.retroui.core.ui.icons.Lock
import com.funapp.retroui.core.ui.icons.Person
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.foundation.layout.fillMaxWidth
import com.funapp.retroui.core.ui.components.controls.RetroButton
import com.funapp.retroui.core.ui.components.controls.RetroButtonVariant
import com.funapp.retroui.core.ui.components.controls.RetroTextField
import com.funapp.retroui.core.ui.components.controls.RetroTextFieldTrailingAction
import com.funapp.retroui.features.auth.presentation.components.AuthScreenLayout
import org.jetbrains.compose.resources.stringResource
import retroui.shared.generated.resources.Res
import retroui.shared.generated.resources.btn_enter_arena
import retroui.shared.generated.resources.common_forgot_password
import retroui.shared.generated.resources.common_new_player
import retroui.shared.generated.resources.field_hide
import retroui.shared.generated.resources.field_password
import retroui.shared.generated.resources.field_show
import retroui.shared.generated.resources.field_username
import retroui.shared.generated.resources.placeholder_password
import retroui.shared.generated.resources.placeholder_username
import retroui.shared.generated.resources.screen_login_subtitle
import retroui.shared.generated.resources.screen_login_title

/**
 * Login: username + password (with SHOW/HIDE masking toggle), then the
 * arena entry point, a hop to registration, and password recovery.
 */
@Composable
fun LoginScreen(
    onGoHome: () -> Unit,
    onGoRegister: () -> Unit,
    onGoForgotPassword: () -> Unit,
    modifier: Modifier = Modifier,
) {
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var showPassword by remember { mutableStateOf(false) }

    val showLabel = stringResource(Res.string.field_show)
    val hideLabel = stringResource(Res.string.field_hide)

    AuthScreenLayout(
        icon = RetroIcons.Lock,
        title = stringResource(Res.string.screen_login_title),
        subtitle = stringResource(Res.string.screen_login_subtitle),
        modifier = modifier,
        content = {
            RetroTextField(
                value = username,
                onValueChange = { username = it },
                label = stringResource(Res.string.field_username),
                placeholder = stringResource(Res.string.placeholder_username),
                leadingIcon = RetroIcons.Person,
            )
            RetroTextField(
                value = password,
                onValueChange = { password = it },
                label = stringResource(Res.string.field_password),
                placeholder = stringResource(Res.string.placeholder_password),
                leadingIcon = RetroIcons.Lock,
                visualTransformation = if (showPassword) {
                    VisualTransformation.None
                } else {
                    PasswordVisualTransformation()
                },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                trailing = {
                    RetroTextFieldTrailingAction(
                        text = if (showPassword) hideLabel else showLabel,
                        onClick = { showPassword = !showPassword },
                    )
                },
            )
        },
        actions = {
            RetroButton(
                text = stringResource(Res.string.btn_enter_arena),
                onClick = onGoHome,
                modifier = Modifier.fillMaxWidth(),
            )
            RetroButton(
                text = stringResource(Res.string.common_new_player),
                variant = RetroButtonVariant.Secondary,
                onClick = onGoRegister,
                modifier = Modifier.fillMaxWidth(),
            )
            RetroButton(
                text = stringResource(Res.string.common_forgot_password),
                variant = RetroButtonVariant.Outline,
                onClick = onGoForgotPassword,
                modifier = Modifier.fillMaxWidth(),
            )
        },
    )
}
