package com.funapp.retroui.core.design.components.feedback

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import com.funapp.retroui.core.design.components.foundation.RetroText
import com.funapp.retroui.core.design.theme.RetroTheme

/**
 * Empty-state block: emoji marker + pixel title + optional subtitle and an
 * optional action slot (usually a [RetroButton]).
 */
@Composable
fun RetroEmptyState(
    title: String,
    modifier: Modifier = Modifier,
    subtitle: String? = null,
    emoji: String = "🃏",
    action: (@Composable () -> Unit)? = null,
) {
    val colors = RetroTheme.colors
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(RetroTheme.spacing.xxl),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        RetroText(
            text = emoji,
            style = RetroTheme.typography.display,
            textAlign = TextAlign.Center,
        )
        Spacer(modifier = Modifier.height(RetroTheme.spacing.md))
        RetroText(
            text = title,
            style = RetroTheme.typography.heading,
            color = colors.textPrimary,
            textAlign = TextAlign.Center,
        )
        if (subtitle != null) {
            Spacer(modifier = Modifier.height(RetroTheme.spacing.sm))
            RetroText(
                text = subtitle,
                style = RetroTheme.typography.bodySmall,
                color = colors.textMuted,
                textAlign = TextAlign.Center,
            )
        }
        if (action != null) {
            Spacer(modifier = Modifier.height(RetroTheme.spacing.lg))
            action()
        }
    }
}
