package com.funapp.retroui.core.design.animation

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
import com.funapp.retroui.core.design.token.RetroAnimation
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
 * Arcade press feedback for interactive surfaces: sinks ~2dp and scales
 * down slightly while pressed, springs back on release. Wire the SAME
 * [InteractionSource] that backs the component's `clickable`.
 */
@Composable
fun Modifier.retroPressFeedback(interactionSource: InteractionSource): Modifier {
    val pressed by interactionSource.collectIsPressedAsState()
    val scale by animateFloatAsState(
        targetValue = if (pressed) 0.96f else 1f,
        animationSpec = RetroAnimation.press,
        label = "retroPressScale",
    )
    val sink by animateFloatAsState(
        targetValue = if (pressed) 2f else 0f,
        animationSpec = RetroAnimation.press,
        label = "retroPressSink",
    )
    return this.graphicsLayer {
        scaleX = scale
        scaleY = scale
        translationY = sink * density
    }
}