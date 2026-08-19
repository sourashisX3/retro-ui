package com.funapp.retroui.core.ui.token

import androidx.compose.animation.core.CubicBezierEasing
import androidx.compose.animation.core.DurationBasedAnimationSpec
import androidx.compose.animation.core.Easing
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.FiniteAnimationSpec
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.keyframes
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween

/**
 * Motion system.
 *
 * Motion is a first-class part of the design system. All animations consume
 * these tokens — never invent arbitrary durations/easings.
 *
 * Rule: animate STATE, not content. Press feedback is quick (100-150ms) to
 * feel like physically pressing an arcade control.
 */
object RetroMotion {
    // Press / tactile feedback: shadow collapse, button sink
    const val FastMs = 120
    // Standard transitions: cards, sheets, reveal
    const val NormalMs = 240
    // Slower feedback: major state changes
    const val SlowMs = 400
    // Expressive: success burst, onboarding
    const val ExpressiveMs = 600

    val StandardEasing: Easing = FastOutSlowInEasing
    val EmphasizedEasing: Easing = CubicBezierEasing(0.2f, 0f, 0f, 1f)
    val DecelerateEasing: Easing = LinearOutSlowInEasing
    val AccelerateEasing: Easing = CubicBezierEasing(0.4f, 0f, 1f, 1f)
}

/**
 * Named, reusable animation specs.
 *
 * These are the ONLY animation specs allowed in feature code. Each one maps
 * to a physical/arcade interaction in the game personality:
 *
 *  - [press]: control sinks & shadow collapses (120ms)
 *  - [pop]: small snap/scale pulse (springy)
 *  - [bounce]: playful overshoot (keyframed)
 *  - [shake]: damage / error wobble (keyframed)
 *  - [slide]: screen / panel movement
 *  - [fade]: simple appearance
 *  - [flip]: card reveal (rotation)
 *  - [cardReveal]: card draw / play
 *  - [draw]: card sliding from deck into hand
 *
 * All are `FiniteAnimationSpec<Float>` so they work with
 * `Animatable`, `animate*AsState`, and `graphicsLayer` scale/rotation.
 */
object RetroAnimation {
    val press: DurationBasedAnimationSpec<Float> = tween(RetroMotion.FastMs, easing = RetroMotion.StandardEasing)

    val pop: FiniteAnimationSpec<Float> = spring(
        dampingRatio = 0.45f,
        stiffness = 900f,
    )

    val bounce: DurationBasedAnimationSpec<Float> = keyframes {
        durationMillis = RetroMotion.SlowMs
        0f at 0
        1f at RetroMotion.FastMs
        0.8f at (RetroMotion.FastMs + RetroMotion.NormalMs / 2)
        1f at durationMillis
    }

    val shake: DurationBasedAnimationSpec<Float> = keyframes {
        durationMillis = RetroMotion.SlowMs
        0f at 0
        -1f at 60
        1f at 120
        -0.6f at 180
        0.6f at 240
        0f at durationMillis
    }

    val slide: DurationBasedAnimationSpec<Float> = tween(RetroMotion.NormalMs, easing = RetroMotion.DecelerateEasing)

    val fade: DurationBasedAnimationSpec<Float> = tween(RetroMotion.NormalMs, easing = RetroMotion.AccelerateEasing)

    val flip: DurationBasedAnimationSpec<Float> = tween(RetroMotion.SlowMs, easing = RetroMotion.EmphasizedEasing)

    val cardReveal: FiniteAnimationSpec<Float> = spring(
        dampingRatio = 0.5f,
        stiffness = 500f,
    )

    val liquid: FiniteAnimationSpec<Float> = spring(
        dampingRatio = 0.72f,
        stiffness = 380f,
    )

    val draw: DurationBasedAnimationSpec<Float> = tween(RetroMotion.NormalMs, easing = RetroMotion.EmphasizedEasing)
}
