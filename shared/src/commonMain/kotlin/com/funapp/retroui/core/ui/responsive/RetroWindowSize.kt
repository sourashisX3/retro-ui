package com.funapp.retroui.core.ui.responsive

import androidx.compose.foundation.layout.BoxWithConstraintsScope
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

/**
 * Retro Duel window-size classes for responsive/adaptive layouts.
 * Thresholds follow Material width classes (600/840 dp).
 */
enum class RetroWindowSize {
    Compact,
    Medium,
    Expanded,
}

/**
 * Snapshot the current window width class. Call inside a
 * `BoxWithConstraints` scope (or feed [width] from any other source).
 */
fun BoxWithConstraintsScope.rememberRetroWindowSize(): RetroWindowSize =
    retroWindowSize(maxWidth)

fun retroWindowSize(width: Dp): RetroWindowSize = when {
    width < 600.dp -> RetroWindowSize.Compact
    width < 840.dp -> RetroWindowSize.Medium
    else -> RetroWindowSize.Expanded
}

/** Phone portrait / small window. */
val RetroWindowSize.isCompact: Boolean
    get() = this == RetroWindowSize.Compact

/** Tablet landscape / desktop side-by-side layouts. */
val RetroWindowSize.isExpanded: Boolean
    get() = this == RetroWindowSize.Expanded