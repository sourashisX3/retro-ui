package com.funapp.retroui.core.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Shapes
import androidx.compose.material3.Typography
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color
import com.funapp.retroui.core.ui.token.DefaultRetroBorders
import com.funapp.retroui.core.ui.token.DefaultRetroDimensions
import com.funapp.retroui.core.ui.token.DefaultRetroElevation
import com.funapp.retroui.core.ui.token.DefaultRetroShapeTokens
import com.funapp.retroui.core.ui.token.DefaultRetroShapes
import com.funapp.retroui.core.ui.token.DefaultRetroSpacing
import com.funapp.retroui.core.ui.token.FallbackRetroTypography
import com.funapp.retroui.core.ui.token.RetroBorders
import com.funapp.retroui.core.ui.token.RetroDimensions
import com.funapp.retroui.core.ui.token.RetroElevation
import com.funapp.retroui.core.ui.token.RetroShapeTokens
import com.funapp.retroui.core.ui.token.RetroShapes
import com.funapp.retroui.core.ui.token.RetroSpacing
import com.funapp.retroui.core.ui.token.RetroTypography
import com.funapp.retroui.core.ui.token.buildRetroTypography

@Immutable
data class RetroTokens(
    val colors: RetroColors,
    val typography: RetroTypography,
    val spacing: RetroSpacing,
    val dimensions: RetroDimensions,
    val shapes: RetroShapes,
    val shapeTokens: RetroShapeTokens,
    val elevation: RetroElevation,
    val borders: RetroBorders,
)

internal val LocalRetroTokens = staticCompositionLocalOf {
    RetroTokens(
        colors = LightRetroColors,
        typography = FallbackRetroTypography,
        spacing = DefaultRetroSpacing,
        dimensions = DefaultRetroDimensions,
        shapes = DefaultRetroShapes,
        shapeTokens = DefaultRetroShapeTokens,
        elevation = DefaultRetroElevation,
        borders = DefaultRetroBorders,
    )
}

/**
 * Central design-system accessor.
 *
 * Usage: `RetroTheme.colors.primary`, `RetroTheme.spacing.lg`,
 * `RetroTheme.typography.title`. Only valid inside a [RetroTheme] composable.
 */
object RetroTheme {
    val colors: RetroColors @Composable get() = LocalRetroTokens.current.colors
    val typography: RetroTypography @Composable get() = LocalRetroTokens.current.typography
    val spacing: RetroSpacing @Composable get() = LocalRetroTokens.current.spacing
    val dimensions: RetroDimensions @Composable get() = LocalRetroTokens.current.dimensions
    val shapes: RetroShapes @Composable get() = LocalRetroTokens.current.shapes
    val shapeTokens: RetroShapeTokens @Composable get() = LocalRetroTokens.current.shapeTokens
    val elevation: RetroElevation @Composable get() = LocalRetroTokens.current.elevation
    val borders: RetroBorders @Composable get() = LocalRetroTokens.current.borders
}

/**
 * RetroTheme provides the full design token set and bridges into Material3
 * so stock Material widgets adapt to the retro palette.
 *
 * @param darkTheme explicit theme override; default follows the system.
 */
@Composable
fun RetroTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit,
) {
    val retroColors = if (darkTheme) DarkRetroColors else LightRetroColors
    val retroTypography = buildRetroTypography()
    val tokens = RetroTokens(
        colors = retroColors,
        typography = retroTypography,
        spacing = DefaultRetroSpacing,
        dimensions = DefaultRetroDimensions,
        shapes = DefaultRetroShapes,
        shapeTokens = DefaultRetroShapeTokens,
        elevation = DefaultRetroElevation,
        borders = DefaultRetroBorders,
    )

    CompositionLocalProvider(LocalRetroTokens provides tokens) {
        MaterialTheme(
            colorScheme = retroColors.toMaterial3ColorScheme(darkTheme),
            typography = retroTypography.toMaterial3Typography(),
            shapes = retroColors.toMaterial3Shapes(),
            content = content,
        )
    }
}

private fun RetroColors.toMaterial3ColorScheme(darkTheme: Boolean): ColorScheme {
    val base = if (darkTheme) darkColorScheme() else lightColorScheme()
    return base.copy(
        primary = primary,
        onPrimary = onPrimary,
        primaryContainer = primaryContainer,
        onPrimaryContainer = onPrimaryContainer,
        secondary = secondary,
        onSecondary = onSecondary,
        secondaryContainer = secondaryContainer,
        onSecondaryContainer = onSecondaryContainer,
        tertiary = accent,
        onTertiary = onAccent,
        tertiaryContainer = accentContainer,
        onTertiaryContainer = onAccentContainer,
        error = error,
        onError = onError,
        errorContainer = errorContainer,
        onErrorContainer = onErrorContainer,
        background = background,
        onBackground = onBackground,
        surface = surface,
        onSurface = onSurface,
        surfaceVariant = surfaceVariant,
        onSurfaceVariant = onSurfaceVariant,
        outline = outline,
        outlineVariant = surfaceMuted,
        scrim = Color.Black,
        surfaceTint = primary,
        inverseSurface = surfaceVariant,
        inverseOnSurface = onSurface,
        inversePrimary = primary,
        surfaceDim = if (darkTheme) background else surfaceMuted,
        surfaceBright = surface,
        surfaceContainerLowest = surface,
        surfaceContainerLow = surface,
        surfaceContainer = surfaceVariant,
        surfaceContainerHigh = surfaceVariant,
        surfaceContainerHighest = surfaceRaised,
    )
}

private fun RetroTypography.toMaterial3Typography(): Typography = Typography(
    displayLarge = display,
    headlineLarge = headline,
    titleLarge = title,
    bodyLarge = body,
    bodyMedium = bodySmall,
    labelLarge = label,
    labelSmall = caption,
)

private fun RetroColors.toMaterial3Shapes(): Shapes {
    val s = DefaultRetroShapeTokens
    return Shapes(
        small = s.input,
        medium = s.card,
        large = s.dialog,
        extraLarge = s.sheet,
    )
}
