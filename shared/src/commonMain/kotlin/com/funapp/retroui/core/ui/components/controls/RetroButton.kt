package com.funapp.retroui.core.ui.components.controls

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CornerBasedShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.funapp.retroui.core.ui.components.foundation.RetroText
import com.funapp.retroui.core.ui.components.foundation.retroTactilePress
import com.funapp.retroui.core.ui.theme.RetroTheme
import com.funapp.retroui.core.utils.rememberRetroTapFeedback

enum class RetroButtonVariant { Primary, Secondary, Accent, Danger, Outline }

/**
 * Tactile arcade button.
 *
 * Dark ink outline + bright flat fill + HARD OFFSET shadow. On press the
 * shadow collapses and the button sinks ~2dp into the page (120ms), so it
 * feels like physically pressing a game control.
 *
 * Never create `GreenButton`/`PurpleButton` — use variants.
 */
@Composable
fun RetroButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    variant: RetroButtonVariant = RetroButtonVariant.Primary,
    enabled: Boolean = true,
    loading: Boolean = false,
    leadingIcon: ImageVector? = null,
    small: Boolean = false,
) {
    val colors = RetroTheme.colors
    val dimensions = RetroTheme.dimensions
    val shape: CornerBasedShape = RetroTheme.shapeTokens.button
    val interactionSource = remember { MutableInteractionSource() }
    val tap = rememberRetroTapFeedback()

    val disabled = !enabled || loading

    val fillColor = when (variant) {
        RetroButtonVariant.Primary -> colors.primary
        RetroButtonVariant.Secondary -> colors.secondary
        RetroButtonVariant.Accent -> colors.accent
        RetroButtonVariant.Danger -> colors.error
        RetroButtonVariant.Outline -> colors.surfaceVariant
    }
    val contentColor = when (variant) {
        RetroButtonVariant.Primary -> colors.onPrimary
        RetroButtonVariant.Secondary -> colors.onSecondary
        RetroButtonVariant.Accent -> colors.onAccent
        RetroButtonVariant.Danger -> colors.onError
        RetroButtonVariant.Outline -> colors.textPrimary
    }
    val borderColor = colors.outlineStrong

    val effectiveFill = if (disabled) colors.surfaceMuted else fillColor
    val effectiveContent = if (disabled) colors.textMuted else contentColor

    val borderThickness: Dp = RetroTheme.borders.default

    Box(
        modifier = modifier
            .heightIn(min = if (small) dimensions.buttonHeightSmall else dimensions.buttonHeight)
            .then(
                Modifier
                    .retroTactilePress(
                        interactionSource = interactionSource,
                        shape = shape,
                        shadowColor = colors.outline,
                        shadowX = 3.dp,
                        shadowY = 4.dp,
                    )
                    .clip(shape)
                    .background(effectiveFill)
                    .border(BorderStroke(borderThickness, borderColor), shape)
            )
            .clickable(
                enabled = enabled && !loading,
                interactionSource = interactionSource,
                indication = null,
            ) {
                tap.play()
                onClick()
            }
            .alpha(if (disabled) 0.6f else 1f)
            .padding(horizontal = if (small) RetroTheme.spacing.md else RetroTheme.spacing.xl),
        contentAlignment = Alignment.Center,
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(RetroTheme.spacing.sm),
        ) {
            if (loading) {
                CircularProgressIndicator(
                    modifier = Modifier.size(if (small) 14.dp else 16.dp),
                    color = effectiveContent,
                    strokeWidth = 2.dp,
                )
            } else if (leadingIcon != null) {
                androidx.compose.material3.Icon(
                    imageVector = leadingIcon,
                    contentDescription = null,
                    modifier = Modifier.size(if (small) 14.dp else 16.dp),
                    tint = effectiveContent,
                )
            }
            RetroText(
                text = text,
                style = RetroTheme.typography.button,
                color = effectiveContent,
            )
        }
    }
}

