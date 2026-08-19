package com.funapp.retroui.features.home.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.funapp.retroui.core.design.components.controls.RetroChip
import com.funapp.retroui.core.design.components.controls.RetroChipVariant
import com.funapp.retroui.core.design.components.game.RetroCardSlot
import com.funapp.retroui.core.design.components.game.rarityColor
import com.funapp.retroui.core.design.components.surfaces.RetroPanel
import com.funapp.retroui.core.design.theme.RetroTheme
import com.funapp.retroui.core.mock.MockChampion
import com.funapp.retroui.core.mock.mockChampionRoster
import org.jetbrains.compose.resources.stringResource
import retroui.shared.generated.resources.Res
import retroui.shared.generated.resources.home_deck_builder
import retroui.shared.generated.resources.home_deck_subtitle
import retroui.shared.generated.resources.home_deck_title

/**
 * Deck snapshot: the five champion slots with a shortcut to the deck builder.
 */
@Composable
internal fun DeckSnapshot(onDeckBuilder: () -> Unit) {
    val champions = mockChampionRoster().take(5)
    RetroPanel(
        title = stringResource(Res.string.home_deck_title),
        subtitle = stringResource(Res.string.home_deck_subtitle),
        trailing = {
            RetroChip(
                text = stringResource(Res.string.home_deck_builder),
                onClick = onDeckBuilder,
                variant = RetroChipVariant.Outline,
            )
        },
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            champions.forEach { champion ->
                DeckChampionCell(champion = champion, onClick = onDeckBuilder)
            }
        }
    }
}

@Composable
private fun DeckChampionCell(
    champion: MockChampion,
    onClick: () -> Unit,
) {
    val colors = RetroTheme.colors
    RetroCardSlot(
        size = 56.dp,
        onClick = onClick,
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .clip(RetroTheme.shapeTokens.chip)
                .background(champion.rarity.rarityColor(colors))
                .padding(RetroTheme.spacing.xs),
            contentAlignment = Alignment.Center,
        ) {
            Icon(
                imageVector = champion.icon,
                contentDescription = null,
                tint = colors.onPrimary,
                modifier = Modifier.size(RetroTheme.dimensions.iconMD),
            )
        }
    }
}