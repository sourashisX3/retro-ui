package com.funapp.retroui.features.home.presentation.screens
import com.funapp.retroui.core.ui.icons.RetroIcons

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.keyframes
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.unit.dp
import com.funapp.retroui.core.ui.icons.PlayArrow
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import com.funapp.retroui.core.ui.animation.RetroEntranceStyle
import com.funapp.retroui.core.ui.animation.retroEntrance
import com.funapp.retroui.core.ui.components.controls.RetroButton
import com.funapp.retroui.core.ui.components.foundation.RetroText
import com.funapp.retroui.core.ui.components.surfaces.RetroScreen
import com.funapp.retroui.core.ui.theme.RetroTheme
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
            HomeHeader(
                onGoSettings = onGoSettings,
                modifier = Modifier.retroEntrance(delayMillis = 0),
            )
        }
        item {
            Spacer(modifier = Modifier.height(RetroTheme.spacing.sm))
        }
        item {
            PlayerBanner(
                onClick = onGoProfile,
                modifier = Modifier.retroEntrance(delayMillis = 60),
            )
        }
        item {
            Spacer(modifier = Modifier.height(RetroTheme.spacing.lg))
        }
        item {
            Column(modifier = Modifier.retroEntrance(style = RetroEntranceStyle.Pop, delayMillis = 120)) {
                val shine = rememberInfiniteTransition(label = "battleShine")
                val shineOffset by shine.animateFloat(
                    initialValue = -1.4f,
                    targetValue = 1.4f,
                    animationSpec = infiniteRepeatable(
                        animation = keyframes {
                            durationMillis = 1800
                            -1.4f at 0 with LinearEasing
                            1.4f at 1300 with LinearEasing
                            1.4f at 1800
                        },
                        repeatMode = RepeatMode.Restart,
                    ),
                    label = "battleShineOffset",
                )
                var buttonWidthPx by remember { mutableStateOf(0) }
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .onSizeChanged { buttonWidthPx = it.width },
                ) {
                    RetroButton(
                        text = stringResource(Res.string.btn_start_battle),
                        leadingIcon = RetroIcons.PlayArrow,
                        onClick = onGoBattle,
                        modifier = Modifier.fillMaxWidth(),
                    )
                    Box(
                        modifier = Modifier
                            .matchParentSize()
                            .clip(RetroTheme.shapeTokens.button),
                    ) {
                        Box(
                            modifier = Modifier
                                .align(Alignment.Center)
                                .size(
                                    width = 36.dp,
                                    height = RetroTheme.dimensions.buttonHeight * 2,
                                )
                                .graphicsLayer {
                                    translationX = buttonWidthPx * shineOffset
                                    rotationZ = 25f
                                }
                                .background(
                                    brush = Brush.linearGradient(
                                        colors = listOf(
                                            Color.Transparent,
                                            Color.White.copy(alpha = 0.35f),
                                            Color.Transparent,
                                        ),
                                    ),
                                ),
                        )
                    }
                }
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
            DeckSnapshot(
                onDeckBuilder = onGoDeckBuilder,
                modifier = Modifier.retroEntrance(delayMillis = 180),
            )
        }
        item {
            Spacer(modifier = Modifier.height(RetroTheme.spacing.lg))
        }
        item {
            DailyQuests(
                onViewAll = onGoQuests,
                modifier = Modifier.retroEntrance(delayMillis = 240),
            )
        }
    }
}
