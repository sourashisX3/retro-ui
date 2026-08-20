package com.funapp.retroui.core.utils

import androidx.compose.runtime.Composable

private object NoopRetroTapFeedback : RetroTapFeedback {
    override fun play() = Unit
}

@Composable
actual fun rememberRetroTapFeedback(): RetroTapFeedback = NoopRetroTapFeedback
