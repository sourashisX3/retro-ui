package com.funapp.retroui.features.home.presentation.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import com.funapp.retroui.core.design.components.controls.RetroButton
import com.funapp.retroui.core.design.components.foundation.RetroText
import com.funapp.retroui.core.design.components.surfaces.RetroScreen
import com.funapp.retroui.core.design.theme.RetroTheme
import com.funapp.retroui.features.home.presentation.components.DailyQuests
import com.funapp.retroui.features.home.presentation.components.DeckSnapshot
import com.funapp.retroui.features.home.presentation.components.HomeHeader
import com.funapp.retroui.features.home.presentation.components.PlayerBanner
import org.jetbrains.compose.resources.stringResource
import retroui.shared.generated.resources.Res
import retroui.shared.generated.resources.btn_start_battle
import retroui.shared.generated.resources.home_battle_hint

/**
 * Home / main menu. Player summary, the battle CTA, a deck snapshot and the
 * daily quests preview — the landing zone after login.
 */
@Composable
fun HomeScreen(
    onGoBattle: () -> Unit,
    onGoCollection: () -> Unit,
    onGoQuests: () -> Unit,
    onGoProfile: () -> Unit,
    onGoSettings: () -> Unit,
    onGoDeckBuilder: () -> Unit,
    modifier: Modifier = Modifier,
) {
    RetroScreen(modifier = modifier) {
        item {
            HomeHeader(onGoSettings = onGoSettings)
        }
        item {
            Spacer(modifier = Modifier.height(RetroTheme.spacing.sm))
        }
        item {
            PlayerBanner(onClick = onGoProfile)
        }
        item {
            Spacer(modifier = Modifier.height(RetroTheme.spacing.lg))
        }
        item {
            Column {
                RetroButton(
                    text = stringResource(Res.string.btn_start_battle),
                    leadingIcon = Icons.Filled.PlayArrow,
                    onClick = onGoBattle,
                    modifier = Modifier.fillMaxWidth(),
                )
                Spacer(modifier = Modifier.height(RetroTheme.spacing.xs))
                RetroText(
                    text = stringResource(Res.string.home_battle_hint),
                    style = RetroTheme.typography.caption,
                    color = RetroTheme.colors.textMuted,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth(),
                )
            }
        }
        item {
            Spacer(modifier = Modifier.height(RetroTheme.spacing.lg))
        }
        item {
            DeckSnapshot(onDeckBuilder = onGoDeckBuilder)
        }
        item {
            Spacer(modifier = Modifier.height(RetroTheme.spacing.lg))
        }
        item {
            DailyQuests(onViewAll = onGoQuests)
        }
    }
}