package com.funapp.retroui.core.design.components.foundation

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.funapp.retroui.core.design.theme.RetroTheme

/**
 * A deliberately printed dark divider — 1-2dp of ink, never a subtle gray
 * Material hairline.
 */
@Composable
fun RetroDivider(
    modifier: Modifier = Modifier,
    thickness: Dp = 2.dp,
    horizontalPadding: Dp = 0.dp,
) {
    HorizontalDivider(
        modifier = modifier
            .padding(horizontal = horizontalPadding)
            .fillMaxWidth(),
        thickness = thickness,
        color = RetroTheme.colors.outlineStrong,
    )
}

@Composable
fun RetroVerticalDivider(
    modifier: Modifier = Modifier,
    thickness: Dp = 2.dp,
) {
    VerticalDivider(
        modifier = modifier,
        thickness = thickness,
        color = RetroTheme.colors.outlineStrong,
    )
}