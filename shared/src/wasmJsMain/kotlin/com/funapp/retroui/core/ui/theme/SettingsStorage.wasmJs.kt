package com.funapp.retroui.core.ui.theme

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import kotlinx.browser.localStorage

@Composable
actual fun rememberPlatformSettingsStorage(): SettingsStorage {
    return remember {
        object : SettingsStorage {
            override fun read(key: String): String? = localStorage.getItem(key)

            override fun write(key: String, value: String) {
                localStorage.setItem(key, value)
            }

            override fun remove(key: String) {
                localStorage.removeItem(key)
            }
        }
    }
}

