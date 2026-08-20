package com.funapp.retroui.core.ui.theme

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import java.util.prefs.Preferences

@Composable
actual fun rememberPlatformSettingsStorage(): SettingsStorage {
    val prefs = Preferences.userRoot().node("com/funapp/retroui")
    return remember(prefs) {
        object : SettingsStorage {
            override fun read(key: String): String? = prefs.get(key, null)

            override fun write(key: String, value: String) {
                prefs.put(key, value)
            }

            override fun remove(key: String) {
                prefs.remove(key)
            }
        }
    }
}

