package com.funapp.retroui.features.auth.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import com.funapp.retroui.core.design.components.feedback.RetroEmptyState
import com.funapp.retroui.core.design.components.surfaces.RetroScreen
import com.funapp.retroui.core.design.theme.RetroTheme

/**
 * Shared auth form shell: retro icon header, credential [content] fields,
 * then the primary [actions], centered on a scrollable safe-area screen.
 */
@Composable
fun AuthScreenLayout(
    icon: ImageVector,
    title: String,
    subtitle: String,
    modifier: Modifier = Modifier,
    content: @Composable ColumnScope.() -> Unit,
    actions: @Composable ColumnScope.() -> Unit,
) {
    RetroScreen(
        modifier = modifier,
        contentPadding = PaddingValues(bottom = RetroTheme.spacing.xxl),
    ) {
        item {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = RetroTheme.spacing.lg),
            ) {
                RetroEmptyState(
                    icon = icon,
                    title = title,
                    subtitle = subtitle,
                    modifier = Modifier.padding(top = RetroTheme.spacing.lg),
                )
                Spacer(modifier = Modifier.height(RetroTheme.spacing.md))
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    verticalArrangement = Arrangement.spacedBy(RetroTheme.spacing.sm),
                ) {
                    content()
                }
                Spacer(modifier = Modifier.height(RetroTheme.spacing.lg))
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    verticalArrangement = Arrangement.spacedBy(RetroTheme.spacing.sm),
                ) {
                    actions()
                }
            }
        }
    }
}