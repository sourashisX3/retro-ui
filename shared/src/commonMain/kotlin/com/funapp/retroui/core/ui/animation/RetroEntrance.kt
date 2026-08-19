package com.funapp.retroui.core.ui.animation

import androidx.compose.animation.core.Animatable
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
 * Fluid staggered entrance for screen content.
 *
 * Fades the element in and slides it up from [fromY] using the shared
 * [RetroAnimation.liquid] spring. Runs once on first composition, so inside
 * a `LazyColumn` each item animates as it scrolls into view. Pass increasing
 * [delayMillis] per item for the cascade effect.
 */
@Composable
fun Modifier.retroEntrance(
    delayMillis: Int = 0,
    fromY: Dp = 12.dp,
): Modifier {
    val alpha = remember { Animatable(0f) }
    val offsetY = remember { Animatable(fromY.value) }

    LaunchedEffect(Unit) {
        delay(delayMillis.toLong())
        launch {
            alpha.animateTo(1f, animationSpec = RetroAnimation.liquid)
        }
        offsetY.animateTo(0f, animationSpec = RetroAnimation.liquid)
    }

    return this.graphicsLayer {
        this.alpha = alpha.value
        translationY = offsetY.value * density
        transformOrigin = TransformOrigin.Center
    }
}

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
