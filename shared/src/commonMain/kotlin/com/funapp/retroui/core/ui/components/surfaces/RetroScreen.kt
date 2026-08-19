package com.funapp.retroui.core.ui.components.surfaces

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.funapp.retroui.core.ui.theme.RetroTheme

/**
 * Standard full-screen container for every Retro Duel screen.
 *
 * Draws the paper background edge-to-edge (behind system bars) and applies
 * [insets] padding to the content so it never collides with the status bar,
 * navigation bar/gesture area, cutouts, or (with [WindowInsets.safeDrawing])
 * the keyboard.
 *
 * All screens MUST be built on [RetroScreen] so safe-area handling stays
 * consistent. Insets resolve to 0 on desktop/web automatically.
 *
 * [content] receives a [LazyListScope] so callers can use `item { }` /
 * `items()` blocks directly; the body scrolls when it overflows.
 */
@Composable
fun RetroScreen(
    modifier: Modifier = Modifier,
    insets: WindowInsets = WindowInsets.safeDrawing,
    contentPadding: PaddingValues = PaddingValues(
        start = RetroTheme.spacing.lg,
        end = RetroTheme.spacing.lg,
        top = RetroTheme.spacing.lg,
        bottom = RetroTheme.dimensions.bottomBarClearance,
    ),
    content: LazyListScope.() -> Unit,
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(RetroTheme.colors.background),
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .windowInsetsPadding(insets),
            contentPadding = contentPadding,
            content = content,
        )
    }
}

/**
 * Static, non-scrollable variant of [RetroScreen] for fixed layouts
 * (e.g. battle fields, fixed split views). [content] is placed in a
 * [ColumnScope] so vertical arrangement/weights compose naturally.
 */
@Composable
fun RetroScreenStatic(
    modifier: Modifier = Modifier,
    insets: WindowInsets = WindowInsets.safeDrawing,
    contentPadding: PaddingValues = PaddingValues(
        start = RetroTheme.spacing.lg,
        end = RetroTheme.spacing.lg,
        top = RetroTheme.spacing.lg,
        bottom = RetroTheme.dimensions.bottomBarClearance,
    ),
    content: @Composable ColumnScope.() -> Unit,
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(RetroTheme.colors.background),
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .windowInsetsPadding(insets)
                .padding(contentPadding),
            content = content,
        )
    }
}
