package com.funapp.retroui.core.ui.components.branding

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.funapp.retroui.core.ui.components.foundation.retroHardShadow
import com.funapp.retroui.core.ui.theme.RetroTheme

/**
 * App icon concept: a cream/accent tile with thick ink outline, hard shadow
 * and the pixel "RD" monogram. Matches the RETRO DUEL visual language so it
 * can be exported as the launcher icon later.
 */
@Composable
fun RetroAppIcon(
    modifier: Modifier = Modifier,
    size: Dp = 64.dp,
    backgroundColor: Color = RetroTheme.colors.accent,
    monogramColor: Color = RetroTheme.colors.onAccent,
) {
    val shape = RoundedCornerShape(RetroTheme.shapes.lg)
    androidx.compose.foundation.layout.Box(
        modifier = modifier
            .size(size)
            .retroHardShadow(
                offsetX = 4.dp,
                offsetY = 5.dp,
                color = RetroTheme.colors.outline,
                shape = shape,
            )
            .clip(shape)
            .background(backgroundColor)
            .border(RetroTheme.borders.hero, RetroTheme.colors.outlineStrong, shape),
        contentAlignment = androidx.compose.ui.Alignment.Center,
    ) {
        RetroOutlineText(
            text = "RD",
            style = RetroTheme.typography.headline,
            fill = monogramColor,
            outline = RetroTheme.colors.outlineStrong,
            shadow = RetroTheme.colors.outline,
            outlineWidth = 3.dp,
        )
    }
}
