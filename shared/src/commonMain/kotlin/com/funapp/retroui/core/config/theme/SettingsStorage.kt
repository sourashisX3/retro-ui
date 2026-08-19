package com.funapp.retroui.core.config.theme

import androidx.compose.runtime.Composable

/** Key under which the theme preference is persisted. */
const val KEY_THEME_MODE = "theme_mode"

/**
 * Minimal platform key-value persistence for app settings.
 *
 * Actuals are backed by SharedPreferences (Android), NSUserDefaults (iOS),
 * Preferences (JVM) and localStorage (JS/Wasm) so the theme choice survives
 * app restarts on every target.
 */
interface SettingsStorage {
    fun read(key: String): String?

    fun write(key: String, value: String)

    fun remove(key: String)
}

/** Platform handle resolved at composition time (needs a Context on Android). */
@Composable
expect fun rememberPlatformSettingsStorage(): SettingsStorage