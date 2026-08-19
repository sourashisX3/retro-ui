package com.funapp.retroui.features.auth.presentation.screens
import com.funapp.retroui.core.ui.icons.RetroIcons

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import com.funapp.retroui.core.ui.icons.AccountCircle
import com.funapp.retroui.core.ui.icons.Lock
import com.funapp.retroui.core.ui.icons.MailOutline
import com.funapp.retroui.core.ui.icons.Person
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import com.funapp.retroui.core.ui.components.controls.RetroButton
import com.funapp.retroui.core.ui.components.controls.RetroButtonVariant
import com.funapp.retroui.core.ui.components.controls.RetroTextField
import com.funapp.retroui.features.auth.presentation.components.AuthScreenLayout
import org.jetbrains.compose.resources.stringResource
import retroui.shared.generated.resources.Res
import retroui.shared.generated.resources.btn_start_quest
import retroui.shared.generated.resources.common_back_to_login
import retroui.shared.generated.resources.field_confirm_password
import retroui.shared.generated.resources.field_email
import retroui.shared.generated.resources.field_password
import retroui.shared.generated.resources.field_username
import retroui.shared.generated.resources.placeholder_email
import retroui.shared.generated.resources.placeholder_password
import retroui.shared.generated.resources.placeholder_username
import retroui.shared.generated.resources.screen_register_subtitle
import retroui.shared.generated.resources.screen_register_title

/**
 * Register: username, email and two matching passwords, then START QUEST
 * heads into the arena; BACK TO LOGIN returns to sign-in.
 */
@Composable
fun RegisterScreen(
    onGoHome: () -> Unit,
    onGoLogin: () -> Unit,
    modifier: Modifier = Modifier,
) {
    var username by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }

    AuthScreenLayout(
        icon = RetroIcons.AccountCircle,
        title = stringResource(Res.string.screen_register_title),
        subtitle = stringResource(Res.string.screen_register_subtitle),
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
                value = email,
                onValueChange = { email = it },
                label = stringResource(Res.string.field_email),
                placeholder = stringResource(Res.string.placeholder_email),
                leadingIcon = RetroIcons.MailOutline,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
            )
            RetroTextField(
                value = password,
                onValueChange = { password = it },
                label = stringResource(Res.string.field_password),
                placeholder = stringResource(Res.string.placeholder_password),
                leadingIcon = RetroIcons.Lock,
                visualTransformation = PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            )
            RetroTextField(
                value = confirmPassword,
                onValueChange = { confirmPassword = it },
                label = stringResource(Res.string.field_confirm_password),
                placeholder = stringResource(Res.string.placeholder_password),
                leadingIcon = RetroIcons.Lock,
                visualTransformation = PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            )
        },
        actions = {
            RetroButton(
                text = stringResource(Res.string.btn_start_quest),
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
