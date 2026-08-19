package com.funapp.retroui.core.design.components.navigation

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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import com.funapp.retroui.core.design.components.foundation.RetroText
import com.funapp.retroui.core.design.components.foundation.retroHardShadow
import com.funapp.retroui.core.design.theme.RetroTheme
import com.funapp.retroui.core.design.token.RetroAnimation

/** A single bottom-navigation tab: label + pixel icon. */
data class RetroBottomBarItem(
    val label: String,
    val icon: ImageVector,
)

/**
 * Arcade dock bottom navigation.
 *
 * A floating rounded "control deck" with a recessed cartridge slot and
 * corner pixel accents. The active tab is a cartridge that pops up out of
 * the dock on a spring, is filled with the brand color, and drops a diamond
 * cursor beneath its label. Inactive tabs sit flat and muted.
 */
@Composable
fun RetroBottomBar(
    items: List<RetroBottomBarItem>,
    selectedIndex: Int,
    onSelect: (Int) -> Unit,
    modifier: Modifier = Modifier,
) {
    val colors = RetroTheme.colors
    val dockShape: CornerBasedShape = RoundedCornerShape(14.dp)

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
                .background(colors.surface)
                .border(BorderStroke(RetroTheme.borders.default, colors.outlineStrong), dockShape),
        ) {
            // Recessed cartridge slot at the top-center of the deck.
            Box(
                modifier = Modifier
                    .align(Alignment.TopCenter)
                    .padding(top = 3.dp)
                    .size(width = 44.dp, height = 6.dp)
                    .clip(RoundedCornerShape(2.dp))
                    .background(colors.surfaceVariant)
                    .border(BorderStroke(RetroTheme.borders.thin, colors.outline), RoundedCornerShape(2.dp)),
            )
            // Corner pixel accents.
            CornerPixel(
                modifier = Modifier
                    .align(Alignment.TopStart)
                    .padding(start = 12.dp, top = 8.dp),
            )
            CornerPixel(
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .padding(end = 12.dp, top = 8.dp),
            )
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 6.dp, vertical = 6.dp),
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
private fun CornerPixel(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .size(5.dp)
            .background(RetroTheme.colors.primary),
    )
}

@Composable
private fun RetroDockTab(
    item: RetroBottomBarItem,
    selected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val colors = RetroTheme.colors
    val tileShape: CornerBasedShape = RetroTheme.shapeTokens.chip

    // Active tab pops up out of the dock on the arcade spring.
    val lift by animateFloatAsState(
        targetValue = if (selected) -1f else 0f,
        animationSpec = RetroAnimation.pop,
        label = "dockTabLift",
    )
    val scale by animateFloatAsState(
        targetValue = if (selected) 1f else 0.8f,
        animationSpec = RetroAnimation.pop,
        label = "dockTabScale",
    )

    Column(
        modifier = modifier
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = null,
            ) { onClick() }
            .padding(vertical = 2.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Box(
            modifier = Modifier
                .graphicsLayer {
                    translationY = lift * 7.dp.toPx()
                    scaleX = scale
                    scaleY = scale
                }
                .size(34.dp)
                .clip(tileShape)
                .background(if (selected) colors.primary else colors.surfaceMuted)
                .border(BorderStroke(RetroTheme.borders.default, colors.outlineStrong), tileShape),
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
        // Diamond cursor under the active tab.
        Box(
            modifier = Modifier
                .padding(top = 2.dp)
                .size(6.dp)
                .graphicsLayer { rotationZ = 45f }
                .background(if (selected) colors.primary else Color.Transparent),
        )
    }
}

/** Convenience: the four main Retro Duel tabs (matches app routes). */
object RetroBottomBarDestinations {
    val all: List<RetroBottomBarItem> = listOf(
        RetroBottomBarItem("HOME", Icons.Filled.Home),
        RetroBottomBarItem("CARDS", Icons.Filled.Star),
        RetroBottomBarItem("QUESTS", Icons.Filled.PlayArrow),
        RetroBottomBarItem("PROFILE", Icons.Filled.Person),
    )
}