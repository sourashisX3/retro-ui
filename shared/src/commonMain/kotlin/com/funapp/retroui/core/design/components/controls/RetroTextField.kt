package com.funapp.retroui.core.design.components.controls

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CornerBasedShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import com.funapp.retroui.core.design.components.foundation.RetroText
import com.funapp.retroui.core.design.components.foundation.retroHardShadow
import com.funapp.retroui.core.design.theme.RetroTheme

/**
 * Retro outlined text field — ink border, cream fill, hard shadow.
 */
@Composable
fun RetroTextField(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    label: String? = null,
    placeholder: String? = null,
    leadingIcon: ImageVector? = null,
    enabled: Boolean = true,
    singleLine: Boolean = true,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
) {
    val colors = RetroTheme.colors
    val shape: CornerBasedShape = RetroTheme.shapeTokens.input

    Column(modifier = modifier) {
        if (label != null) {
            RetroText(
                text = label,
                style = RetroTheme.typography.caption,
                color = colors.textSecondary,
                modifier = Modifier.padding(start = RetroTheme.spacing.xs, bottom = RetroTheme.spacing.xxs),
            )
        }
        val contentColor = if (enabled) colors.textPrimary else colors.textMuted
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .retroHardShadow(
                    offsetX = 2.dp,
                    offsetY = 3.dp,
                    color = colors.outline,
                    shape = shape,
                )
                .clip(shape)
                .background(colors.surface)
                .border(BorderStroke(RetroTheme.borders.default, colors.outlineStrong), shape)
                .alpha(if (enabled) 1f else 0.6f)
                .padding(horizontal = RetroTheme.spacing.md),
        ) {
            BasicTextField(
                value = value,
                onValueChange = onValueChange,
                enabled = enabled,
                singleLine = singleLine,
                keyboardOptions = keyboardOptions,
                textStyle = TextStyle(
                    color = contentColor,
                    fontFamily = RetroTheme.typography.body.fontFamily,
                    fontSize = RetroTheme.typography.body.fontSize,
                    lineHeight = RetroTheme.typography.body.lineHeight,
                ),
                cursorBrush = SolidColor(colors.primary),
                decorationBox = { innerTextField ->
                    Row(
                        modifier = Modifier.height(RetroTheme.dimensions.inputHeight),
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        if (leadingIcon != null) {
                            Icon(
                                imageVector = leadingIcon,
                                contentDescription = null,
                                modifier = Modifier
                                    .padding(end = RetroTheme.spacing.sm)
                                    .size(16.dp),
                                tint = colors.textMuted,
                            )
                        }
                        Box(Modifier.weight(1f)) {
                            if (value.isEmpty() && placeholder != null) {
                                Text(
                                    text = placeholder,
                                    style = RetroTheme.typography.body.copy(color = colors.textMuted),
                                )
                            }
                            innerTextField()
                        }
                    }
                },
            )
        }
    }
}