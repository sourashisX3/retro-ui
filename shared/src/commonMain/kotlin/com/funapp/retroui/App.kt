package com.funapp.retroui

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.funapp.retroui.core.design.DesignSystemScreen
import com.funapp.retroui.core.design.theme.RetroTheme

/**
 * Root composable. Applies the retro design-system theme (system light/dark
 * by default) and shows the design-system showcase as the first screen.
 */
@Composable
@Preview
fun App() {
    RetroTheme {
        DesignSystemScreen()
    }
}