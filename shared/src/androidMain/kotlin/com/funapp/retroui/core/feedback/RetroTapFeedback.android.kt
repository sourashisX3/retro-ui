package com.funapp.retroui.core.feedback

import android.content.Context
import android.media.AudioAttributes
import android.media.SoundPool
import android.view.HapticFeedbackConstants
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retroui.shared.generated.resources.Res
import java.io.File

@Composable
actual fun rememberRetroTapFeedback(): RetroTapFeedback {
    val context = LocalContext.current
    val view = LocalView.current
    val sound = remember { AndroidRetroTapSound(context) }
    DisposableEffect(Unit) {
        onDispose { sound.release() }
    }
    return remember {
        RetroTapFeedback {
            view.performHapticFeedback(HapticFeedbackConstants.KEYBOARD_TAP)
            sound.play()
        }
    }
}

private class AndroidRetroTapSound(context: Context) {
    private val soundPool = SoundPool.Builder()
        .setMaxStreams(3)
        .setAudioAttributes(
            AudioAttributes.Builder()
                .setUsage(AudioAttributes.USAGE_GAME)
                .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                .build(),
        )
        .build()

    private val file = File(context.cacheDir, "retro_tap.wav")
    private var soundId: Int? = null
    private var ready = false

    init {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                if (!file.exists()) {
                    file.writeBytes(Res.readBytes("files/tap.wav"))
                }
                val id = soundPool.load(file.absolutePath, 1)
                soundPool.setOnLoadCompleteListener { _, _, status ->
                    if (status == 0) {
                        soundId = id
                        ready = true
                    }
                }
            } catch (_: Throwable) {
                // Feedback is best-effort; never crash a tap.
            }
        }
    }

    fun play() {
        if (!ready) return
        soundId?.let { soundPool.play(it, 1f, 1f, 1, 0, 1f) }
    }

    fun release() {
        soundPool.release()
    }
}