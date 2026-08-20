package com.funapp.retroui.core.ui.token

import androidx.compose.runtime.Composable
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import org.jetbrains.compose.resources.Font
import retroui.shared.generated.resources.Res
import retroui.shared.generated.resources.pixelify_sans_bold
import retroui.shared.generated.resources.pixelify_sans_medium
import retroui.shared.generated.resources.pixelify_sans_regular
import retroui.shared.generated.resources.pixelify_sans_semibold
import retroui.shared.generated.resources.press_start_2p

/**
 * Semantic typography scale.
 *
 * The retro kit deliberately mixes two voices:
 *  - PIXEL DISPLAY (Press Start 2P) for headings, buttons, HUD, stats
 *  - READABLE BODY (Pixelify Sans — pixel-styled but legible) for long text
 *
 * Body styles use tabular numerals (`tnum`) so digits keep uniform width and
 * stay readable in scores, counts and stat rows.
 */
data class RetroTypography(
    val display: TextStyle,
    val headline: TextStyle,
    val heading: TextStyle,
    val title: TextStyle,
    val score: TextStyle,
    val label: TextStyle,
    val button: TextStyle,
    val body: TextStyle,
    val bodySmall: TextStyle,
    val caption: TextStyle,
)

/** Pixel / arcade typeface used for headings, buttons and game HUD labels. */
@Composable
private fun pixelFontFamily(): FontFamily = FontFamily(
    Font(Res.font.press_start_2p, FontWeight.Normal),
)

/** Pixel-styled but readable typeface used for body and long text. */
@Composable
private fun bodyFontFamily(): FontFamily = FontFamily(
    Font(Res.font.pixelify_sans_regular, FontWeight.Normal),
    Font(Res.font.pixelify_sans_medium, FontWeight.Medium),
    Font(Res.font.pixelify_sans_semibold, FontWeight.SemiBold),
    Font(Res.font.pixelify_sans_bold, FontWeight.Bold),
)

/** Builds the typography with the brand typefaces. Call from composition. */
@Composable
fun buildRetroTypography(): RetroTypography = RetroTypography(
    display = TextStyle(
        fontFamily = pixelFontFamily(),
        fontSize = 34.sp,
        lineHeight = 40.sp,
        fontWeight = FontWeight.Normal,
        letterSpacing = 0.sp,
    ),
    headline = TextStyle(
        fontFamily = pixelFontFamily(),
        fontSize = 24.sp,
        lineHeight = 30.sp,
        fontWeight = FontWeight.Normal,
    ),
    heading = TextStyle(
        fontFamily = pixelFontFamily(),
        fontSize = 20.sp,
        lineHeight = 26.sp,
        fontWeight = FontWeight.Normal,
    ),
    title = TextStyle(
        fontFamily = pixelFontFamily(),
        fontSize = 16.sp,
        lineHeight = 22.sp,
        fontWeight = FontWeight.Normal,
    ),
    score = TextStyle(
        fontFamily = pixelFontFamily(),
        fontSize = 20.sp,
        lineHeight = 26.sp,
        fontWeight = FontWeight.Normal,
        letterSpacing = 1.sp,
    ),
    label = TextStyle(
        fontFamily = pixelFontFamily(),
        fontSize = 12.sp,
        lineHeight = 16.sp,
        fontWeight = FontWeight.Normal,
    ),
    button = TextStyle(
        fontFamily = pixelFontFamily(),
        fontSize = 14.sp,
        lineHeight = 18.sp,
        fontWeight = FontWeight.Normal,
    ),
    body = TextStyle(
        fontFamily = bodyFontFamily(),
        fontSize = 17.sp,
        lineHeight = 26.sp,
        fontWeight = FontWeight.Normal,
        letterSpacing = 0.25.sp,
        fontFeatureSettings = "tnum",
    ),
    bodySmall = TextStyle(
        fontFamily = bodyFontFamily(),
        fontSize = 15.sp,
        lineHeight = 22.sp,
        fontWeight = FontWeight.Medium,
        letterSpacing = 0.25.sp,
        fontFeatureSettings = "tnum",
    ),
    caption = TextStyle(
        fontFamily = bodyFontFamily(),
        fontSize = 13.sp,
        lineHeight = 18.sp,
        fontWeight = FontWeight.Medium,
        letterSpacing = 0.25.sp,
        fontFeatureSettings = "tnum",
    ),
)

/** Non-composable fallback used as the CompositionLocal default. */
val FallbackRetroTypography = RetroTypography(
    display = TextStyle(fontSize = 34.sp, lineHeight = 40.sp, fontWeight = FontWeight.Normal),
    headline = TextStyle(fontSize = 24.sp, lineHeight = 30.sp, fontWeight = FontWeight.Normal),
    heading = TextStyle(fontSize = 20.sp, lineHeight = 26.sp, fontWeight = FontWeight.Normal),
    title = TextStyle(fontSize = 16.sp, lineHeight = 22.sp, fontWeight = FontWeight.Normal),
    score = TextStyle(fontSize = 20.sp, lineHeight = 26.sp, fontWeight = FontWeight.Normal),
    label = TextStyle(fontSize = 12.sp, lineHeight = 16.sp, fontWeight = FontWeight.Normal),
    button = TextStyle(fontSize = 14.sp, lineHeight = 18.sp, fontWeight = FontWeight.Normal),
    body = TextStyle(fontSize = 17.sp, lineHeight = 26.sp, fontWeight = FontWeight.Normal, fontFeatureSettings = "tnum"),
    bodySmall = TextStyle(fontSize = 15.sp, lineHeight = 22.sp, fontWeight = FontWeight.Medium, fontFeatureSettings = "tnum"),
    caption = TextStyle(fontSize = 13.sp, lineHeight = 18.sp, fontWeight = FontWeight.Medium, fontFeatureSettings = "tnum"),
)
