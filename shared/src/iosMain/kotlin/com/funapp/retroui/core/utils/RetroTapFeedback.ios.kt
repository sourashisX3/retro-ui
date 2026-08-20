package com.funapp.retroui.core.utils

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import kotlinx.cinterop.ExperimentalForeignApi
import kotlinx.cinterop.addressOf
import kotlinx.cinterop.usePinned
import platform.AVFAudio.AVAudioPlayer
import platform.Foundation.NSURL
import platform.Foundation.NSTemporaryDirectory
import platform.UIKit.UIImpactFeedbackGenerator
import platform.UIKit.UIImpactFeedbackStyle
import platform.posix.fclose
import platform.posix.fopen
import platform.posix.fwrite
import retroui.shared.generated.resources.Res

@OptIn(ExperimentalForeignApi::class)
@Composable
actual fun rememberRetroTapFeedback(): RetroTapFeedback {
    var player by remember { mutableStateOf<AVAudioPlayer?>(null) }
    val generator = remember {
        UIImpactFeedbackGenerator(UIImpactFeedbackStyle.UIImpactFeedbackStyleLight)
    }

    LaunchedEffect(Unit) {
        try {
            val bytes = Res.readBytes("files/tap.wav")
            val path = NSTemporaryDirectory() + "retro_tap.wav"
            val file = fopen(path, "wb")
            if (file != null) {
                bytes.usePinned { pinned ->
                    fwrite(pinned.addressOf(0), 1u, bytes.size.toULong(), file)
                }
                fclose(file)
            }
            val audioPlayer = AVAudioPlayer(
                contentsOfURL = NSURL.fileURLWithPath(path),
                error = null,
            )
            audioPlayer.prepareToPlay()
            player = audioPlayer
        } catch (_: Throwable) {
            // Feedback is best-effort; never crash a tap.
        }
    }

    return remember {
        RetroTapFeedback {
            generator.impactOccurred()
            player?.let { audioPlayer ->
                audioPlayer.currentTime = 0.0
                audioPlayer.play()
            }
        }
    }
}
