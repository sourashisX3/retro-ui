package com.funapp.retroui.core.design.token

import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

/**
 * Semantic dimensions for touch targets, control heights and icon sizes.
 *
 * All interactive elements meet the accessibility-friendly minimum touch
 * target of [touchTargetMin].
 */
data class RetroDimensions(
    // Touch targets
    val touchTargetMin: Dp = 48.dp,
    val touchTargetCompact: Dp = 40.dp,

    // Control heights
    val buttonHeight: Dp = 44.dp,
    val buttonHeightSmall: Dp = 36.dp,
    val inputHeight: Dp = 44.dp,
    val chipHeight: Dp = 32.dp,

    // Bars
    val topBarHeight: Dp = 56.dp,
    val bottomBarHeight: Dp = 56.dp,
    val bottomBarClearance: Dp = 96.dp,
    val progressBarHeight: Dp = 14.dp,
    val progressBarThin: Dp = 8.dp,

    // Icon sizes
    val iconXS: Dp = 16.dp,
    val iconSM: Dp = 18.dp,
    val iconMD: Dp = 22.dp,
    val iconLG: Dp = 30.dp,
    val iconXL: Dp = 44.dp,

    // Avatars
    val avatarXS: Dp = 24.dp,
    val avatarSM: Dp = 32.dp,
    val avatarMD: Dp = 44.dp,
    val avatarLG: Dp = 56.dp,
    val avatarXL: Dp = 72.dp,

    // Badges
    val badgeMinHeight: Dp = 20.dp,

    // Game cards (collection grid / battle hand)
    val gameCardWidth: Dp = 96.dp,
    val gameCardHeight: Dp = 136.dp,
    val handCardWidth: Dp = 92.dp,
    val handCardHeight: Dp = 132.dp,
    val miniCardWidth: Dp = 72.dp,
    val miniCardHeight: Dp = 100.dp,

    // Deck slots & stat cards
    val deckCardWidth: Dp = 56.dp,
    val deckCardHeight: Dp = 76.dp,
    val cardSlotSize: Dp = 48.dp,
    val statCardWidth: Dp = 120.dp,

    // Layout
    val contentMaxWidth: Dp = 640.dp,
)

val DefaultRetroDimensions = RetroDimensions()