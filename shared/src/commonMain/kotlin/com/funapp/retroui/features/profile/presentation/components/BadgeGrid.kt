package com.funapp.retroui.features.profile.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.funapp.retroui.core.ui.components.feedback.RetroShine
import com.funapp.retroui.core.ui.components.foundation.RetroText
import com.funapp.retroui.core.ui.components.foundation.retroHardShadow
import com.funapp.retroui.core.ui.icons.Lock
import com.funapp.retroui.core.ui.icons.RetroIcons
import com.funapp.retroui.core.ui.theme.RetroTheme
import com.funapp.retroui.core.utils.asString
import com.funapp.retroui.features.profile.data.Badge
import com.funapp.retroui.features.profile.data.getMockBadges
import org.jetbrains.compose.resources.stringResource
import retroui.shared.generated.resources.Res
import retroui.shared.generated.resources.badge_locked

/**
 * Grid of badges — circular medal coins matching the avatar style: ink
 * outline, hard offset shadow and an accent plate. Earned badges show their
 * icon and label; locked badges are greyed out with a lock icon and "???".
 */
@Composable
internal fun BadgeGrid() {
    val badges = getMockBadges()
    Column {
        badges.chunked(3).forEachIndexed { index, rowBadges ->
            if (index > 0) {
                Spacer(modifier = Modifier.height(RetroTheme.spacing.md))
            }
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(RetroTheme.spacing.sm),
            ) {
                rowBadges.forEach { badge ->
                    BadgeCell(badge = badge, modifier = Modifier.weight(1f))
                }
                repeat(3 - rowBadges.size) {
                    Spacer(modifier = Modifier.weight(1f))
                }
            }
        }
    }
}

@Composable
private fun BadgeCell(
    badge: Badge,
    modifier: Modifier = Modifier,
) {
    val colors = RetroTheme.colors
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Box(modifier = Modifier.size(48.dp)) {
            Box(
                modifier = Modifier
                    .matchParentSize()
                    .retroHardShadow(
                        offsetX = 2.dp,
                        offsetY = 3.dp,
                        color = colors.outline,
                        shape = CircleShape,
                    )
                    .clip(CircleShape)
                    .background(if (badge.earned) colors.accentContainer else colors.surfaceMuted)
                    .border(RetroTheme.borders.default, colors.outlineStrong, CircleShape),
                contentAlignment = Alignment.Center,
            ) {
                Icon(
                    imageVector = if (badge.earned) badge.icon else RetroIcons.Lock,
                    contentDescription = null,
                    tint = if (badge.earned) colors.onAccentContainer else colors.textMuted,
                    modifier = Modifier.size(26.dp),
                )
            }
            if (badge.earned) {
                RetroShine(shape = CircleShape)
            }
        }
        Spacer(modifier = Modifier.height(RetroTheme.spacing.xxs))
        RetroText(
            text = if (badge.earned) {
                badge.label.asString()
            } else {
                stringResource(Res.string.badge_locked)
            },
            style = RetroTheme.typography.caption,
            color = if (badge.earned) colors.textPrimary else colors.textMuted,
        )
    }
}