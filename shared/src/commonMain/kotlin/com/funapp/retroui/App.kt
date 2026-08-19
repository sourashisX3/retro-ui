package com.funapp.retroui

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.funapp.retroui.core.config.navigation.AppNavHost
import com.funapp.retroui.core.design.theme.RetroTheme

/**
 * Root composable. Applies the retro design-system theme (system light/dark
 * by default) and hosts the typed navigation graph. The design-system
 * showcase is the current start destination until the splash screen lands.
 */
@Composable
@Preview
fun App() {
    RetroTheme {
        AppNavHost()
    }
}