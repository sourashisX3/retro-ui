package com.funapp.retroui.features.collection.presentation.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.funapp.retroui.core.design.components.controls.RetroButton
import com.funapp.retroui.core.design.components.controls.RetroButtonVariant
import com.funapp.retroui.core.design.theme.RetroTheme
import com.funapp.retroui.core.ui.placeholder.RetroPlaceholderScreen

/**
 * Collection placeholder. Rebuilt in the collection phase with the
 * character/card grid, filters and search.
 */
@Composable
fun CollectionScreen(
    onGoDeckBuilder: () -> Unit,
    onGoHome: () -> Unit,
    modifier: Modifier = Modifier,
) {
    RetroPlaceholderScreen(
        title = "COLLECTION",
        subtitle = "Browse every card you've earned. (Coming soon)",
        modifier = modifier,
        emoji = "🃏",
        action = {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(RetroTheme.spacing.sm),
            ) {
                RetroButton(text = "DECK BUILDER", variant = RetroButtonVariant.Secondary, onClick = onGoDeckBuilder)
                RetroButton(
                    text = "BACK TO MENU",
                    variant = RetroButtonVariant.Outline,
                    onClick = onGoHome,
                )
            }
        },
    )
}