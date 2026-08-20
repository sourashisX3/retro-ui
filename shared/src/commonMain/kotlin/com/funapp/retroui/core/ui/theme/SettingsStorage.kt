package com.funapp.retroui.core.ui.theme

import androidx.compose.runtime.Composable

/** Keys under which app settings are persisted. */
const val KEY_THEME_MODE = "theme_mode"
const val KEY_SOUND_ENABLED = "sound_enabled"
const val KEY_HAPTICS_ENABLED = "haptics_enabled"
const val KEY_MUSIC_ENABLED = "music_enabled"
const val KEY_SHOW_ENEMY_HP = "show_enemy_hp"
const val KEY_QUICK_BATTLE = "quick_battle"
const val KEY_GYRO_TILT_ENABLED = "gyro_tilt_enabled"

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
