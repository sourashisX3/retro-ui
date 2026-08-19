package com.funapp.retroui.core.design.components.navigation

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
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.shape.CornerBasedShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import com.funapp.retroui.core.design.components.foundation.RetroText
import com.funapp.retroui.core.design.theme.RetroTheme

/** A single bottom-navigation tab: label + pixel icon. */
data class RetroBottomBarItem(
    val label: String,
    val icon: ImageVector,
)

/** Retro arcade bottom navigation bar. */
@Composable
fun RetroBottomBar(
    items: List<RetroBottomBarItem>,
    selectedIndex: Int,
    onSelect: (Int) -> Unit,
    modifier: Modifier = Modifier,
) {
    val colors = RetroTheme.colors

    Row(
        modifier = modifier
            .fillMaxWidth()
            .background(colors.surface)
            .border(BorderStroke(RetroTheme.borders.default, colors.outlineStrong))
            .windowInsetsPadding(WindowInsets.navigationBars.only(WindowInsetsSides.Bottom))
            .height(RetroTheme.dimensions.bottomBarHeight),
        horizontalArrangement = Arrangement.SpaceEvenly,
    ) {
        items.forEachIndexed { index, item ->
            val selected = index == selectedIndex
            Column(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth()
                    .clickable(
                        interactionSource = remember { MutableInteractionSource() },
                        indication = null,
                    ) { onSelect(index) }
                    .padding(vertical = 6.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                RetroBottomBarIcon(item.icon, selected)
                Spacer(modifier = Modifier.height(2.dp))
                RetroText(
                    text = item.label,
                    style = RetroTheme.typography.caption,
                    color = if (selected) colors.primary else colors.textMuted,
                    maxLines = 1,
                )
            }
        }
    }
}

@Composable
private fun RetroBottomBarIcon(icon: ImageVector, selected: Boolean) {
    val colors = RetroTheme.colors
    val shape: CornerBasedShape = RetroTheme.shapeTokens.chip
    Box(
        modifier = Modifier
            .size(30.dp)
            .clip(shape)
            .background(if (selected) colors.primary else colors.surfaceMuted)
            .border(BorderStroke(RetroTheme.borders.default, colors.outlineStrong), shape),
        contentAlignment = Alignment.Center,
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = if (selected) colors.onPrimary else colors.textMuted,
            modifier = Modifier.size(RetroTheme.dimensions.iconSM),
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