package com.funapp.retroui.features.battle.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import com.funapp.retroui.core.design.components.game.RetroCardRarity
import com.funapp.retroui.core.design.components.game.RetroCardSlot
import com.funapp.retroui.core.design.components.surfaces.RetroPanel
import com.funapp.retroui.core.design.theme.RetroColors
import com.funapp.retroui.core.design.theme.RetroTheme
import org.jetbrains.compose.resources.stringResource
import retroui.shared.generated.resources.Res
import retroui.shared.generated.resources.battle_hand_title

internal data class HandCard(
    val icon: ImageVector,
    val rarity: RetroCardRarity,
)

internal val battleHand = listOf(
    HandCard(Icons.Filled.Star, RetroCardRarity.Legendary),
    HandCard(Icons.Filled.Favorite, RetroCardRarity.Epic),
    HandCard(Icons.Filled.Person, RetroCardRarity.Rare),
    HandCard(Icons.Filled.Face, RetroCardRarity.Common),
    HandCard(Icons.Filled.AccountCircle, RetroCardRarity.Epic),
)

internal fun RetroCardRarity.tileColor(colors: RetroColors): Color = when (this) {
    RetroCardRarity.Common -> colors.surfaceMuted
    RetroCardRarity.Rare -> colors.info
    RetroCardRarity.Epic -> colors.accent
    RetroCardRarity.Legendary -> colors.secondary
}

/**
 * Player hand shown at the bottom of the arena: five selectable champion
 * slots.
 */
@Composable
internal fun HandRow() {
    RetroPanel(
        title = stringResource(Res.string.battle_hand_title),
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            battleHand.forEach { card ->
                HandCardCell(card = card)
            }
        }
    }
}

@Composable
private fun HandCardCell(card: HandCard) {
    val colors = RetroTheme.colors
    RetroCardSlot(size = 44.dp) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .clip(RetroTheme.shapeTokens.chip)
                .background(card.rarity.tileColor(colors)),
            contentAlignment = Alignment.Center,
        ) {
            Icon(
                imageVector = card.icon,
                contentDescription = null,
                tint = colors.onPrimary,
                modifier = Modifier.size(RetroTheme.dimensions.iconMD),
            )
        }
    }
}