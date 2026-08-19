package com.funapp.retroui.features.home.presentation.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.funapp.retroui.core.design.components.controls.RetroButton
import com.funapp.retroui.core.design.components.controls.RetroButtonVariant
import com.funapp.retroui.core.design.theme.RetroTheme
import com.funapp.retroui.core.ui.placeholder.RetroPlaceholderScreen
import org.jetbrains.compose.resources.stringResource
import retroui.shared.generated.resources.Res
import retroui.shared.generated.resources.btn_collection
import retroui.shared.generated.resources.btn_profile
import retroui.shared.generated.resources.btn_quests
import retroui.shared.generated.resources.btn_settings
import retroui.shared.generated.resources.btn_start_battle
import retroui.shared.generated.resources.screen_home_subtitle
import retroui.shared.generated.resources.screen_home_title

/**
 * Home placeholder. Rebuilt in the home phase with player summary,
 * daily quests and the battle CTA.
 */
@Composable
fun HomeScreen(
    onGoBattle: () -> Unit,
    onGoCollection: () -> Unit,
    onGoQuests: () -> Unit,
    onGoProfile: () -> Unit,
    onGoSettings: () -> Unit,
    modifier: Modifier = Modifier,
) {
    RetroPlaceholderScreen(
        title = stringResource(Res.string.screen_home_title),
        subtitle = stringResource(Res.string.screen_home_subtitle),
        icon = Icons.Filled.Home,
        modifier = modifier,
        action = {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(RetroTheme.spacing.sm),
            ) {
                RetroButton(
                    text = stringResource(Res.string.btn_start_battle),
                    leadingIcon = Icons.Filled.PlayArrow,
                    onClick = onGoBattle,
                )
                RetroButton(
                    text = stringResource(Res.string.btn_collection),
                    variant = RetroButtonVariant.Secondary,
                    onClick = onGoCollection,
                )
                RetroButton(
                    text = stringResource(Res.string.btn_quests),
                    variant = RetroButtonVariant.Accent,
                    onClick = onGoQuests,
                )
                RetroButton(
                    text = stringResource(Res.string.btn_profile),
                    variant = RetroButtonVariant.Outline,
                    onClick = onGoProfile,
                )
                RetroButton(
                    text = stringResource(Res.string.btn_settings),
                    variant = RetroButtonVariant.Outline,
                    onClick = onGoSettings,
                )
            }
        },
    )
}