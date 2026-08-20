package com.funapp.retroui.core.ui.components.surfaces

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.funapp.retroui.core.ui.components.foundation.RetroDivider
import com.funapp.retroui.core.ui.components.foundation.RetroText
import com.funapp.retroui.core.ui.theme.RetroTheme

/**
 * Titled card container. Thin wrapper over [RetroCard] with a
 * [RetroCardHeader] strip — the standard "panel" of the app.
 */
@Composable
fun RetroPanel(
    title: String,
    modifier: Modifier = Modifier,
    subtitle: String? = null,
    trailing: (@Composable () -> Unit)? = null,
    content: @Composable ColumnScope.() -> Unit,
) {
    RetroCard(
        modifier = modifier,
        header = {
            RetroCardHeader(
                title = title,
                subtitle = subtitle,
                trailing = trailing,
            )
        },
        content = content,
    )
}

/**
 * Lightweight section block: pixel title + ink divider + content. No card
 * chrome — used inside panels and scrolling screens.
 */
@Composable
fun RetroSection(
    title: String,
    modifier: Modifier = Modifier,
    content: @Composable ColumnScope.() -> Unit,
) {
    Column(modifier = modifier.fillMaxWidth()) {
        RetroText(
            text = title,
            style = RetroTheme.typography.title,
            color = RetroTheme.colors.textPrimary,
        )
        Spacer(modifier = Modifier.height(RetroTheme.spacing.xs))
        RetroDivider()
        Spacer(modifier = Modifier.height(RetroTheme.spacing.md))
        Column(content = content)
    }
}
