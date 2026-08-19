package com.funapp.retroui.core.ui.theme

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

/**
 * App-wide settings, persisted through [SettingsStorage] so choices survive
 * restarts. Owned by the root [com.funapp.retroui.core.di.AppContainer].
 */
class SettingsRepository(private val storage: SettingsStorage) {

    private val _themeMode = MutableStateFlow(readThemeMode())
    val themeMode: StateFlow<ThemeMode> = _themeMode.asStateFlow()

    fun setThemeMode(mode: ThemeMode) {
        _themeMode.value = mode
        storage.write(KEY_THEME_MODE, mode.name)
    }

    private fun readThemeMode(): ThemeMode = storage.read(KEY_THEME_MODE)
        ?.let { stored -> ThemeMode.entries.firstOrNull { it.name == stored } }
        ?: ThemeMode.System
}
