package com.funapp.retroui.features.quests.presentation.screens

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.funapp.retroui.core.design.components.controls.RetroButton
import com.funapp.retroui.core.design.components.controls.RetroButtonVariant
import com.funapp.retroui.core.ui.placeholder.RetroPlaceholderScreen
import org.jetbrains.compose.resources.stringResource
import retroui.shared.generated.resources.Res
import retroui.shared.generated.resources.common_back_to_menu
import retroui.shared.generated.resources.screen_quests_subtitle
import retroui.shared.generated.resources.screen_quests_title

/**
 * Quests placeholder. Rebuilt in the quests phase with daily/weekly lists
 * and progress bars.
 */
@Composable
fun QuestsScreen(
    onGoHome: () -> Unit,
    modifier: Modifier = Modifier,
) {
    RetroPlaceholderScreen(
        title = stringResource(Res.string.screen_quests_title),
        subtitle = stringResource(Res.string.screen_quests_subtitle),
        icon = Icons.Filled.CheckCircle,
        modifier = modifier,
        action = {
            RetroButton(
                text = stringResource(Res.string.common_back_to_menu),
                variant = RetroButtonVariant.Outline,
                onClick = onGoHome,
            )
        },
    )
}