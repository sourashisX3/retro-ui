package com.funapp.retroui.core.ui.theme

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import com.funapp.retroui.core.ui.theme.SettingsStorage
import androidx.core.content.edit

@Composable
actual fun rememberPlatformSettingsStorage(): SettingsStorage {
    val context = LocalContext.current.applicationContext
    return remember(context) {
        val prefs = context.getSharedPreferences("retro_settings", Context.MODE_PRIVATE)
        object : SettingsStorage {
            override fun read(key: String): String? = prefs.getString(key, null)

            override fun write(key: String, value: String) {
                prefs.edit { putString(key, value) }
            }

            override fun remove(key: String) {
                prefs.edit { remove(key) }
            }
        }
    }
}

