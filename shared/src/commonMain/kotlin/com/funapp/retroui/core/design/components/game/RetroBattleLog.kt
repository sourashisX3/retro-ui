package com.funapp.retroui.core.design.components.game

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.funapp.retroui.core.design.components.foundation.RetroText
import com.funapp.retroui.core.design.components.surfaces.RetroCard
import com.funapp.retroui.core.design.theme.RetroTheme

/** Battle log entry severity — controls the accent color and icon tint. */
enum class RetroBattleLogType { Damage, Heal, Shield, System }

/** One row of the battle log. */
@Immutable
data class RetroBattleLogEntry(
    val text: String,
    val type: RetroBattleLogType = RetroBattleLogType.System,
    val icon: ImageVector? = null,
)

private fun RetroBattleLogType.accentColor(colors: com.funapp.retroui.core.design.theme.RetroColors): Color =
    when (this) {
        RetroBattleLogType.Damage -> colors.error
        RetroBattleLogType.Heal -> colors.success
        RetroBattleLogType.Shield -> colors.info
        RetroBattleLogType.System -> colors.textMuted
    }

/**
 * Scrollable battle log panel. Each entry shows an icon marker plus text,
 * tinted by type (damage = red, heal = green, shield = blue, system = muted).
 */
@Composable
fun RetroBattleLog(
    entries: List<RetroBattleLogEntry>,
    modifier: Modifier = Modifier,
    height: Dp = 140.dp,
) {
    val colors = RetroTheme.colors
    RetroCard(
        modifier = modifier.height(height),
        contentPadding = RetroTheme.spacing.md,
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(RetroTheme.spacing.xs),
        ) {
            entries.forEach { entry ->
                Row(verticalAlignment = Alignment.CenterVertically) {
                    if (entry.icon != null) {
                        Icon(
                            imageVector = entry.icon,
                            contentDescription = null,
                            tint = entry.type.accentColor(colors),
                            modifier = Modifier
                                .padding(end = RetroTheme.spacing.sm)
                                .size(RetroTheme.dimensions.iconXS),
                        )
                    }
                    RetroText(
                        text = entry.text,
                        style = RetroTheme.typography.bodySmall,
                        color = colors.textSecondary,
                    )
                }
            }
        }
    }
}
