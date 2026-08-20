package com.funapp.retroui.features.home.presentation.components
import com.funapp.retroui.core.ui.icons.RetroIcons

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import com.funapp.retroui.core.ui.icons.Notifications
import com.funapp.retroui.core.ui.icons.Settings
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.funapp.retroui.core.ui.components.controls.RetroIconButton
import com.funapp.retroui.core.ui.components.foundation.RetroText
import com.funapp.retroui.core.ui.theme.RetroTheme
import org.jetbrains.compose.resources.stringResource
import retroui.shared.generated.resources.Res
import retroui.shared.generated.resources.app_name
import retroui.shared.generated.resources.btn_notifications
import retroui.shared.generated.resources.btn_settings
import retroui.shared.generated.resources.home_greeting

/**
 * Home top bar: brand title + tagline with notifications and settings
 * actions.
 */
@Composable
internal fun HomeHeader(
    onGoNotifications: () -> Unit,
    onGoSettings: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Row(modifier = modifier, verticalAlignment = Alignment.CenterVertically) {
        Column(modifier = Modifier.weight(1f)) {
            RetroText(
                text = stringResource(Res.string.app_name),
                style = RetroTheme.typography.heading,
                color = RetroTheme.colors.textPrimary,
            )
            RetroText(
                text = stringResource(Res.string.home_greeting),
                style = RetroTheme.typography.caption,
                color = RetroTheme.colors.textMuted,
            )
        }
        RetroIconButton(
            imageVector = RetroIcons.Notifications,
            contentDescription = stringResource(Res.string.btn_notifications),
            onClick = onGoNotifications,
        )
        Spacer(modifier = Modifier.width(RetroTheme.spacing.xs))
        RetroIconButton(
            imageVector = RetroIcons.Settings,
            contentDescription = stringResource(Res.string.btn_settings),
            onClick = onGoSettings,
        )
    }
}
