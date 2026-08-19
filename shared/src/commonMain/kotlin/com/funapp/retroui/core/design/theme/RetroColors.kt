package com.funapp.retroui.core.design.theme

import androidx.compose.ui.graphics.Color
import com.funapp.retroui.core.design.token.RetroPrimitiveColors as Prim

/**
 * Semantic color scheme.
 *
 * Application and component code reference ONLY these semantic colors —
 * never the primitive palette directly.
 *
 * Light ("paper") and dark ("arcade night") schemes are deliberately
 * designed — not mechanically inverted.
 */
data class RetroColors(
    // Brand / action
    val primary: Color,
    val onPrimary: Color,
    val primaryContainer: Color,
    val onPrimaryContainer: Color,
    val secondary: Color,
    val onSecondary: Color,
    val secondaryContainer: Color,
    val onSecondaryContainer: Color,
    val accent: Color,
    val onAccent: Color,
    val accentContainer: Color,
    val onAccentContainer: Color,

    // Surfaces
    val background: Color,
    val onBackground: Color,
    val surface: Color,
    val onSurface: Color,
    val surfaceVariant: Color,
    val onSurfaceVariant: Color,
    val surfaceMuted: Color,

    // Text
    val textPrimary: Color,
    val textSecondary: Color,
    val textMuted: Color,

    // Lines & dividers
    val outline: Color,
    val outlineStrong: Color,

    // Feedback
    val error: Color,
    val onError: Color,
    val errorContainer: Color,
    val onErrorContainer: Color,
    val success: Color,
    val onSuccess: Color,
    val successContainer: Color,
    val onSuccessContainer: Color,
    val warning: Color,
    val onWarning: Color,
    val warningContainer: Color,
    val onWarningContainer: Color,
    val info: Color,
    val onInfo: Color,
    val infoContainer: Color,
    val onInfoContainer: Color,

    // Decorative accents
    val decorPrimary: Color,
    val decorSecondary: Color,
    val decorAccent: Color,
)

val LightRetroColors = RetroColors(
    primary = Prim.Green,
    onPrimary = Prim.Ink,
    primaryContainer = Color(0xFFBCF2CD),
    onPrimaryContainer = Prim.GreenDeep,
    secondary = Prim.Yellow,
    onSecondary = Prim.Ink,
    secondaryContainer = Color(0xFFFFE8A8),
    onSecondaryContainer = Prim.YellowDeep,
    accent = Prim.Purple,
    onAccent = Color(0xFFF7F1FF),
    accentContainer = Color(0xFFE4D8FF),
    onAccentContainer = Prim.PurpleDeep,

    background = Prim.Paper,
    onBackground = Prim.Ink,
    surface = Prim.PaperBright,
    onSurface = Prim.Ink,
    surfaceVariant = Prim.PaperTint,
    onSurfaceVariant = Prim.InkSoft,
    surfaceMuted = Prim.PaperMuted,

    textPrimary = Prim.Ink,
    textSecondary = Prim.InkSoft,
    textMuted = Prim.InkMuted,

    outline = Prim.Ink,
    outlineStrong = Prim.Ink,

    error = Prim.Red,
    onError = Prim.Ink,
    errorContainer = Color(0xFFFBC0C0),
    onErrorContainer = Prim.RedDeep,
    success = Prim.Green,
    onSuccess = Prim.Ink,
    successContainer = Color(0xFFBCF2CD),
    onSuccessContainer = Prim.GreenDeep,
    warning = Prim.Orange,
    onWarning = Prim.Ink,
    warningContainer = Color(0xFFFCD9A8),
    onWarningContainer = Prim.OrangeDeep,
    info = Prim.Blue,
    onInfo = Color(0xFFF2F4FF),
    infoContainer = Color(0xFFC9D3FF),
    onInfoContainer = Prim.BlueDeep,

    decorPrimary = Prim.Purple,
    decorSecondary = Prim.Blue,
    decorAccent = Prim.Pink,
)

val DarkRetroColors = RetroColors(
    primary = Prim.Green,
    onPrimary = Prim.Night,
    primaryContainer = Color(0xFF1E4A2E),
    onPrimaryContainer = Color(0xFF9BE8B3),
    secondary = Prim.Yellow,
    onSecondary = Prim.Night,
    secondaryContainer = Color(0xFF4A3B10),
    onSecondaryContainer = Color(0xFFEBCE82),
    accent = Prim.Purple,
    onAccent = Color(0xFF15101F),
    accentContainer = Color(0xFF3B2A5E),
    onAccentContainer = Color(0xFFCDB8F5),

    background = Prim.Night,
    onBackground = Prim.NightInk,
    surface = Prim.NightSurface,
    onSurface = Prim.NightInk,
    surfaceVariant = Prim.NightTint,
    onSurfaceVariant = Prim.NightInkSoft,
    surfaceMuted = Prim.NightMuted,

    textPrimary = Prim.NightInk,
    textSecondary = Prim.NightInkSoft,
    textMuted = Prim.NightInkMuted,

    outline = Prim.NightInk,
    outlineStrong = Prim.NightInk,

    error = Prim.Red,
    onError = Prim.Night,
    errorContainer = Color(0xFF4A1E1E),
    onErrorContainer = Color(0xFFF5A9A9),
    success = Prim.Green,
    onSuccess = Prim.Night,
    successContainer = Color(0xFF1E4A2E),
    onSuccessContainer = Color(0xFF9BE8B3),
    warning = Prim.Orange,
    onWarning = Prim.Night,
    warningContainer = Color(0xFF4A3A14),
    onWarningContainer = Color(0xFFEBCE82),
    info = Prim.Blue,
    onInfo = Color(0xFF10142B),
    infoContainer = Color(0xFF1E2A5E),
    onInfoContainer = Color(0xFFB3C2FF),

    decorPrimary = Prim.Purple,
    decorSecondary = Prim.Blue,
    decorAccent = Prim.Pink,
)