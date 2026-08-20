package com.funapp.retroui.core.ui.components.controls

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CornerBasedShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.funapp.retroui.core.ui.components.foundation.retroTactilePress
import com.funapp.retroui.core.ui.theme.RetroTheme
import com.funapp.retroui.core.utils.rememberRetroTapFeedback

/**
 * Square-ish icon button with ink outline and hard shadow.
 */
@Composable
fun RetroIconButton(
    imageVector: ImageVector,
    contentDescription: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    size: Dp = 40.dp,
    containerColor: Color = RetroTheme.colors.surface,
    tint: Color = RetroTheme.colors.textPrimary,
    enabled: Boolean = true,
) {
    val shape: CornerBasedShape = RetroTheme.shapeTokens.input
    val tap = rememberRetroTapFeedback()
    val interactionSource = remember { MutableInteractionSource() }
    Box(
        modifier = modifier
            .size(size)
            .then(
                Modifier
                    .retroTactilePress(
                        interactionSource = interactionSource,
                        shape = shape,
                        shadowColor = RetroTheme.colors.shadow,
                        shadowX = 2.dp,
                        shadowY = 3.dp,
                    )
                    .clip(shape)
                    .background(containerColor)
                    .border(BorderStroke(RetroTheme.borders.default, RetroTheme.colors.outlineStrong), shape)
            )
            .clickable(
                enabled = enabled,
                interactionSource = interactionSource,
                indication = null,
            ) {
                tap.play()
                onClick()
            }
            .alpha(if (enabled) 1f else 0.5f),
        contentAlignment = Alignment.Center,
    ) {
        Icon(
            imageVector = imageVector,
            contentDescription = contentDescription,
            modifier = Modifier.size(size / 2.4f),
            tint = tint,
        )
    }
}

