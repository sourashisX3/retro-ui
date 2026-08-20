package com.funapp.retroui.core.ui.components.feedback

import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.funapp.retroui.core.ui.components.foundation.RetroText
import com.funapp.retroui.core.ui.theme.RetroTheme
import com.funapp.retroui.core.ui.token.RetroAnimation
import org.jetbrains.compose.resources.stringResource
import retroui.shared.generated.resources.Res
import retroui.shared.generated.resources.common_loading

/**
 * Playful pixel-style loading indicator: a pixel block that bounces with the
 * shared [RetroAnimation.bounce] spec. Never a generic Material spinner.
 */
@Composable
fun RetroLoadingIndicator(
    modifier: Modifier = Modifier,
    size: Dp = 16.dp,
    label: String? = null,
    showLabel: Boolean = true,
) {
    val colors = RetroTheme.colors
    val transition = rememberInfiniteTransition(label = "retroLoading")
    val bounce by transition.animateFloat(
        initialValue = 0f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(
            animation = RetroAnimation.bounce,
            repeatMode = RepeatMode.Restart,
        ),
        label = "bounce",
    )
    val resolveLabel = label ?: stringResource(Res.string.common_loading)

    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Box(
            modifier = Modifier
                .offset(y = 6.dp * (1f - bounce))
                .size(size)
                .clip(RetroTheme.shapeTokens.badge)
                .background(colors.primary)
                .border(
                    RetroTheme.borders.thin,
                    colors.outlineStrong,
                    RetroTheme.shapeTokens.badge,
                ),
        )
        if (showLabel) {
            RetroText(
                text = resolveLabel,
                style = RetroTheme.typography.caption,
                color = colors.textSecondary,
                modifier = Modifier.padding(start = RetroTheme.spacing.sm),
            )
        }
    }
}
