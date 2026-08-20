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
    val Paper = Color(0xFFF3E9D2)
    val PaperBright = Color(0xFFFFFDF5)
    val PaperTint = Color(0xFFE3E7EF)
    val PaperRaised = Color(0xFFFFFFFF)
    val PaperMuted = Color(0xFFE7DCC3)
    val PaperDeep = Color(0xFFD9C49F)

    // Ink / outline
    val Ink = Color(0xFF171717)
    val InkSoft = Color(0xFF3A3A3A)
    val InkMuted = Color(0xFF6E6558)

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

    // Dark arcade mode (arcade navy)
    val Night = Color(0xFF0E1220)
    val NightSurface = Color(0xFF1A2032)
    val NightTint = Color(0xFF242C45)
    val NightRaised = Color(0xFF2D3553)
    val NightMuted = Color(0xFF161B2B)
    val NightInk = Color(0xFFF4F6FB)
    val NightInkSoft = Color(0xFFB9C2D4)
    val NightInkMuted = Color(0xFF7E879C)

    // Dark-mode chrome ("inverted ink"): the border is a light navy ink — clearly
    // lighter than every surface and the page — and the hard drop shadow is
    // slightly darker than the border (but still lighter than the page), so
    // elements read as outlined blocks with real shadows, never glowing.
    val NightOutline = Color(0xFF54639A)
    val NightShadow = Color(0xFF3A4670)
}
