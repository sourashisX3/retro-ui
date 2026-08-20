package com.funapp.retroui.core.ui.components.game

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerBasedShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.funapp.retroui.core.ui.components.foundation.retroHardShadow
import com.funapp.retroui.core.ui.theme.RetroTheme

/**
 * Player / character avatar.
 *
 * A compact squared (or circular) tile with an ink outline and hard shadow.
 * [icon] is rendered inside (tinted [RetroTheme.colors.textPrimary]); pass
 * null for an empty slot. Set [borderColor] to a feedback color to build a
 * status/rarity ring.
 */
@Composable
fun RetroAvatar(
    modifier: Modifier = Modifier,
    size: Dp = RetroTheme.dimensions.avatarMD,
    icon: ImageVector? = null,
    backgroundColor: Color = RetroTheme.colors.surfaceVariant,
    borderColor: Color = RetroTheme.colors.outlineStrong,
    circle: Boolean = false,
) {
    val shape: CornerBasedShape = if (circle) CircleShape else RetroTheme.shapeTokens.chip
    Box(
        modifier = modifier
            .size(size)
            .retroHardShadow(
                offsetX = 2.dp,
                offsetY = 2.dp,
                color = RetroTheme.colors.shadow,
                shape = shape,
            )
            .clip(shape)
            .background(backgroundColor)
            .border(RetroTheme.borders.default, borderColor, shape),
        contentAlignment = Alignment.Center,
    ) {
        if (icon != null) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = RetroTheme.colors.textPrimary,
                modifier = Modifier.size(size * 0.55f),
            )
        }
    }
}
