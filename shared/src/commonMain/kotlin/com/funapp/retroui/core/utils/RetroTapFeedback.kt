package com.funapp.retroui.core.utils

import androidx.compose.runtime.Composable

/**
 * Haptic + audible tap feedback.
 *
 * Every interactive control fires [play] right before its click callback so
 * taps respond like an arcade button: a light haptic tick plus a retro blip
 * (Kenney CC0 UI click, bundled as a Compose resource). Real implementations
 * on Android/iOS; no-op on desktop/web targets.
 */
fun interface RetroTapFeedback {
    fun play()
}

@Composable
expect fun rememberRetroTapFeedback(): RetroTapFeedback
