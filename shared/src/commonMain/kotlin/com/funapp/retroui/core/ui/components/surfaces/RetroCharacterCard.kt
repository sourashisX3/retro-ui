package com.funapp.retroui.core.ui.components.surfaces

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import com.funapp.retroui.core.ui.components.feedback.RetroProgressBar
import com.funapp.retroui.core.ui.components.feedback.RetroProgressColor
import com.funapp.retroui.core.ui.components.foundation.RetroText
import com.funapp.retroui.core.ui.components.game.RetroAvatar
import com.funapp.retroui.core.ui.theme.RetroTheme

/**
 * Fighter HUD card used in the battle screen: avatar + name + level chip +
 * HP bar + optional shield bar. Reused for both player and opponent.
 */
@Composable
fun RetroCharacterCard(
    name: String,
    level: String,
    modifier: Modifier = Modifier,
    avatarIcon: ImageVector? = null,
    avatarColor: Color = RetroTheme.colors.surfaceVariant,
    hp: Float = 1f,
    shield: Float = 0f,
    hpText: String? = null,
) {
    val colors = RetroTheme.colors
    RetroCard(modifier = modifier, contentPadding = RetroTheme.spacing.md) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            RetroAvatar(
                icon = avatarIcon,
                size = RetroTheme.dimensions.avatarMD,
                backgroundColor = avatarColor,
            )
            Spacer(modifier = Modifier.width(RetroTheme.spacing.md))
            Column(modifier = Modifier.weight(1f)) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    RetroText(
                        text = name,
                        style = RetroTheme.typography.title,
                        color = colors.textPrimary,
                        modifier = Modifier.weight(1f),
                    )
                    RetroText(
                        text = "LV $level",
                        style = RetroTheme.typography.caption,
                        color = colors.onAccent,
                        modifier = Modifier
                            .padding(start = RetroTheme.spacing.sm)
                            .clip(RetroTheme.shapeTokens.badge)
                            .background(colors.accent)
                            .padding(horizontal = RetroTheme.spacing.xs, vertical = RetroTheme.spacing.xxs),
                    )
                }
                Spacer(modifier = Modifier.height(RetroTheme.spacing.sm))
                RetroProgressBar(
                    progress = hp,
                    color = RetroProgressColor.Health,
                    valueText = hpText,
                )
                if (shield > 0f) {
                    Spacer(modifier = Modifier.height(RetroTheme.spacing.xs))
                    RetroProgressBar(
                        progress = shield,
                        color = RetroProgressColor.Info,
                        height = RetroTheme.dimensions.progressBarThin,
                    )
                }
            }
        }
    }
}
