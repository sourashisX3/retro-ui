package com.funapp.retroui

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.tooling.preview.Preview
import com.funapp.retroui.core.ui.theme.SettingsRepository
import com.funapp.retroui.core.ui.theme.ThemeMode
import com.funapp.retroui.core.ui.theme.rememberPlatformSettingsStorage
import com.funapp.retroui.core.config.navigation.AppNavHost
import com.funapp.retroui.core.di.AppContainer
import com.funapp.retroui.core.di.LocalAppContainer
import com.funapp.retroui.core.ui.theme.RetroTheme

/**
 * Root composable. Provides the app container, resolves the theme preference
 * (system / light / dark from Settings) and hosts the typed navigation graph
 * (splash → onboarding → auth → main tabs with the retro bottom bar).
 */
@Composable
@Preview
fun App() {
    val storage = rememberPlatformSettingsStorage()
    val container = remember {
        AppContainer(settingsRepository = SettingsRepository(storage))
    }
    val themeMode by container.settingsRepository.themeMode.collectAsState()
    val darkTheme = when (themeMode) {
        ThemeMode.System -> isSystemInDarkTheme()
        ThemeMode.Light -> false
        ThemeMode.Dark -> true
    }

    CompositionLocalProvider(LocalAppContainer provides container) {
        RetroTheme(darkTheme = darkTheme) {
            AppNavHost()
        }
    }
}
