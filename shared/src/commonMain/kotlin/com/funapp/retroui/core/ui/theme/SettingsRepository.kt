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

    private val _soundEnabled = MutableStateFlow(readBoolean(KEY_SOUND_ENABLED, true))
    val soundEnabled: StateFlow<Boolean> = _soundEnabled.asStateFlow()

    private val _hapticsEnabled = MutableStateFlow(readBoolean(KEY_HAPTICS_ENABLED, true))
    val hapticsEnabled: StateFlow<Boolean> = _hapticsEnabled.asStateFlow()

    private val _musicEnabled = MutableStateFlow(readBoolean(KEY_MUSIC_ENABLED, true))
    val musicEnabled: StateFlow<Boolean> = _musicEnabled.asStateFlow()

    private val _showEnemyHp = MutableStateFlow(readBoolean(KEY_SHOW_ENEMY_HP, true))
    val showEnemyHp: StateFlow<Boolean> = _showEnemyHp.asStateFlow()

    private val _quickBattle = MutableStateFlow(readBoolean(KEY_QUICK_BATTLE, false))
    val quickBattle: StateFlow<Boolean> = _quickBattle.asStateFlow()

    private val _gyroTiltEnabled = MutableStateFlow(readBoolean(KEY_GYRO_TILT_ENABLED, true))
    val gyroTiltEnabled: StateFlow<Boolean> = _gyroTiltEnabled.asStateFlow()

    fun setThemeMode(mode: ThemeMode) {
        _themeMode.value = mode
        storage.write(KEY_THEME_MODE, mode.name)
    }

    fun setSoundEnabled(enabled: Boolean) {
        _soundEnabled.value = enabled
        storage.write(KEY_SOUND_ENABLED, enabled.toString())
    }

    fun setHapticsEnabled(enabled: Boolean) {
        _hapticsEnabled.value = enabled
        storage.write(KEY_HAPTICS_ENABLED, enabled.toString())
    }

    fun setMusicEnabled(enabled: Boolean) {
        _musicEnabled.value = enabled
        storage.write(KEY_MUSIC_ENABLED, enabled.toString())
    }

    fun setShowEnemyHp(enabled: Boolean) {
        _showEnemyHp.value = enabled
        storage.write(KEY_SHOW_ENEMY_HP, enabled.toString())
    }

    fun setQuickBattle(enabled: Boolean) {
        _quickBattle.value = enabled
        storage.write(KEY_QUICK_BATTLE, enabled.toString())
    }

    fun setGyroTiltEnabled(enabled: Boolean) {
        _gyroTiltEnabled.value = enabled
        storage.write(KEY_GYRO_TILT_ENABLED, enabled.toString())
    }

    private fun readBoolean(key: String, default: Boolean): Boolean =
        storage.read(key)?.toBooleanStrictOrNull() ?: default

    private fun readThemeMode(): ThemeMode = storage.read(KEY_THEME_MODE)
        ?.let { stored -> ThemeMode.entries.firstOrNull { it.name == stored } }
        ?: ThemeMode.Light
}