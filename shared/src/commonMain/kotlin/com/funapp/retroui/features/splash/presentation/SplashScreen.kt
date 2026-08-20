package com.funapp.retroui.features.splash.presentation

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.keyframes
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.funapp.retroui.core.ui.components.branding.RetroOutlineText
import com.funapp.retroui.core.ui.components.foundation.RetroText
import com.funapp.retroui.core.ui.theme.RetroTheme
import com.funapp.retroui.core.ui.token.RetroAnimation
import com.funapp.retroui.core.ui.token.RetroMotion
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.jetbrains.compose.resources.stringResource
import retroui.shared.generated.resources.Res
import retroui.shared.generated.resources.app_tagline
import retroui.shared.generated.resources.splash_press_start
import retroui.shared.generated.resources.splash_wordmark_bottom
import retroui.shared.generated.resources.splash_wordmark_top

private const val SplashScreenDurationMs = 2600L

/**
 * Splash screen: the DECKRON wordmark drops in like a slot-machine reel (a
 * keyframed drop with a settle, not a stiff spring — smooth on every target),
 * the tagline rises on a soft [RetroAnimation.liquid] spring, and a
 * "PRESS START" hint blinks arcade-style. Auto-advances to [onFinished]
 * after [SplashScreenDurationMs].
 */
@Composable
fun SplashScreen(
    onFinished: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val colors = RetroTheme.colors
    val spacing = RetroTheme.spacing
    val pressStart = stringResource(Res.string.splash_press_start)

    val dropTop = remember { androidx.compose.animation.core.Animatable(-160f) }
    val dropBottom = remember { androidx.compose.animation.core.Animatable(-190f) }
    val taglineY = remember { androidx.compose.animation.core.Animatable(24f) }
    val taglineAlpha = remember { androidx.compose.animation.core.Animatable(0f) }
    val columnAlpha = remember { androidx.compose.animation.core.Animatable(0f) }

    LaunchedEffect(Unit) {
        launch { columnAlpha.animateTo(1f, tween(durationMillis = RetroMotion.NormalMs, easing = RetroMotion.DecelerateEasing)) }
        launch {
            dropTop.animateTo(
                targetValue = 0f,
                animationSpec = keyframes {
                    durationMillis = 720
                    -160f at 0
                    0f at 560 with RetroMotion.DecelerateEasing
                    -8f at 640
                    0f at 720
                },
            )
            dropBottom.animateTo(
                targetValue = 0f,
                animationSpec = keyframes {
                    durationMillis = 780
                    -190f at 0
                    0f at 610 with RetroMotion.DecelerateEasing
                    -10f at 700
                    0f at 780
                },
            )
        }
        launch {
            delay(RetroMotion.ExpressiveMs.toLong())
            taglineAlpha.animateTo(1f, tween(durationMillis = RetroMotion.NormalMs))
            taglineY.animateTo(0f, RetroAnimation.liquid)
        }
    }

    val blink = rememberInfiniteTransition(label = "pressStartBlink")
    val blinkAlpha by blink.animateFloat(
        initialValue = 0.25f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(
            animation = tween(RetroMotion.SlowMs, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse,
        ),
        label = "pressStartAlpha",
    )

    LaunchedEffect(Unit) {
        delay(SplashScreenDurationMs)
        onFinished()
    }

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(colors.background),
        contentAlignment = Alignment.Center,
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .padding(spacing.xl)
                .alpha(columnAlpha.value),
        ) {
            RetroOutlineText(
                text = stringResource(Res.string.splash_wordmark_top),
                style = RetroTheme.typography.display,
                fill = colors.primary,
                outline = colors.outlineStrong,
                shadow = colors.shadow,
                outlineWidth = 4.dp,
                modifier = Modifier.graphicsLayer { translationY = dropTop.value },
            )
            RetroOutlineText(
                text = stringResource(Res.string.splash_wordmark_bottom),
                style = RetroTheme.typography.display,
                fill = colors.primary,
                outline = colors.outlineStrong,
                shadow = colors.shadow,
                outlineWidth = 4.dp,
                modifier = Modifier
                    .padding(top = 2.dp)
                    .graphicsLayer { translationY = dropBottom.value },
            )
            Spacer(Modifier.height(spacing.lg))
            RetroText(
                text = stringResource(Res.string.app_tagline),
                style = RetroTheme.typography.body,
                color = colors.textSecondary,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .alpha(taglineAlpha.value)
                    .graphicsLayer { translationY = taglineY.value },
            )
            Spacer(Modifier.height(spacing.xxl))
            RetroText(
                text = pressStart,
                style = RetroTheme.typography.label,
                color = colors.accent,
                textAlign = TextAlign.Center,
                modifier = Modifier.alpha(blinkAlpha),
            )
        }
    }
}