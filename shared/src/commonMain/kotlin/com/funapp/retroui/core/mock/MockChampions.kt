package com.funapp.retroui.core.mock

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Star
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import com.funapp.retroui.core.design.components.game.RetroCardRarity
import org.jetbrains.compose.resources.stringResource
import retroui.shared.generated.resources.Res
import retroui.shared.generated.resources.card_blaze
import retroui.shared.generated.resources.card_ember
import retroui.shared.generated.resources.card_frost
import retroui.shared.generated.resources.card_gale
import retroui.shared.generated.resources.card_nova
import retroui.shared.generated.resources.card_rock
import retroui.shared.generated.resources.card_sage
import retroui.shared.generated.resources.card_shade
import retroui.shared.generated.resources.card_terra
import retroui.shared.generated.resources.card_volt
import retroui.shared.generated.resources.type_assassin
import retroui.shared.generated.resources.type_guardian
import retroui.shared.generated.resources.type_mage
import retroui.shared.generated.resources.type_rogue
import retroui.shared.generated.resources.type_sage
import retroui.shared.generated.resources.type_warrior

/**
 * A collectible champion in the roster.
 *
 * Names/types come from string resources; visuals come from core icons and
 * rarity tokens. Replaced by repository data in the backend phase.
 */
data class MockChampion(
    val name: String,
    val type: String,
    val cost: String,
    val icon: ImageVector,
    val rarity: RetroCardRarity,
    val owned: Int,
)

/** The full mock roster shared across collection, deck, home and battle. */
@Composable
fun mockChampionRoster(): List<MockChampion> = listOf(
    MockChampion(
        name = stringResource(Res.string.card_blaze),
        type = stringResource(Res.string.type_warrior),
        cost = "2",
        icon = Icons.Filled.Star,
        rarity = RetroCardRarity.Legendary,
        owned = 1,
    ),
    MockChampion(
        name = stringResource(Res.string.card_frost),
        type = stringResource(Res.string.type_mage),
        cost = "3",
        icon = Icons.Filled.Favorite,
        rarity = RetroCardRarity.Epic,
        owned = 2,
    ),
    MockChampion(
        name = stringResource(Res.string.card_volt),
        type = stringResource(Res.string.type_rogue),
        cost = "1",
        icon = Icons.Filled.Person,
        rarity = RetroCardRarity.Rare,
        owned = 3,
    ),
    MockChampion(
        name = stringResource(Res.string.card_terra),
        type = stringResource(Res.string.type_guardian),
        cost = "4",
        icon = Icons.Filled.Face,
        rarity = RetroCardRarity.Common,
        owned = 4,
    ),
    MockChampion(
        name = stringResource(Res.string.card_shade),
        type = stringResource(Res.string.type_assassin),
        cost = "2",
        icon = Icons.Filled.AccountCircle,
        rarity = RetroCardRarity.Epic,
        owned = 1,
    ),
    MockChampion(
        name = stringResource(Res.string.card_nova),
        type = stringResource(Res.string.type_mage),
        cost = "3",
        icon = Icons.Filled.Check,
        rarity = RetroCardRarity.Rare,
        owned = 2,
    ),
    MockChampion(
        name = stringResource(Res.string.card_rock),
        type = stringResource(Res.string.type_guardian),
        cost = "5",
        icon = Icons.Filled.Home,
        rarity = RetroCardRarity.Common,
        owned = 2,
    ),
    MockChampion(
        name = stringResource(Res.string.card_gale),
        type = stringResource(Res.string.type_rogue),
        cost = "1",
        icon = Icons.Filled.Search,
        rarity = RetroCardRarity.Common,
        owned = 3,
    ),
    MockChampion(
        name = stringResource(Res.string.card_ember),
        type = stringResource(Res.string.type_warrior),
        cost = "2",
        icon = Icons.Filled.Settings,
        rarity = RetroCardRarity.Rare,
        owned = 1,
    ),
    MockChampion(
        name = stringResource(Res.string.card_sage),
        type = stringResource(Res.string.type_sage),
        cost = "4",
        icon = Icons.Filled.Notifications,
        rarity = RetroCardRarity.Legendary,
        owned = 1,
    ),
)