package com.funapp.retroui.features.collection.presentation.screens

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
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import com.funapp.retroui.core.design.components.controls.RetroButton
import com.funapp.retroui.core.design.components.controls.RetroButtonVariant
import com.funapp.retroui.core.design.components.controls.RetroChip
import com.funapp.retroui.core.design.components.foundation.RetroText
import com.funapp.retroui.core.design.components.game.RetroCardRarity
import com.funapp.retroui.core.design.components.game.RetroGameCard
import com.funapp.retroui.core.design.theme.RetroTheme
import org.jetbrains.compose.resources.stringResource
import retroui.shared.generated.resources.Res
import retroui.shared.generated.resources.btn_deck_builder
import retroui.shared.generated.resources.card_blaze
import retroui.shared.generated.resources.card_ember
import retroui.shared.generated.resources.card_frost
import retroui.shared.generated.resources.card_gale
import retroui.shared.generated.resources.card_nova
import retroui.shared.generated.resources.card_owned
import retroui.shared.generated.resources.card_rock
import retroui.shared.generated.resources.card_sage
import retroui.shared.generated.resources.card_shade
import retroui.shared.generated.resources.card_terra
import retroui.shared.generated.resources.card_volt
import retroui.shared.generated.resources.collection_count
import retroui.shared.generated.resources.rarity_all
import retroui.shared.generated.resources.rarity_common
import retroui.shared.generated.resources.rarity_epic
import retroui.shared.generated.resources.rarity_legendary
import retroui.shared.generated.resources.rarity_rare
import retroui.shared.generated.resources.screen_collection_subtitle
import retroui.shared.generated.resources.screen_collection_title
import retroui.shared.generated.resources.type_assassin
import retroui.shared.generated.resources.type_guardian
import retroui.shared.generated.resources.type_mage
import retroui.shared.generated.resources.type_rogue
import retroui.shared.generated.resources.type_sage
import retroui.shared.generated.resources.type_warrior

private data class CollectionCard(
    val title: String,
    val cost: String,
    val type: String,
    val artworkIcon: ImageVector,
    val rarity: RetroCardRarity,
    val owned: Int,
)

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
    val cards = mockCollection()
    var filter by remember { mutableStateOf<RetroCardRarity?>(null) }
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
            contentPadding = PaddingValues(RetroTheme.spacing.lg),
            horizontalArrangement = Arrangement.spacedBy(RetroTheme.spacing.sm),
            verticalArrangement = Arrangement.spacedBy(RetroTheme.spacing.sm),
        ) {
            item(span = { GridItemSpan(maxLineSpan) }) {
                CollectionHeader(onGoDeckBuilder = onGoDeckBuilder)
            }
            item(span = { GridItemSpan(maxLineSpan) }) {
                Spacer(modifier = Modifier.height(RetroTheme.spacing.md))
            }
            item(span = { GridItemSpan(maxLineSpan) }) {
                RarityFilters(
                    filter = filter,
                    onSelect = { filter = it },
                )
            }
            item(span = { GridItemSpan(maxLineSpan) }) {
                Spacer(modifier = Modifier.height(RetroTheme.spacing.md))
                RetroText(
                    text = stringResource(Res.string.collection_count, filtered.size),
                    style = RetroTheme.typography.caption,
                    color = RetroTheme.colors.textSecondary,
                )
                Spacer(modifier = Modifier.height(RetroTheme.spacing.sm))
            }
            items(filtered) { card ->
                Box(
                    modifier = Modifier.fillMaxWidth(),
                    contentAlignment = Alignment.Center,
                ) {
                    RetroGameCard(
                        title = card.title,
                        cost = card.cost,
                        type = card.type,
                        artworkIcon = card.artworkIcon,
                        rarity = card.rarity,
                        footer = stringResource(Res.string.card_owned, card.owned),
                    )
                }
            }
        }
    }
}

@Composable
private fun CollectionHeader(onGoDeckBuilder: () -> Unit) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Column(modifier = Modifier.weight(1f)) {
            RetroText(
                text = stringResource(Res.string.screen_collection_title),
                style = RetroTheme.typography.heading,
                color = RetroTheme.colors.textPrimary,
            )
            RetroText(
                text = stringResource(Res.string.screen_collection_subtitle),
                style = RetroTheme.typography.caption,
                color = RetroTheme.colors.textMuted,
            )
        }
        RetroButton(
            text = stringResource(Res.string.btn_deck_builder),
            leadingIcon = Icons.Filled.Edit,
            variant = RetroButtonVariant.Secondary,
            onClick = onGoDeckBuilder,
            small = true,
        )
    }
}

@Composable
private fun RarityFilters(
    filter: RetroCardRarity?,
    onSelect: (RetroCardRarity?) -> Unit,
) {
    Row(
        modifier = Modifier.horizontalScroll(rememberScrollState()),
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

@Composable
private fun mockCollection(): List<CollectionCard> = listOf(
    CollectionCard(
        title = stringResource(Res.string.card_blaze),
        cost = "2",
        type = stringResource(Res.string.type_warrior),
        artworkIcon = Icons.Filled.Star,
        rarity = RetroCardRarity.Legendary,
        owned = 1,
    ),
    CollectionCard(
        title = stringResource(Res.string.card_frost),
        cost = "3",
        type = stringResource(Res.string.type_mage),
        artworkIcon = Icons.Filled.Favorite,
        rarity = RetroCardRarity.Epic,
        owned = 2,
    ),
    CollectionCard(
        title = stringResource(Res.string.card_volt),
        cost = "1",
        type = stringResource(Res.string.type_rogue),
        artworkIcon = Icons.Filled.Person,
        rarity = RetroCardRarity.Rare,
        owned = 3,
    ),
    CollectionCard(
        title = stringResource(Res.string.card_terra),
        cost = "4",
        type = stringResource(Res.string.type_guardian),
        artworkIcon = Icons.Filled.Face,
        rarity = RetroCardRarity.Common,
        owned = 4,
    ),
    CollectionCard(
        title = stringResource(Res.string.card_shade),
        cost = "2",
        type = stringResource(Res.string.type_assassin),
        artworkIcon = Icons.Filled.AccountCircle,
        rarity = RetroCardRarity.Epic,
        owned = 1,
    ),
    CollectionCard(
        title = stringResource(Res.string.card_nova),
        cost = "3",
        type = stringResource(Res.string.type_mage),
        artworkIcon = Icons.Filled.Check,
        rarity = RetroCardRarity.Rare,
        owned = 2,
    ),
    CollectionCard(
        title = stringResource(Res.string.card_rock),
        cost = "5",
        type = stringResource(Res.string.type_guardian),
        artworkIcon = Icons.Filled.Home,
        rarity = RetroCardRarity.Common,
        owned = 2,
    ),
    CollectionCard(
        title = stringResource(Res.string.card_gale),
        cost = "1",
        type = stringResource(Res.string.type_rogue),
        artworkIcon = Icons.Filled.Search,
        rarity = RetroCardRarity.Common,
        owned = 3,
    ),
    CollectionCard(
        title = stringResource(Res.string.card_ember),
        cost = "2",
        type = stringResource(Res.string.type_warrior),
        artworkIcon = Icons.Filled.Settings,
        rarity = RetroCardRarity.Rare,
        owned = 1,
    ),
    CollectionCard(
        title = stringResource(Res.string.card_sage),
        cost = "4",
        type = stringResource(Res.string.type_sage),
        artworkIcon = Icons.Filled.Notifications,
        rarity = RetroCardRarity.Legendary,
        owned = 1,
    ),
)