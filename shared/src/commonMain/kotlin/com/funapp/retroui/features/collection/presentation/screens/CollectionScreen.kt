package com.funapp.retroui.features.collection.presentation.screens
import com.funapp.retroui.core.ui.icons.RetroIcons

import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.foundation.rememberScrollState
import com.funapp.retroui.core.ui.icons.Edit
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.funapp.retroui.core.ui.animation.RetroEntranceStyle
import com.funapp.retroui.core.ui.animation.retroCascade
import com.funapp.retroui.core.ui.animation.retroEntrance
import com.funapp.retroui.core.ui.components.controls.RetroButton
import com.funapp.retroui.core.ui.components.controls.RetroButtonVariant
import com.funapp.retroui.core.ui.components.controls.RetroChip
import com.funapp.retroui.core.ui.components.controls.RetroIconButton
import com.funapp.retroui.core.ui.components.feedback.RetroStatusLabel
import com.funapp.retroui.core.ui.components.foundation.RetroText
import com.funapp.retroui.core.ui.components.game.RetroCardRarity
import com.funapp.retroui.core.ui.components.game.RetroGameCard
import com.funapp.retroui.core.ui.components.game.rarityColor
import com.funapp.retroui.core.ui.components.surfaces.RetroDialog
import com.funapp.retroui.core.ui.theme.RetroTheme
import com.funapp.retroui.core.data.mock.MockChampion
import com.funapp.retroui.core.data.mock.mockChampionRoster
import org.jetbrains.compose.resources.stringResource
import retroui.shared.generated.resources.Res
import retroui.shared.generated.resources.btn_deck_builder
import retroui.shared.generated.resources.card_owned
import retroui.shared.generated.resources.collection_count
import retroui.shared.generated.resources.common_close
import retroui.shared.generated.resources.rarity_all
import retroui.shared.generated.resources.rarity_common
import retroui.shared.generated.resources.rarity_epic
import retroui.shared.generated.resources.rarity_legendary
import retroui.shared.generated.resources.rarity_rare
import retroui.shared.generated.resources.screen_collection_title
import retroui.shared.generated.resources.sheet_card_cost
import retroui.shared.generated.resources.sheet_card_owned_no
import retroui.shared.generated.resources.sheet_card_owned_yes
import retroui.shared.generated.resources.sheet_card_type

private val rarityFilters: List<Pair<RetroCardRarity?, RarityLabel>> = listOf(
    null to RarityLabel.All,
    RetroCardRarity.Common to RarityLabel.Common,
    RetroCardRarity.Rare to RarityLabel.Rare,
    RetroCardRarity.Epic to RarityLabel.Epic,
    RetroCardRarity.Legendary to RarityLabel.Legendary,
)

private enum class RarityLabel {
    All, Common, Rare, Epic, Legendary,
}

@Composable
private fun rarityLabelText(label: RarityLabel): String = when (label) {
    RarityLabel.All -> stringResource(Res.string.rarity_all)
    RarityLabel.Common -> stringResource(Res.string.rarity_common)
    RarityLabel.Rare -> stringResource(Res.string.rarity_rare)
    RarityLabel.Epic -> stringResource(Res.string.rarity_epic)
    RarityLabel.Legendary -> stringResource(Res.string.rarity_legendary)
}

/**
 * Collection. Browse every earned card in a rarity-filterable grid.
 * Static mock data for now; wired to the repository in the data phase.
 */
@Composable
fun CollectionScreen(
    onGoDeckBuilder: () -> Unit,
    onGoHome: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val cards = mockChampionRoster()
    var filter by remember { mutableStateOf<RetroCardRarity?>(null) }
    var detailsCard by remember { mutableStateOf<MockChampion?>(null) }
    var previewCard by remember { mutableStateOf<MockChampion?>(null) }
    val filtered = if (filter == null) cards else cards.filter { it.rarity == filter }

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(RetroTheme.colors.background),
    ) {
        LazyVerticalGrid(
            columns = GridCells.Adaptive(minSize = 108.dp),
            modifier = Modifier
                .fillMaxSize()
                .windowInsetsPadding(WindowInsets.safeDrawing),
            contentPadding = PaddingValues(
                start = RetroTheme.spacing.lg,
                end = RetroTheme.spacing.lg,
                top = RetroTheme.spacing.lg,
                bottom = RetroTheme.dimensions.bottomBarClearance,
            ),
            horizontalArrangement = Arrangement.spacedBy(RetroTheme.spacing.sm),
            verticalArrangement = Arrangement.spacedBy(RetroTheme.spacing.sm),
        ) {
            item(span = { GridItemSpan(maxLineSpan) }) {
                CollectionHeader(
                    onGoDeckBuilder = onGoDeckBuilder,
                    modifier = Modifier.retroEntrance(delayMillis = 0),
                )
            }
            item(span = { GridItemSpan(maxLineSpan) }) {
                Spacer(modifier = Modifier.height(RetroTheme.spacing.md))
            }
            item(span = { GridItemSpan(maxLineSpan) }) {
                RarityFilters(
                    filter = filter,
                    onSelect = { filter = it },
                    modifier = Modifier.retroEntrance(delayMillis = 60),
                )
            }
            item(span = { GridItemSpan(maxLineSpan) }) {
                Spacer(modifier = Modifier.height(RetroTheme.spacing.md))
                RetroText(
                    text = stringResource(Res.string.collection_count, filtered.size),
                    style = RetroTheme.typography.caption,
                    color = RetroTheme.colors.textSecondary,
                    modifier = Modifier.retroEntrance(style = RetroEntranceStyle.Coin, delayMillis = 100),
                )
                Spacer(modifier = Modifier.height(RetroTheme.spacing.sm))
            }
            itemsIndexed(filtered) { index, card ->
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .retroEntrance(style = RetroEntranceStyle.Pop, delayMillis = 140 + retroCascade(index, stepMs = 40)),
                    contentAlignment = Alignment.Center,
                ) {
                    RetroGameCard(
                        title = card.name,
                        cost = card.cost,
                        type = card.type,
                        artworkIcon = card.icon,
                        rarity = card.rarity,
                        footer = stringResource(Res.string.card_owned, card.owned),
                        onClick = {
                            previewCard = null
                            detailsCard = card
                        },
                        onDoubleTap = {
                            detailsCard = null
                            previewCard = card
                        },
                    )
                }
            }
        }
    }

    detailsCard?.let { card ->
        RetroDialog(
            visible = true,
            onDismiss = { detailsCard = null },
            title = card.name,
            content = {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    RetroChip(
                        text = rarityLabel(card.rarity),
                        onClick = {},
                        selected = false,
                    )
                    Spacer(modifier = Modifier.weight(1f))
                    RetroStatusLabel(
                        text = stringResource(
                            if (card.owned > 0) {
                                Res.string.sheet_card_owned_yes
                            } else {
                                Res.string.sheet_card_owned_no
                            },
                        ),
                        dotColor = if (card.owned > 0) RetroTheme.colors.success else RetroTheme.colors.textMuted,
                        container = RetroTheme.colors.surfaceVariant,
                    )
                }
                Spacer(modifier = Modifier.height(RetroTheme.spacing.md))
                CardDetailRow(
                    label = stringResource(Res.string.sheet_card_type),
                    value = card.type,
                )
                Spacer(modifier = Modifier.height(RetroTheme.spacing.xs))
                CardDetailRow(
                    label = stringResource(Res.string.sheet_card_cost),
                    value = card.cost,
                )
                Spacer(modifier = Modifier.height(RetroTheme.spacing.md))
                RetroButton(
                    text = stringResource(Res.string.common_close),
                    variant = RetroButtonVariant.Danger,
                    onClick = { detailsCard = null },
                    small = true,
                    modifier = Modifier.fillMaxWidth(),
                )
            },
        )
    }

    previewCard?.let { card ->
        RetroDialog(
            visible = true,
            onDismiss = { previewCard = null },
            title = card.name,
            content = {
                RetroGameCard(
                    title = card.name,
                    cost = card.cost,
                    type = card.type,
                    artworkIcon = card.icon,
                    rarity = card.rarity,
                    footer = stringResource(Res.string.card_owned, card.owned),
                    width = 240.dp,
                    height = 340.dp,
                )
                Spacer(modifier = Modifier.height(RetroTheme.spacing.md))
                RetroButton(
                    text = stringResource(Res.string.common_close),
                    variant = RetroButtonVariant.Outline,
                    onClick = { previewCard = null },
                    small = true,
                    modifier = Modifier.fillMaxWidth(),
                )
            },
        )
    }
}

@Composable
private fun rarityLabel(rarity: RetroCardRarity): String = when (rarity) {
    RetroCardRarity.Common -> stringResource(Res.string.rarity_common)
    RetroCardRarity.Rare -> stringResource(Res.string.rarity_rare)
    RetroCardRarity.Epic -> stringResource(Res.string.rarity_epic)
    RetroCardRarity.Legendary -> stringResource(Res.string.rarity_legendary)
}

@Composable
private fun CardDetailRow(label: String, value: String) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        RetroText(
            text = label,
            style = RetroTheme.typography.caption,
            color = RetroTheme.colors.textSecondary,
        )
        Spacer(modifier = Modifier.weight(1f))
        RetroText(
            text = value,
            style = RetroTheme.typography.label,
            color = RetroTheme.colors.textPrimary,
        )
    }
}

@Composable
private fun CollectionHeader(onGoDeckBuilder: () -> Unit, modifier: Modifier = Modifier) {
    Row(modifier = modifier, verticalAlignment = Alignment.CenterVertically) {
        Column(modifier = Modifier.weight(1f)) {
            RetroText(
                text = stringResource(Res.string.screen_collection_title),
                style = RetroTheme.typography.heading,
                color = RetroTheme.colors.textPrimary,
            )
        }
        RetroIconButton(
            imageVector = RetroIcons.Edit,
            contentDescription = stringResource(Res.string.btn_deck_builder),
            onClick = onGoDeckBuilder,
            containerColor = RetroTheme.colors.surfaceVariant,
        )
    }
}

@Composable
private fun RarityFilters(
    filter: RetroCardRarity?,
    onSelect: (RetroCardRarity?) -> Unit,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier.horizontalScroll(rememberScrollState()),
        horizontalArrangement = Arrangement.spacedBy(RetroTheme.spacing.xs),
    ) {
        rarityFilters.forEach { (rarity, label) ->
            RetroChip(
                text = rarityLabelText(label),
                onClick = { onSelect(rarity) },
                selected = filter == rarity,
            )
        }
    }
}

