package com.funapp.retroui.features.splash.presentation

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.infiniteRepeatable
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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.style.TextAlign
import com.funapp.retroui.core.design.components.branding.RetroDuelLogo
import com.funapp.retroui.core.design.components.branding.RetroLogoSize
import com.funapp.retroui.core.design.components.foundation.RetroText
import com.funapp.retroui.core.design.theme.RetroTheme
import com.funapp.retroui.core.design.token.RetroAnimation
import com.funapp.retroui.core.design.token.RetroMotion
import kotlinx.coroutines.delay
import org.jetbrains.compose.resources.stringResource
import retroui.shared.generated.resources.Res
import retroui.shared.generated.resources.app_tagline

private const val SplashScreenDurationMs = 2600L

/**
 * Splash screen: the RETRO DUEL wordmark pops in (spring scale + fade),
 * the tagline fades in, and a "PRESS START" hint blinks arcade-style.
 * Auto-advances to [onFinished] after [SplashScreenDurationMs].
 */
@Composable
fun SplashScreen(
    onFinished: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val colors = RetroTheme.colors
    val spacing = RetroTheme.spacing

    var started by remember { mutableStateOf(false) }
    LaunchedEffect(Unit) { started = true }

    val logoScale by animateFloatAsState(
        targetValue = if (started) 1f else 0.6f,
        animationSpec = RetroAnimation.pop,
        label = "splashLogoScale",
    )
    val logoAlpha by animateFloatAsState(
        targetValue = if (started) 1f else 0f,
        animationSpec = tween(
            durationMillis = RetroMotion.ExpressiveMs,
            delayMillis = RetroMotion.FastMs,
        ),
        label = "splashLogoAlpha",
    )
    val taglineAlpha by animateFloatAsState(
        targetValue = if (started) 1f else 0f,
        animationSpec = tween(
            durationMillis = RetroMotion.NormalMs,
            delayMillis = RetroMotion.ExpressiveMs,
        ),
        label = "splashTaglineAlpha",
    )

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
            modifier = Modifier.padding(spacing.xl),
        ) {
            RetroDuelLogo(
                size = RetroLogoSize.Large,
                modifier = Modifier.graphicsLayer {
                    scaleX = logoScale
                    scaleY = logoScale
                    alpha = logoAlpha
                },
            )
            Spacer(Modifier.height(spacing.lg))
            RetroText(
                text = stringResource(Res.string.app_tagline),
                style = RetroTheme.typography.body,
                color = colors.textSecondary,
                textAlign = TextAlign.Center,
                modifier = Modifier.alpha(taglineAlpha),
            )
            Spacer(Modifier.height(spacing.xxl))
            RetroText(
                text = "PRESS START",
                style = RetroTheme.typography.label,
                color = colors.accent,
                textAlign = TextAlign.Center,
                modifier = Modifier.alpha(blinkAlpha),
            )
        }
    }
}