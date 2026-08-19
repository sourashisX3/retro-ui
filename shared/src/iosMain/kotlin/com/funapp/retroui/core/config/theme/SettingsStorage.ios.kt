package com.funapp.retroui.core.config.theme

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import platform.Foundation.NSUserDefaults

@Composable
actual fun rememberPlatformSettingsStorage(): SettingsStorage {
    val defaults = NSUserDefaults.standardUserDefaults
    return remember(defaults) {
        object : SettingsStorage {
            override fun read(key: String): String? = defaults.stringForKey(key)

            override fun write(key: String, value: String) {
                defaults.setObject(value, forKey = key)
            }

            override fun remove(key: String) {
                defaults.removeObjectForKey(key)
            }
        }
    }
}