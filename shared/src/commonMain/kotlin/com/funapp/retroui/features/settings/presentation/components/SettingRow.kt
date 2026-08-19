package com.funapp.retroui.features.settings.presentation.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.funapp.retroui.core.design.components.foundation.RetroText
import com.funapp.retroui.core.design.theme.RetroTheme

/**
 * One settings row: title, optional subtitle and a trailing control
 * (toggle, label or action).
 */
@Composable
internal fun SettingRow(
    title: String,
    modifier: Modifier = Modifier,
    subtitle: String? = null,
    trailing: (@Composable () -> Unit)? = null,
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Column(modifier = Modifier.weight(1f)) {
            RetroText(
                text = title,
                style = RetroTheme.typography.bodySmall,
                color = RetroTheme.colors.textPrimary,
            )
            if (subtitle != null) {
                Spacer(modifier = Modifier.height(RetroTheme.spacing.xxs))
                RetroText(
                    text = subtitle,
                    style = RetroTheme.typography.caption,
                    color = RetroTheme.colors.textMuted,
                )
            }
        }
        if (trailing != null) {
            Spacer(modifier = Modifier.width(RetroTheme.spacing.sm))
            trailing()
        }
    }
}