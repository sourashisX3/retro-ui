package com.funapp.retroui.core.design.components.surfaces

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.ModalBottomSheetProperties
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.funapp.retroui.core.design.animation.retroEntrance
import com.funapp.retroui.core.design.components.foundation.RetroDivider
import com.funapp.retroui.core.design.components.foundation.RetroText
import com.funapp.retroui.core.design.theme.RetroTheme

/**
 * Retro bottom sheet: raised panel with pixel header, ink divider and an
 * optional drag handle, shown over a dark scrim. Content scrolls when it
 * exceeds the sheet height.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RetroBottomSheet(
    visible: Boolean,
    onDismiss: () -> Unit,
    title: String,
    modifier: Modifier = Modifier,
    content: @Composable ColumnScope.() -> Unit,
) {
    if (!visible) return

    val colors = RetroTheme.colors

    ModalBottomSheet(
        onDismissRequest = onDismiss,
        modifier = modifier,
        containerColor = colors.surfaceRaised,
        contentColor = colors.onSurface,
        scrimColor = Color.Black.copy(alpha = 0.6f),
        shape = RoundedCornerShape(topStart = 6.dp, topEnd = 6.dp),
        tonalElevation = 0.dp,
        dragHandle = { RetroSheetDragHandle() },
        properties = ModalBottomSheetProperties(shouldDismissOnBackPress = true),
    ) {
        RetroText(
            text = title,
            style = RetroTheme.typography.title,
            color = colors.textPrimary,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = RetroTheme.spacing.lg)
                .retroEntrance(delayMillis = 0),
        )
        Spacer(modifier = Modifier.height(RetroTheme.spacing.md))
        RetroDivider(
            modifier = Modifier.padding(horizontal = RetroTheme.spacing.lg),
        )
        Spacer(modifier = Modifier.height(RetroTheme.spacing.md))
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = RetroTheme.spacing.lg)
                .retroEntrance(delayMillis = 60),
            content = content,
        )
        Spacer(modifier = Modifier.height(RetroTheme.spacing.lg))
    }
}

@Composable
private fun RetroSheetDragHandle() {
    Box(
        modifier = Modifier
            .padding(top = RetroTheme.spacing.sm, bottom = RetroTheme.spacing.xs)
            .size(width = 44.dp, height = 5.dp)
            .clip(RoundedCornerShape(2.dp))
            .background(RetroTheme.colors.outlineStrong),
    )
}