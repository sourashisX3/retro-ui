package com.funapp.retroui.core.ui.components.feedback

import androidx.compose.animation.core.Animatable
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.dp
import com.funapp.retroui.core.ui.components.foundation.RetroText
import com.funapp.retroui.core.ui.components.foundation.retroHardShadow
import com.funapp.retroui.core.ui.components.foundation.retroTactilePress
import com.funapp.retroui.core.ui.icons.Check
import com.funapp.retroui.core.ui.icons.Close
import com.funapp.retroui.core.ui.icons.Info
import com.funapp.retroui.core.ui.icons.RetroIcons
import com.funapp.retroui.core.ui.icons.Warning
import com.funapp.retroui.core.ui.theme.RetroTheme
import com.funapp.retroui.core.ui.token.RetroAnimation
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.jetbrains.compose.resources.stringResource
import retroui.shared.generated.resources.Res
import retroui.shared.generated.resources.common_close

/**
 * Retro top toast — the arcade-flavoured equivalent of a Snackbar.
 *
 * One at a time: toasts queue up and each auto-dismisses after its
 * [RetroToastDuration]. Use [LocalRetroToastController] to fire one from any
 * screen:
 *
 * ```
 * LocalRetroToastController.current.show(
 *     stringResource(Res.string.toast_deck_saved),
 *     type = RetroToastType.Success,
 * )
 * ```
 *
 * Backend phase: message resolution already supports suspend getters, so the
 * same API works with fetched strings.
 */
@Stable
class RetroToastController(
    private val scope: CoroutineScope,
) {
    private val queue = ArrayDeque<RetroToastData>()
    private var dismissJob: Job? = null

    var current by mutableStateOf<RetroToastData?>(null)
        private set

    fun show(
        message: String,
        type: RetroToastType = RetroToastType.Info,
        duration: RetroToastDuration = RetroToastDuration.Short,
    ) {
        queue.addLast(RetroToastData(message = message, type = type, duration = duration))
        drain()
    }

    fun dismiss() {
        dismissJob?.cancel()
        current = null
        drain()
    }

    private fun drain() {
        if (current != null || queue.isEmpty()) return
        current = queue.removeFirst()
        val toast = current ?: return
        dismissJob = scope.launch {
            delay(toast.duration.millis)
            current = null
            drain()
        }
    }
}

/** The current toast (message + styling) shown by the [RetroToastHost]. */
@Stable
data class RetroToastData(
    val message: String,
    val type: RetroToastType,
    val duration: RetroToastDuration,
)

enum class RetroToastType {
    Success,
    Error,
    Warning,
    Info,
}

enum class RetroToastDuration(val millis: Long) {
    Short(2_000L),
    Medium(3_500L),
    Long(5_000L),
}

/**
 * Composition-local entry point for toasts. Provided in [AppNavHost]'s root;
 * screens call `LocalRetroToastController.current.show(...)`.
 */
val LocalRetroToastController = staticCompositionLocalOf<RetroToastController> {
    error("LocalRetroToastController not provided — AppNavHost must provide it.")
}

/**
 * Overlay host for the current toast. Place once at the root of the app,
 * on top of the nav graph, so toasts survive navigation.
 */
@Composable
fun RetroToastHost(
    controller: RetroToastController,
    modifier: Modifier = Modifier,
) {
    val toast = controller.current
    if (toast != null) {
        val colors = RetroTheme.colors
        val (icon, plateColor, onPlate) = when (toast.type) {
            RetroToastType.Success -> Triple(RetroIcons.Check, colors.success, colors.onSuccess)
            RetroToastType.Error -> Triple(RetroIcons.Warning, colors.error, colors.onError)
            RetroToastType.Warning -> Triple(RetroIcons.Warning, colors.warning, colors.onWarning)
            RetroToastType.Info -> Triple(RetroIcons.Info, colors.info, colors.onInfo)
        }

        val scale = androidx.compose.runtime.remember { Animatable(0.6f) }
        androidx.compose.runtime.LaunchedEffect(toast) {
            scale.animateTo(1f, animationSpec = RetroAnimation.liquid)
        }

        val dismissInteraction = remember { MutableInteractionSource() }
        val dismissLabel = stringResource(Res.string.common_close)

        Box(
            modifier = modifier
                .fillMaxWidth()
                .windowInsetsPadding(WindowInsets.safeDrawing)
                .padding(top = RetroTheme.spacing.md)
                .graphicsLayer {
                    scaleX = scale.value
                    scaleY = scale.value
                    translationY = (1f - scale.value) * -12.dp.toPx()
                },
            contentAlignment = Alignment.TopCenter,
        ) {
            Row(
                modifier = Modifier
                    .widthIn(max = 420.dp)
                    .fillMaxWidth()
                    .padding(horizontal = RetroTheme.spacing.lg)
                    .retroHardShadow(
                        offsetX = 3.dp,
                        offsetY = 3.dp,
                        color = colors.shadow,
                    )
                    .background(plateColor)
                    .border(BorderStroke(RetroTheme.borders.default, colors.outlineStrong))
                    .padding(
                        horizontal = RetroTheme.spacing.md,
                        vertical = RetroTheme.spacing.sm,
                    ),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                val tileShape = RetroTheme.shapeTokens.input
                Icon(
                    imageVector = icon,
                    contentDescription = null,
                    tint = onPlate,
                    modifier = Modifier.size(20.dp),
                )
                Spacer(modifier = Modifier.width(RetroTheme.spacing.sm))
                RetroText(
                    text = toast.message,
                    style = RetroTheme.typography.bodySmall,
                    color = onPlate,
                    modifier = Modifier.weight(1f),
                )
                Spacer(modifier = Modifier.width(RetroTheme.spacing.xs))
                Box(
                    modifier = Modifier
                        .size(26.dp)
                        .retroTactilePress(
                            interactionSource = dismissInteraction,
                            shape = tileShape,
                            shadowColor = colors.shadow,
                            shadowX = 2.dp,
                            shadowY = 2.dp,
                        )
                        .clip(tileShape)
                        .background(colors.surfaceRaised)
                        .border(BorderStroke(RetroTheme.borders.default, colors.outlineStrong), tileShape)
                        .clickable(
                            interactionSource = dismissInteraction,
                            indication = null,
                            onClick = controller::dismiss,
                        ),
                    contentAlignment = Alignment.Center,
                ) {
                    Icon(
                        imageVector = RetroIcons.Close,
                        contentDescription = dismissLabel,
                        tint = colors.textMuted,
                        modifier = Modifier.size(12.dp),
                    )
                }
            }
        }
    }
}