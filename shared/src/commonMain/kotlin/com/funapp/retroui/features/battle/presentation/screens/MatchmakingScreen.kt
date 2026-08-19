package com.funapp.retroui.features.battle.presentation.screens

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.dp
import com.funapp.retroui.core.data.mock.MockChampion
import com.funapp.retroui.core.ui.animation.RetroEntranceStyle
import com.funapp.retroui.core.ui.animation.retroEntrance
import com.funapp.retroui.core.ui.components.controls.RetroButton
import com.funapp.retroui.core.ui.components.controls.RetroButtonVariant
import com.funapp.retroui.core.ui.components.foundation.RetroText
import com.funapp.retroui.core.ui.components.surfaces.RetroScreenStatic
import com.funapp.retroui.core.ui.icons.RetroIcons
import com.funapp.retroui.core.ui.icons.Search
import com.funapp.retroui.core.ui.theme.RetroTheme
import com.funapp.retroui.core.ui.token.RetroMotion
import com.funapp.retroui.features.battle.data.MatchmakingRepository
import org.jetbrains.compose.resources.stringResource
import retroui.shared.generated.resources.Res
import retroui.shared.generated.resources.btn_cancel_search
import retroui.shared.generated.resources.matchmaking_subtitle
import retroui.shared.generated.resources.matchmaking_title

/**
 * Matchmaking queue. A radar-style spinner rotates arcade-style while the
 * repository searches; the found opponent is handed back via [onFound].
 * [onCancel] pops back to the previous screen.
 */
@Composable
fun MatchmakingScreen(
    repository: MatchmakingRepository,
    onFound: (MockChampion) -> Unit,
    onCancel: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val colors = RetroTheme.colors

    val radar = rememberInfiniteTransition(label = "matchmakingRadar")
    val spin by radar.animateFloat(
        initialValue = 0f,
        targetValue = 360f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 2200, easing = LinearEasing),
        ),
        label = "matchmakingSpin",
    )
    val statusBlink by radar.animateFloat(
        initialValue = 0.3f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = RetroMotion.NormalMs, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse,
        ),
        label = "matchmakingBlink",
    )

    LaunchedEffect(Unit) {
        onFound(repository.findOpponent())
    }

    RetroScreenStatic(modifier = modifier) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(RetroTheme.spacing.xl),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = androidx.compose.foundation.layout.Arrangement.Center,
        ) {
            Box(
                modifier = Modifier
                    .size(96.dp)
                    .retroEntrance(style = RetroEntranceStyle.Coin, delayMillis = 0)
                    .clip(CircleShape)
                    .background(colors.surfaceVariant)
                    .border(BorderStroke(RetroTheme.borders.default, colors.outlineStrong), CircleShape),
                contentAlignment = Alignment.Center,
            ) {
                Icon(
                    imageVector = RetroIcons.Search,
                    contentDescription = null,
                    tint = colors.accent,
                    modifier = Modifier
                        .size(44.dp)
                        .graphicsLayer { rotationZ = spin },
                )
            }
            Spacer(modifier = Modifier.height(RetroTheme.spacing.lg))
            RetroText(
                text = stringResource(Res.string.matchmaking_title),
                style = RetroTheme.typography.heading,
                color = colors.textPrimary,
                modifier = Modifier.retroEntrance(style = RetroEntranceStyle.Stomp, delayMillis = 60),
            )
            Spacer(modifier = Modifier.height(RetroTheme.spacing.xs))
            RetroText(
                text = stringResource(Res.string.matchmaking_subtitle),
                style = RetroTheme.typography.caption,
                color = colors.textSecondary,
                modifier = Modifier
                    .retroEntrance(style = RetroEntranceStyle.Stomp, delayMillis = 120)
                    .graphicsLayer { alpha = statusBlink },
            )
            Spacer(modifier = Modifier.height(RetroTheme.spacing.xxl))
            RetroButton(
                text = stringResource(Res.string.btn_cancel_search),
                variant = RetroButtonVariant.Outline,
                onClick = onCancel,
                small = true,
                modifier = Modifier.retroEntrance(style = RetroEntranceStyle.Pop, delayMillis = 180),
            )
        }
    }
}