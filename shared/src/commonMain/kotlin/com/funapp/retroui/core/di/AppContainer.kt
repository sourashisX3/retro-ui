package com.funapp.retroui.core.di

import com.funapp.retroui.core.ui.theme.SettingsRepository

/**
 * Manual dependency container — no Hilt (Hilt is Android-only and would
 * break the KMP shared module). Created by the root [com.funapp.retroui.App]
 * and handed out via [LocalAppContainer].
 *
 * Grows as features are implemented: repositories, local/remote data
 * sources, mappers and use cases are registered here.
 */
class AppContainer(
    val settingsRepository: SettingsRepository,
) {

    val json = kotlinx.serialization.json.Json {
        ignoreUnknownKeys = true
        encodeDefaults = true
    }
}
