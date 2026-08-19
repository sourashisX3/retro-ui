package com.funapp.retroui.features.collection.presentation.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.List
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.funapp.retroui.core.design.components.controls.RetroButton
import com.funapp.retroui.core.design.components.controls.RetroButtonVariant
import com.funapp.retroui.core.design.theme.RetroTheme
import com.funapp.retroui.core.ui.placeholder.RetroPlaceholderScreen
import org.jetbrains.compose.resources.stringResource
import retroui.shared.generated.resources.Res
import retroui.shared.generated.resources.btn_deck_builder
import retroui.shared.generated.resources.common_back_to_menu
import retroui.shared.generated.resources.screen_collection_subtitle
import retroui.shared.generated.resources.screen_collection_title

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
        title = stringResource(Res.string.screen_collection_title),
        subtitle = stringResource(Res.string.screen_collection_subtitle),
        icon = Icons.Filled.List,
        modifier = modifier,
        action = {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(RetroTheme.spacing.sm),
            ) {
                RetroButton(
                    text = stringResource(Res.string.btn_deck_builder),
                    variant = RetroButtonVariant.Secondary,
                    onClick = onGoDeckBuilder,
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