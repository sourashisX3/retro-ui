package com.funapp.retroui.features.home.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
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
import com.funapp.retroui.core.design.components.controls.RetroChip
import com.funapp.retroui.core.design.components.controls.RetroChipVariant
import com.funapp.retroui.core.design.components.game.RetroCardRarity
import com.funapp.retroui.core.design.components.game.RetroCardSlot
import com.funapp.retroui.core.design.components.surfaces.RetroPanel
import com.funapp.retroui.core.design.theme.RetroColors
import com.funapp.retroui.core.design.theme.RetroTheme
import org.jetbrains.compose.resources.stringResource
import retroui.shared.generated.resources.Res
import retroui.shared.generated.resources.home_deck_builder
import retroui.shared.generated.resources.home_deck_subtitle
import retroui.shared.generated.resources.home_deck_title

internal data class DeckChampion(
    val icon: ImageVector,
    val rarity: RetroCardRarity,
)

internal val deckChampions = listOf(
    DeckChampion(Icons.Filled.Star, RetroCardRarity.Legendary),
    DeckChampion(Icons.Filled.Favorite, RetroCardRarity.Epic),
    DeckChampion(Icons.Filled.Person, RetroCardRarity.Rare),
    DeckChampion(Icons.Filled.Face, RetroCardRarity.Common),
    DeckChampion(Icons.Filled.AccountCircle, RetroCardRarity.Epic),
)

internal fun RetroCardRarity.tileColor(colors: RetroColors): Color = when (this) {
    RetroCardRarity.Common -> colors.surfaceMuted
    RetroCardRarity.Rare -> colors.info
    RetroCardRarity.Epic -> colors.accent
    RetroCardRarity.Legendary -> colors.secondary
}

/**
 * Deck snapshot: the five champion slots with a shortcut to the deck builder.
 */
@Composable
internal fun DeckSnapshot(onDeckBuilder: () -> Unit) {
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
            deckChampions.forEach { champion ->
                DeckChampionCell(champion = champion, onClick = onDeckBuilder)
            }
        }
    }
}

@Composable
private fun DeckChampionCell(
    champion: DeckChampion,
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
                .background(champion.rarity.tileColor(colors))
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