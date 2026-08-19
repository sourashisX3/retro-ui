package com.funapp.retroui.core.ui.placeholder

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.funapp.retroui.core.design.components.feedback.RetroEmptyState
import com.funapp.retroui.core.design.components.surfaces.RetroScreen
import com.funapp.retroui.core.design.theme.RetroTheme

/**
 * Interim placeholder used by features not yet implemented. Renders the
 * screen's [title]/[subtitle] centred on a safe-area-aware [RetroScreen]
 * with an optional [action] slot (buttons etc.). Feature screens are rebuilt
 * in their own phase; this keeps navigation wired end-to-end meanwhile.
 */
@Composable
fun RetroPlaceholderScreen(
    title: String,
    subtitle: String,
    modifier: Modifier = Modifier,
    emoji: String = "🚧",
    action: (@Composable () -> Unit)? = null,
) {
    RetroScreen(modifier = modifier) {
        item {
            Box(
                modifier = Modifier
                    .fillParentMaxSize()
                    .padding(bottom = RetroTheme.spacing.xxl),
                contentAlignment = Alignment.Center,
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    RetroEmptyState(
                        title = title,
                        subtitle = subtitle,
                        emoji = emoji,
                        action = action,
                    )
                }
            }
        }
    }
}