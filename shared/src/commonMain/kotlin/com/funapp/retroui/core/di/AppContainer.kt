package com.funapp.retroui.core.di

/**
 * Manual dependency container — no Hilt (Hilt is Android-only and would
 * break the KMP shared module). Owned by the root [com.funapp.retroui.App]
 * and passed down to features as phases land.
 *
 * Grows as features are implemented: repositories, local/remote data
 * sources, mappers and use cases are registered here.
 */
class AppContainer {

    val json = kotlinx.serialization.json.Json {
        ignoreUnknownKeys = true
        encodeDefaults = true
    }
}