package com.funapp.retroui.features.deck.presentation.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.funapp.retroui.core.design.components.controls.RetroButton
import com.funapp.retroui.core.design.components.controls.RetroButtonVariant
import com.funapp.retroui.core.design.components.controls.RetroChip
import com.funapp.retroui.core.design.components.controls.RetroIconButton
import com.funapp.retroui.core.design.components.foundation.RetroText
import com.funapp.retroui.core.design.components.game.RetroCardRarity
import com.funapp.retroui.core.design.components.game.RetroCardSlot
import com.funapp.retroui.core.design.components.game.rarityColor
import com.funapp.retroui.core.design.components.surfaces.RetroPanel
import com.funapp.retroui.core.design.components.surfaces.RetroScreen
import com.funapp.retroui.core.design.theme.RetroTheme
import com.funapp.retroui.core.mock.MockChampion
import com.funapp.retroui.core.mock.mockChampionRoster
import org.jetbrains.compose.resources.stringResource
import retroui.shared.generated.resources.Res
import retroui.shared.generated.resources.btn_browse_collection
import retroui.shared.generated.resources.btn_save_deck
import retroui.shared.generated.resources.deck_add
import retroui.shared.generated.resources.deck_available_title
import retroui.shared.generated.resources.deck_empty_hint
import retroui.shared.generated.resources.deck_slots_ready
import retroui.shared.generated.resources.common_back
import retroui.shared.generated.resources.screen_deck_subtitle
import retroui.shared.generated.resources.screen_deck_title

private const val DECK_SIZE = 5

/**
 * Deck builder. Assemble a 5-champion deck from the available pool.
 * Static mock data for now; wired to the repository in the data phase.
 */
@Composable
fun DeckBuilderScreen(
    onGoCollection: () -> Unit,
    onGoHome: () -> Unit,
    onBack: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val pool = mockChampionRoster()
    val deck = remember { mutableStateListOf<MockChampion>() }

    RetroScreen(modifier = modifier) {
        item {
            Row(verticalAlignment = Alignment.CenterVertically) {
                RetroIconButton(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = stringResource(Res.string.common_back),
                    onClick = onBack,
                    containerColor = RetroTheme.colors.surfaceVariant,
                )
                Spacer(modifier = Modifier.width(RetroTheme.spacing.sm))
                Column(modifier = Modifier.weight(1f)) {
                    RetroText(
                        text = stringResource(Res.string.screen_deck_title),
                        style = RetroTheme.typography.heading,
                        color = RetroTheme.colors.textPrimary,
                    )
                    RetroText(
                        text = stringResource(Res.string.screen_deck_subtitle),
                        style = RetroTheme.typography.caption,
                        color = RetroTheme.colors.textMuted,
                    )
                }
            }
        }
        item {
            Spacer(modifier = Modifier.height(RetroTheme.spacing.lg))
        }
        item {
            DeckPanel(
                deck = deck,
                onRemoveAt = { deck.removeAt(it) },
            )
        }
        item {
            Spacer(modifier = Modifier.height(RetroTheme.spacing.md))
        }
        item {
            RetroText(
                text = stringResource(Res.string.deck_slots_ready, deck.size, DECK_SIZE),
                style = RetroTheme.typography.label,
                color = if (deck.size == DECK_SIZE) {
                    RetroTheme.colors.success
                } else {
                    RetroTheme.colors.textSecondary
                },
            )
            Spacer(modifier = Modifier.height(RetroTheme.spacing.sm))
        }
        item {
            RetroButton(
                text = stringResource(Res.string.btn_save_deck),
                leadingIcon = Icons.Filled.Check,
                onClick = onGoHome,
                enabled = deck.size == DECK_SIZE,
                small = true,
                modifier = Modifier.fillMaxWidth(),
            )
            Spacer(modifier = Modifier.height(RetroTheme.spacing.md))
            RetroButton(
                text = stringResource(Res.string.btn_browse_collection),
                variant = RetroButtonVariant.Outline,
                onClick = onGoCollection,
                small = true,
                modifier = Modifier.fillMaxWidth(),
            )
        }
        item {
            Spacer(modifier = Modifier.height(RetroTheme.spacing.lg))
        }
        item {
            RetroText(
                text = stringResource(Res.string.deck_available_title),
                style = RetroTheme.typography.title,
                color = RetroTheme.colors.textPrimary,
            )
            Spacer(modifier = Modifier.height(RetroTheme.spacing.sm))
        }
        item {
            if (deck.isEmpty()) {
                RetroText(
                    text = stringResource(Res.string.deck_empty_hint),
                    style = RetroTheme.typography.caption,
                    color = RetroTheme.colors.textMuted,
                )
                Spacer(modifier = Modifier.height(RetroTheme.spacing.sm))
            }
        }
        items(
            items = pool.filter { card -> deck.none { it.name == card.name } },
            key = { it.name },
        ) { card ->
            AvailableChampionRow(
                card = card,
                addEnabled = deck.size < DECK_SIZE,
                onAdd = { deck.add(card) },
            )
        }
    }
}

@Composable
private fun DeckPanel(
    deck: List<MockChampion>,
    onRemoveAt: (Int) -> Unit,
) {
    RetroPanel(
        title = stringResource(Res.string.screen_deck_title),
        subtitle = stringResource(Res.string.deck_slots_ready, deck.size, DECK_SIZE),
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            repeat(DECK_SIZE) { index ->
                val card = deck.getOrNull(index)
                RetroCardSlot(
                    size = 56.dp,
                    empty = card == null,
                    onClick = if (card != null) {
                        { onRemoveAt(index) }
                    } else null,
                ) {
                    if (card != null) {
                        ChampionTile(card = card)
                    }
                }
            }
        }
    }
}

@Composable
private fun AvailableChampionRow(
    card: MockChampion,
    addEnabled: Boolean,
    onAdd: () -> Unit,
) {
    val colors = RetroTheme.colors
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = RetroTheme.spacing.xs),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        ChampionTile(card = card)
        Spacer(modifier = Modifier.width(RetroTheme.spacing.sm))
        Column(modifier = Modifier.weight(1f)) {
            RetroText(
                text = card.name,
                style = RetroTheme.typography.label,
                color = colors.textPrimary,
            )
            RetroText(
                text = "${card.type} · ${card.cost}",
                style = RetroTheme.typography.caption,
                color = colors.textMuted,
            )
        }
        RetroChip(
            text = stringResource(Res.string.deck_add),
            onClick = onAdd,
            enabled = addEnabled,
            selected = true,
        )
    }
}

@Composable
private fun ChampionTile(card: MockChampion) {
    val colors = RetroTheme.colors
    Box(
        modifier = Modifier
            .size(40.dp)
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
