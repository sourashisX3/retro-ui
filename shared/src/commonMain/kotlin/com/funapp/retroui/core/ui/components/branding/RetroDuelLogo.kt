package com.funapp.retroui.core.ui.components.branding

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.funapp.retroui.core.ui.theme.RetroTheme
import org.jetbrains.compose.resources.stringResource
import retroui.shared.generated.resources.Res
import retroui.shared.generated.resources.brand_wordmark

/** Wordmark size variants. */
enum class RetroLogoSize { Large, Medium, Small }

/**
 * DECKRON wordmark.
 *
 * Typographic only — pixel display font, thick ink outline, bright arcade
 * accent fill and a hard offset shadow. Optional pixel [RetroStar]/[RetroSparkle]
 * decorations frame the wordmark.
 */
@Composable
fun RetroDuelLogo(
    modifier: Modifier = Modifier,
    size: RetroLogoSize = RetroLogoSize.Large,
    fill: Color = RetroTheme.colors.primary,
    outline: Color = RetroTheme.colors.outline,
    shadow: Color = RetroTheme.colors.shadow,
    decorated: Boolean = true,
) {
    val typography = RetroTheme.typography
    val style: TextStyle = when (size) {
        RetroLogoSize.Large -> typography.display
        RetroLogoSize.Medium -> typography.headline
        RetroLogoSize.Small -> typography.heading
    }
    val outlineWidth: Dp = when (size) {
        RetroLogoSize.Large -> 4.dp
        RetroLogoSize.Medium -> 3.dp
        RetroLogoSize.Small -> 2.dp
    }

    Box(modifier = modifier, contentAlignment = Alignment.Center) {
        if (decorated && size != RetroLogoSize.Small) {
            RetroSparkle(
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .offset(x = 6.dp, y = (-6).dp)
                    .size(if (size == RetroLogoSize.Large) 18.dp else 12.dp),
                color = fill,
            )
            RetroStar(
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .offset(x = (-4).dp, y = 6.dp)
                    .size(if (size == RetroLogoSize.Large) 14.dp else 10.dp),
                color = shadow,
            )
        }

        RetroOutlineText(
            text = stringResource(Res.string.brand_wordmark),
            style = style,
            fill = fill,
            outline = outline,
            shadow = shadow,
            outlineWidth = outlineWidth,
        )
    }
}
