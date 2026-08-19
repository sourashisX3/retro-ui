package com.funapp.retroui.core.ui.components.navigation
import com.funapp.retroui.core.ui.icons.RetroIcons

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.WindowInsetsSides
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.only
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.shape.CornerBasedShape
import androidx.compose.foundation.shape.RoundedCornerShape
import com.funapp.retroui.core.ui.icons.Home
import com.funapp.retroui.core.ui.icons.Person
import com.funapp.retroui.core.ui.icons.PlayArrow
import com.funapp.retroui.core.ui.icons.Star
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import com.funapp.retroui.core.ui.components.foundation.RetroText
import com.funapp.retroui.core.ui.components.foundation.retroHardShadow
import com.funapp.retroui.core.ui.theme.RetroTheme
import com.funapp.retroui.core.ui.token.RetroAnimation
import com.funapp.retroui.core.utils.rememberRetroTapFeedback
import com.funapp.retroui.core.ui.animation.retroPopPress

/** A single bottom-navigation tab: label + pixel icon. */
data class RetroBottomBarItem(
    val label: String,
    val icon: ImageVector,
)

/**
 * Arcade dock bottom navigation.
 *
 * A floating rounded "control deck". The active tab is a capsule pill that
 * expands on a spring inside the dock, filled with the brand color, while
 * inactive tabs sit flat and muted.
 */
@Composable
fun RetroBottomBar(
    items: List<RetroBottomBarItem>,
    selectedIndex: Int,
    onSelect: (Int) -> Unit,
    modifier: Modifier = Modifier,
) {
    val colors = RetroTheme.colors
    val dockShape: CornerBasedShape = RoundedCornerShape(6.dp)

    Box(
        modifier = modifier
            .fillMaxWidth()
            .windowInsetsPadding(WindowInsets.navigationBars.only(WindowInsetsSides.Bottom))
            .padding(horizontal = RetroTheme.spacing.lg)
            .padding(bottom = RetroTheme.spacing.md)
            .retroHardShadow(
                offsetX = 3.dp,
                offsetY = 5.dp,
                color = colors.outline,
                shape = dockShape,
            ),
    ) {
        // Dock panel (clipped so the border/shape stay crisp; tabs draw above).
        Box(
            modifier = Modifier
                .matchParentSize()
                .clip(dockShape)
                .background(colors.surfaceRaised)
                .border(BorderStroke(RetroTheme.borders.default, colors.outlineStrong), dockShape),
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 6.dp, vertical = 10.dp),
            horizontalArrangement = Arrangement.SpaceEvenly,
        ) {
            items.forEachIndexed { index, item ->
                RetroDockTab(
                    item = item,
                    selected = index == selectedIndex,
                    onClick = { onSelect(index) },
                    modifier = Modifier.weight(1f),
                )
            }
        }
    }
}

@Composable
private fun RetroDockTab(
    item: RetroBottomBarItem,
    selected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val colors = RetroTheme.colors
    val pillShape: CornerBasedShape = RetroTheme.shapeTokens.buttonPill
    val tap = rememberRetroTapFeedback()
    val interactionSource = remember { MutableInteractionSource() }

    // Active capsule springs open and lifts slightly inside the dock.
    val active by animateFloatAsState(
        targetValue = if (selected) 1f else 0f,
        animationSpec = RetroAnimation.pop,
        label = "dockTabActive",
    )

    Column(
        modifier = modifier
            .clickable(
                interactionSource = interactionSource,
                indication = null,
            ) {
                tap.play()
                onClick()
            }
            .padding(vertical = 2.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Box(
            modifier = Modifier
                .graphicsLayer {
                    translationY = active * 3.dp.toPx()
                }
                .retroPopPress(interactionSource)
                .size(width = 36.dp + 14.dp * active, height = 34.dp)
                .clip(pillShape)
                .background(if (selected) colors.primary else colors.surfaceMuted)
                .border(BorderStroke(RetroTheme.borders.default, colors.outlineStrong), pillShape),
            contentAlignment = Alignment.Center,
        ) {
            Icon(
                imageVector = item.icon,
                contentDescription = null,
                tint = if (selected) colors.onPrimary else colors.textMuted,
                modifier = Modifier.size(RetroTheme.dimensions.iconSM),
            )
        }
        Spacer(modifier = Modifier.height(3.dp))
        RetroText(
            text = item.label,
            style = RetroTheme.typography.caption,
            color = if (selected) colors.primary else colors.textMuted,
            maxLines = 1,
        )
    }
}

/** Convenience: the four main Retro Duel tabs (matches app routes). */
object RetroBottomBarDestinations {
    val all: List<RetroBottomBarItem> = listOf(
        RetroBottomBarItem("HOME", RetroIcons.Home),
        RetroBottomBarItem("CARDS", RetroIcons.Star),
        RetroBottomBarItem("QUESTS", RetroIcons.PlayArrow),
        RetroBottomBarItem("PROFILE", RetroIcons.Person),
    )
}

