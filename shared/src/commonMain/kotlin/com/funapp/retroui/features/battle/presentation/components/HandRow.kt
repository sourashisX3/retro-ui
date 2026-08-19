package com.funapp.retroui.features.battle.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.funapp.retroui.core.design.components.game.RetroCardSlot
import com.funapp.retroui.core.design.components.game.rarityColor
import com.funapp.retroui.core.design.components.surfaces.RetroPanel
import com.funapp.retroui.core.design.theme.RetroTheme
import com.funapp.retroui.core.mock.MockChampion
import com.funapp.retroui.core.mock.mockChampionRoster
import org.jetbrains.compose.resources.stringResource
import retroui.shared.generated.resources.Res
import retroui.shared.generated.resources.battle_hand_title

/**
 * Player hand shown at the bottom of the arena: five selectable champion
 * slots.
 */
@Composable
internal fun HandRow() {
    val hand = mockChampionRoster().take(5)
    RetroPanel(
        title = stringResource(Res.string.battle_hand_title),
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            hand.forEach { card ->
                HandCardCell(card = card)
            }
        }
    }
}

@Composable
private fun HandCardCell(card: MockChampion) {
    val colors = RetroTheme.colors
    RetroCardSlot(size = 44.dp) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .clip(RetroTheme.shapeTokens.chip)
                .background(card.rarity.rarityColor(colors)),
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