package com.funapp.retroui.core.ui.components.surfaces

import androidx.compose.animation.core.Animatable
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CornerBasedShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.funapp.retroui.core.ui.components.controls.RetroButton
import com.funapp.retroui.core.ui.components.controls.RetroButtonVariant
import com.funapp.retroui.core.ui.components.foundation.RetroText
import com.funapp.retroui.core.ui.components.foundation.retroHardShadow
import com.funapp.retroui.core.ui.theme.RetroTheme
import com.funapp.retroui.core.ui.token.RetroAnimation
import kotlinx.coroutines.launch

/** Visual intent of a [RetroDialog]. Drives icon color + confirm button. */
enum class RetroDialogVariant { Info, Danger }

/**
 * Common retro app dialog: scrim + raised panel with hard border/shadow,
 * pixel title, optional icon/message and configurable actions. All text is
 * supplied by the caller (from strings). Prefer this over stock M3 dialogs.
 *
 * When [content] is provided, the dialog renders the title followed by the
 * custom [content] (icon/message/action row are skipped).
 */
@Composable
fun RetroDialog(
    visible: Boolean,
    onDismiss: () -> Unit,
    title: String,
    modifier: Modifier = Modifier,
    message: String? = null,
    icon: ImageVector? = null,
    variant: RetroDialogVariant = RetroDialogVariant.Info,
    confirmText: String? = null,
    onConfirm: (() -> Unit)? = null,
    dismissText: String? = null,
    content: (@Composable ColumnScope.() -> Unit)? = null,
) {
    if (!visible) return

    val colors = RetroTheme.colors
    val shape: CornerBasedShape = RetroTheme.shapeTokens.dialog
    val dialogAlpha = remember { Animatable(0f) }
    val dialogScale = remember { Animatable(0.85f) }

    LaunchedEffect(Unit) {
        launch { dialogAlpha.animateTo(1f, animationSpec = RetroAnimation.fade) }
        dialogScale.animateTo(1f, animationSpec = RetroAnimation.pop)
    }

    val iconContainer = if (variant == RetroDialogVariant.Danger) colors.errorContainer else colors.infoContainer
    val iconTint = if (variant == RetroDialogVariant.Danger) colors.error else colors.info

    Dialog(
        onDismissRequest = onDismiss,
        properties = DialogProperties(usePlatformDefaultWidth = false),
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(RetroTheme.spacing.lg),
            contentAlignment = Alignment.Center,
        ) {
            Column(
                modifier = modifier
                    .width(320.dp)
                    .retroHardShadow(
                        offsetX = 3.dp,
                        offsetY = 5.dp,
                        color = colors.shadow,
                        shape = shape,
                    )
                    .clip(shape)
                    .background(colors.surfaceRaised)
                    .border(BorderStroke(RetroTheme.borders.default, colors.outlineStrong), shape)
                    .padding(RetroTheme.spacing.lg)
                    .graphicsLayer {
                        alpha = dialogAlpha.value
                        scaleX = dialogScale.value
                        scaleY = dialogScale.value
                    },
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                if (icon != null) {
                    Box(
                        modifier = Modifier
                            .size(RetroTheme.dimensions.avatarMD)
                            .clip(RetroTheme.shapeTokens.badge)
                            .background(iconContainer),
                        contentAlignment = Alignment.Center,
                    ) {
                        Icon(
                            imageVector = icon,
                            contentDescription = null,
                            tint = iconTint,
                            modifier = Modifier.size(RetroTheme.dimensions.iconLG),
                        )
                    }
                    Spacer(modifier = Modifier.height(RetroTheme.spacing.md))
                }
                RetroText(
                    text = title,
                    style = RetroTheme.typography.title,
                    color = colors.textPrimary,
                )
                if (content != null) {
                    Spacer(modifier = Modifier.height(RetroTheme.spacing.md))
                    content()
                } else {
                    if (message != null) {
                        Spacer(modifier = Modifier.height(RetroTheme.spacing.sm))
                        RetroText(
                            text = message,
                            style = RetroTheme.typography.bodySmall,
                            color = colors.textMuted,
                        )
                    }
                    Spacer(modifier = Modifier.height(RetroTheme.spacing.lg))
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(RetroTheme.spacing.md),
                    ) {
                        if (dismissText != null) {
                            RetroButton(
                                text = dismissText,
                                variant = RetroButtonVariant.Outline,
                                onClick = onDismiss,
                                small = true,
                                modifier = Modifier
                                    .weight(1f)
                                    .height(RetroTheme.dimensions.buttonHeightSmall),
                            )
                        }
                        if (confirmText != null && onConfirm != null) {
                            RetroButton(
                                text = confirmText,
                                variant = if (variant == RetroDialogVariant.Danger) {
                                    RetroButtonVariant.Danger
                                } else {
                                    RetroButtonVariant.Primary
                                },
                                onClick = onConfirm,
                                small = true,
                                modifier = Modifier
                                    .weight(1f)
                                    .height(RetroTheme.dimensions.buttonHeightSmall),
                            )
                        }
                    }
                }
            }
        }
    }
}
