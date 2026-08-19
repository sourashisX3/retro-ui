package com.funapp.retroui.core.design.components.hud

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.funapp.retroui.core.design.components.foundation.RetroText
import com.funapp.retroui.core.design.theme.RetroTheme

data class HudStat(
    val icon: ImageVector,
    val value: String,
    val tint: Color,
    val label: String? = null,
)

/**
 * Compact game-HUD statistics row: `ICON VALUE   ICON VALUE ...`
 *
 * Icons are prominent, numbers bold, spacing tight. This is a game overlay,
 * not a business dashboard.
 */
@Composable
fun StatHud(
    stats: List<HudStat>,
    modifier: Modifier = Modifier,
    spacing: Dp = RetroTheme.spacing.lg,
    iconSize: Dp = RetroTheme.dimensions.iconMD,
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(spacing),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        stats.forEach { stat ->
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    imageVector = stat.icon,
                    contentDescription = stat.label,
                    modifier = Modifier.size(iconSize),
                    tint = stat.tint,
                )
                RetroText(
                    text = stat.value,
                    style = RetroTheme.typography.title,
                    color = stat.tint,
                    modifier = Modifier.padding(start = RetroTheme.spacing.xs),
                )
            }
        }
    }
}