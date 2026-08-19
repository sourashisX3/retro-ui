package com.funapp.retroui.features.auth.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import com.funapp.retroui.core.ui.animation.retroEntrance
import com.funapp.retroui.core.ui.components.feedback.RetroEmptyState
import com.funapp.retroui.core.ui.components.surfaces.RetroScreenStatic
import com.funapp.retroui.core.ui.theme.RetroTheme

/**
 * Shared auth form shell: retro icon header, scrollable credential
 * [content] fields, and the primary [actions] pinned to the bottom of the
 * screen — always visible, even when the fields scroll.
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
    RetroScreenStatic(
        modifier = modifier,
        contentPadding = PaddingValues(bottom = RetroTheme.spacing.lg),
    ) {
        Column(modifier = Modifier.fillMaxSize()) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .verticalScroll(rememberScrollState())
                    .retroEntrance(delayMillis = 0)
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
            }
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = RetroTheme.spacing.lg),
                verticalArrangement = Arrangement.spacedBy(RetroTheme.spacing.sm),
            ) {
                actions()
            }
        }
    }
}