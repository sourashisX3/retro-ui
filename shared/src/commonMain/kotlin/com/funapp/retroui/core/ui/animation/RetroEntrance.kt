package com.funapp.retroui.core.ui.animation

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.FiniteAnimationSpec
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.interaction.InteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.TransformOrigin
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.funapp.retroui.core.ui.token.RetroAnimation
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

/**
 * Arcade motion personality for entrances. Every element type has its own
 * animation language — cards pop, panels rise, HUD blips like coins, titles
 * slam down. All transforms run on [graphicsLayer] (alpha / translation /
 * scale only — never layout), so entrances are always smooth.
 *
 *  - [Rise]: fade + rise. Panels, banners, sections, forms.
 *  - [Pop]: fade + scale up + small rise. Game cards, stat cards, slots, chips.
 *  - [Coin]: fade + small-scale blip. Icons, badges, HUD, log lines.
 *  - [SlideLeft]: fade + slide in from the left. Rows and side elements.
 *  - [Stomp]: slams down onto place, no fade. Section titles.
 */
enum class RetroEntranceStyle {
    Rise,
    Pop,
    Coin,
    SlideLeft,
    Stomp,
}

private val RetroEntranceStyle.travelY: Dp
    get() = when (this) {
        RetroEntranceStyle.Rise -> 24.dp
        RetroEntranceStyle.Pop -> 10.dp
        RetroEntranceStyle.Coin -> 6.dp
        RetroEntranceStyle.SlideLeft -> 0.dp
        RetroEntranceStyle.Stomp -> 0.dp
    }

private val RetroEntranceStyle.travelX: Dp
    get() = when (this) {
        RetroEntranceStyle.SlideLeft -> 32.dp
        else -> 0.dp
    }

private val RetroEntranceStyle.startScale: Float
    get() = when (this) {
        RetroEntranceStyle.Pop -> 0.84f
        RetroEntranceStyle.Coin -> 0.6f
        RetroEntranceStyle.Stomp -> 1.06f
        else -> 1f
    }

private val RetroEntranceStyle.startsHidden: Boolean
    get() = this != RetroEntranceStyle.Stomp

private val RetroEntranceStyle.scaleSpec: FiniteAnimationSpec<Float>
    get() = if (this == RetroEntranceStyle.Stomp) RetroAnimation.arcade else RetroAnimation.pop

/**
 * Staggered arcade entrance for screen content.
 *
 * Runs once on first composition, so inside a `LazyColumn` each item
 * animates as it scrolls into view. Pass increasing [delayMillis] per item
 * for the cascade effect (see [retroCascade]). [fromY] / [fromX] override
 * the style's default travel for directional entrances (e.g. an opponent
 * card dropping in from the top with `fromY = -12.dp`).
 */
@Composable
fun Modifier.retroEntrance(
    style: RetroEntranceStyle = RetroEntranceStyle.Rise,
    delayMillis: Int = 0,
    fromY: Dp? = null,
    fromX: Dp? = null,
): Modifier {
    val travelY = fromY ?: style.travelY
    val travelX = fromX ?: style.travelX
    val alpha = remember { Animatable(if (style.startsHidden) 0f else 1f) }
    val offsetX = remember { Animatable(travelX.value) }
    val offsetY = remember { Animatable(travelY.value) }
    val scale = remember { Animatable(style.startScale) }

    LaunchedEffect(Unit) {
        delay(delayMillis.toLong())
        launch {
            alpha.animateTo(1f, animationSpec = RetroAnimation.arcade)
        }
        launch {
            offsetX.animateTo(0f, animationSpec = RetroAnimation.arcade)
            offsetY.animateTo(0f, animationSpec = RetroAnimation.arcade)
        }
        launch {
            scale.animateTo(1f, animationSpec = style.scaleSpec)
        }
    }

    return this.graphicsLayer {
        this.alpha = alpha.value
        translationX = offsetX.value * density
        translationY = offsetY.value * density
        scaleX = scale.value
        scaleY = scale.value
        transformOrigin = TransformOrigin.Center
    }
}

/**
 * Uniform stagger cadence for cascades: `retroCascade(0)` = 0ms,
 * `retroCascade(1)` = 60ms, `retroCascade(2)` = 120ms, ... Use with
 * [retroEntrance] in list/grid item loops so every screen shares the same
 * musical step.
 */
fun retroCascade(index: Int, stepMs: Int = 60): Int = index * stepMs

/**
 * Pixel-pop press feedback for shadowless compact controls (switch, checkbox,
 * radio, bottom-bar tabs): quick scale-down + sink while pressed, springy
 * overshoot back on release — a playful arcade blip.
 *
 * Distinct from `retroTactilePress` (hard-shadow collapse + sink): controls
 * without a hard shadow use this so the press language stays two-tier.
 * Wire the SAME [InteractionSource] that backs the component's `clickable`.
 */
@Composable
fun Modifier.retroPopPress(
    interactionSource: InteractionSource,
    pressedScale: Float = 0.88f,
    sinkY: Dp = 2.dp,
): Modifier {
    val pressed by interactionSource.collectIsPressedAsState()
    val scale by animateFloatAsState(
        targetValue = if (pressed) pressedScale else 1f,
        animationSpec = if (pressed) RetroAnimation.press else RetroAnimation.pop,
        label = "retroPopScale",
    )
    val sink by animateFloatAsState(
        targetValue = if (pressed) sinkY.value else 0f,
        animationSpec = if (pressed) RetroAnimation.press else RetroAnimation.pop,
        label = "retroPopSink",
    )
    return this.graphicsLayer {
        scaleX = scale
        scaleY = scale
        translationY = sink * density
    }
}