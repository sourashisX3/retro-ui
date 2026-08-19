package com.funapp.retroui.core.ui.components.foundation

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import com.funapp.retroui.core.ui.theme.RetroTheme

/**
 * Text rendered with the retro design-system typography.
 * Use `RetroTheme.typography.*` for style roles.
 */
@Composable
fun RetroText(
    text: String,
    style: TextStyle,
    modifier: Modifier = Modifier,
    color: Color = RetroTheme.colors.textPrimary,
    textAlign: TextAlign? = null,
    maxLines: Int = Int.MAX_VALUE,
    overflow: TextOverflow = TextOverflow.Clip,
) {
    Text(
        text = text,
        style = style,
        color = color,
        modifier = modifier,
        textAlign = textAlign,
        maxLines = maxLines,
        overflow = overflow,
    )
}
