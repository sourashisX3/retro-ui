package com.funapp.retroui.core.ui.token

import androidx.compose.ui.graphics.Color

/**
 * Primitive color palette.
 *
 * Raw colors only. Feature code must NEVER use these directly — always go
 * through the semantic [com.funapp.retroui.core.ui.theme.RetroColors]
 * layer exposed by `RetroTheme`.
 */
object RetroPrimitiveColors {

    // Warm paper / cream surfaces
    val Paper = Color(0xFFF8F0DA)
    val PaperBright = Color(0xFFFFF8E8)
    val PaperTint = Color(0xFFEFE1C0)
    val PaperRaised = Color(0xFFE9DCB8)
    val PaperMuted = Color(0xFFE6D3C0)
    val PaperDeep = Color(0xFFD9C49F)

    // Ink / outline
    val Ink = Color(0xFF171717)
    val InkSoft = Color(0xFF3A3A3A)
    val InkMuted = Color(0xFF6B5D4B)

    // Arcade accents (flat)
    val Green = Color(0xFF45D66F)
    val GreenDeep = Color(0xFF2FA953)
    val Yellow = Color(0xFFFFC52E)
    val YellowDeep = Color(0xFFD99E00)
    val Orange = Color(0xFFF4A62A)
    val OrangeDeep = Color(0xFFC97E12)
    val Red = Color(0xFFF05B5B)
    val RedDeep = Color(0xFFC63A3A)
    val Blue = Color(0xFF4C6FFF)
    val BlueDeep = Color(0xFF3451D6)
    val Purple = Color(0xFF8B5CF6)
    val PurpleDeep = Color(0xFF6D3FE0)
    val Pink = Color(0xFFF06491)
    val PinkDeep = Color(0xFFD04474)

    // Dark arcade mode
    val Night = Color(0xFF14140F)
    val NightSurface = Color(0xFF1E1C16)
    val NightTint = Color(0xFF2A261D)
    val NightRaised = Color(0xFF2E2A1F)
    val NightMuted = Color(0xFF3A352A)
    val NightInk = Color(0xFFF8F0DA)
    val NightInkSoft = Color(0xFFCBC3B0)
    val NightInkMuted = Color(0xFF9C9484)
}
