package com.funapp.retroui.features.battle.presentation.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.funapp.retroui.core.design.components.foundation.RetroText
import com.funapp.retroui.core.design.components.foundation.retroHardShadow
import com.funapp.retroui.core.design.theme.RetroTheme
import org.jetbrains.compose.resources.stringResource
import retroui.shared.generated.resources.Res
import retroui.shared.generated.resources.battle_vs

/**
 * VS badge shown between the two fighters.
 */
@Composable
internal fun VsBadge(modifier: Modifier = Modifier) {
    val shape = RetroTheme.shapeTokens.button
    Box(
        modifier = modifier
            .retroHardShadow(
                offsetX = 2.dp,
                offsetY = 2.dp,
                color = RetroTheme.colors.outline,
                shape = shape,
            )
            .clip(shape)
            .background(RetroTheme.colors.surface)
            .border(BorderStroke(RetroTheme.borders.default, RetroTheme.colors.outlineStrong), shape)
            .padding(horizontal = RetroTheme.spacing.lg, vertical = RetroTheme.spacing.sm),
    ) {
        RetroText(
            text = stringResource(Res.string.battle_vs),
            style = RetroTheme.typography.heading,
            color = RetroTheme.colors.error,
        )
    }
}