package com.funapp.retroui.features.deck.presentation.screens

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
 * Deck-builder placeholder. Rebuilt in the deck-builder phase with the
 * deck list, card slots and saved-deck management.
 */
@Composable
fun DeckBuilderScreen(
    onGoCollection: () -> Unit,
    onGoHome: () -> Unit,
    modifier: Modifier = Modifier,
) {
    RetroPlaceholderScreen(
        title = "DECK BUILDER",
        subtitle = "Assemble your deck of 5 champions. (Coming soon)",
        modifier = modifier,
        emoji = "🗃️",
        action = {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(RetroTheme.spacing.sm),
            ) {
                RetroButton(
                    text = "BROWSE COLLECTION",
                    variant = RetroButtonVariant.Secondary,
                    onClick = onGoCollection,
                )
                RetroButton(
                    text = "BACK TO MENU",
                    variant = RetroButtonVariant.Outline,
                    onClick = onGoHome,
                )
            }
        },
    )
}