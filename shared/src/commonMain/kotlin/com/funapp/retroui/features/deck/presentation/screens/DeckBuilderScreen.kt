package com.funapp.retroui.features.deck.presentation.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Create
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.funapp.retroui.core.design.components.controls.RetroButton
import com.funapp.retroui.core.design.components.controls.RetroButtonVariant
import com.funapp.retroui.core.design.theme.RetroTheme
import com.funapp.retroui.core.ui.placeholder.RetroPlaceholderScreen
import org.jetbrains.compose.resources.stringResource
import retroui.shared.generated.resources.Res
import retroui.shared.generated.resources.btn_browse_collection
import retroui.shared.generated.resources.common_back_to_menu
import retroui.shared.generated.resources.screen_deck_subtitle
import retroui.shared.generated.resources.screen_deck_title

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
        title = stringResource(Res.string.screen_deck_title),
        subtitle = stringResource(Res.string.screen_deck_subtitle),
        icon = Icons.Filled.Create,
        modifier = modifier,
        action = {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(RetroTheme.spacing.sm),
            ) {
                RetroButton(
                    text = stringResource(Res.string.btn_browse_collection),
                    variant = RetroButtonVariant.Secondary,
                    onClick = onGoCollection,
                )
                RetroButton(
                    text = stringResource(Res.string.common_back_to_menu),
                    variant = RetroButtonVariant.Outline,
                    onClick = onGoHome,
                )
            }
        },
    )
}