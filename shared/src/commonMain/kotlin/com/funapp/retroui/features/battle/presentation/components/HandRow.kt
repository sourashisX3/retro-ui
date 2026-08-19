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
import com.funapp.retroui.core.ui.animation.retroEntrance
import com.funapp.retroui.core.ui.components.game.RetroCardSlot
import com.funapp.retroui.core.ui.components.game.rarityColor
import com.funapp.retroui.core.ui.components.surfaces.RetroPanel
import com.funapp.retroui.core.ui.theme.RetroTheme
import com.funapp.retroui.core.data.mock.MockChampion
import com.funapp.retroui.core.data.mock.mockChampionRoster
import org.jetbrains.compose.resources.stringResource
import retroui.shared.generated.resources.Res
import retroui.shared.generated.resources.battle_hand_title

/**
 * Player hand shown at the bottom of the arena: five selectable champion
 * slots.
 */
@Composable
internal fun HandRow(modifier: Modifier = Modifier) {
    val hand = mockChampionRoster().take(5)
    RetroPanel(
        title = stringResource(Res.string.battle_hand_title),
        modifier = modifier,
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            hand.forEachIndexed { index, card ->
                HandCardCell(
                    card = card,
                    modifier = Modifier.retroEntrance(delayMillis = 40 * index),
                )
            }
        }
    }
}

@Composable
private fun HandCardCell(card: MockChampion, modifier: Modifier = Modifier) {
    val colors = RetroTheme.colors
    RetroCardSlot(
        size = 44.dp,
        modifier = modifier,
    ) {
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
