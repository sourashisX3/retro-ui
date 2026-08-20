package com.funapp.retroui.core.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.funapp.retroui.core.ui.components.controls.RetroButton
import com.funapp.retroui.core.ui.components.controls.RetroButtonVariant
import com.funapp.retroui.core.ui.components.controls.RetroCheckbox
import com.funapp.retroui.core.ui.components.controls.RetroChip
import com.funapp.retroui.core.ui.components.controls.RetroChipVariant
import com.funapp.retroui.core.ui.components.controls.RetroIconButton
import com.funapp.retroui.core.ui.components.controls.RetroRadio
import com.funapp.retroui.core.ui.components.controls.RetroSwitch
import com.funapp.retroui.core.ui.components.controls.RetroTextField
import com.funapp.retroui.core.ui.components.feedback.RetroProgressBar
import com.funapp.retroui.core.ui.components.feedback.RetroProgressColor
import com.funapp.retroui.core.ui.components.feedback.RetroStatusLabel
import com.funapp.retroui.core.ui.components.feedback.RetroToastController
import com.funapp.retroui.core.ui.components.feedback.RetroToastHost
import com.funapp.retroui.core.ui.components.game.RetroAvatar
import com.funapp.retroui.core.ui.components.game.RetroCardRarity
import com.funapp.retroui.core.ui.components.game.RetroGameCard
import com.funapp.retroui.core.ui.components.hud.HudStat
import com.funapp.retroui.core.ui.components.hud.StatHud
import com.funapp.retroui.core.ui.components.navigation.RetroBottomBar
import com.funapp.retroui.core.ui.components.navigation.RetroBottomBarItem
import com.funapp.retroui.core.ui.components.surfaces.RetroCard
import com.funapp.retroui.core.ui.icons.Check
import com.funapp.retroui.core.ui.icons.Home
import com.funapp.retroui.core.ui.icons.Info
import com.funapp.retroui.core.ui.icons.PlayArrow
import com.funapp.retroui.core.ui.icons.RetroIcons
import com.funapp.retroui.core.ui.icons.Settings
import com.funapp.retroui.core.ui.icons.Star
import com.funapp.retroui.core.ui.icons.Warning
import com.funapp.retroui.core.ui.theme.RetroTheme

/**
 * Studio previews for the kit's flagship components. Every component preview
 * is wrapped in [RetroTheme] so token colors resolve; previews work in
 * Android Studio on the Android target.
 */

@Preview(name = "RetroButton", widthDp = 240)
@Composable
private fun RetroButtonPreview() {
    RetroTheme {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp),
        ) {
            RetroButton(text = "PRIMARY", onClick = {})
            RetroButton(text = "SECONDARY", variant = RetroButtonVariant.Secondary, onClick = {})
            RetroButton(text = "ACCENT", variant = RetroButtonVariant.Accent, onClick = {})
            RetroButton(text = "DANGER", variant = RetroButtonVariant.Danger, onClick = {})
            RetroButton(text = "OUTLINE", variant = RetroButtonVariant.Outline, onClick = {})
            RetroButton(text = "DISABLED", enabled = false, onClick = {})
        }
    }
}

@Preview(name = "RetroIconButton", widthDp = 160)
@Composable
private fun RetroIconButtonPreview() {
    RetroTheme {
        Row(
            modifier = Modifier.padding(16.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp),
        ) {
            RetroIconButton(imageVector = RetroIcons.Star, contentDescription = "Favorite", onClick = {})
            RetroIconButton(imageVector = RetroIcons.PlayArrow, contentDescription = "Play", onClick = {})
            RetroIconButton(imageVector = RetroIcons.Settings, contentDescription = "Settings", onClick = {})
        }
    }
}

@Preview(name = "RetroChip", widthDp = 260)
@Composable
private fun RetroChipPreview() {
    RetroTheme {
        Row(
            modifier = Modifier.padding(16.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp),
        ) {
            RetroChip(text = "FILLED", onClick = {})
            RetroChip(text = "OUTLINE", variant = RetroChipVariant.Outline, onClick = {})
            RetroChip(text = "SELECTED", selected = true, onClick = {})
        }
    }
}

@Preview(name = "RetroSelection", widthDp = 240)
@Composable
private fun RetroSelectionPreview() {
    RetroTheme {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp),
        ) {
            RetroSwitch(checked = true, onCheckedChange = {})
            RetroSwitch(checked = false, onCheckedChange = {})
            RetroCheckbox(checked = true, onCheckedChange = {}, label = "CHECKED")
            RetroCheckbox(checked = false, onCheckedChange = {}, label = "UNCHECKED")
            RetroRadio(selected = true, onSelect = {}, label = "SELECTED")
            RetroRadio(selected = false, onSelect = {}, label = "UNSELECTED")
        }
    }
}

@Preview(name = "RetroTextField", widthDp = 240)
@Composable
private fun RetroTextFieldPreview() {
    RetroTheme {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp),
        ) {
            RetroTextField(value = "Player", onValueChange = {}, label = "NAME")
            RetroTextField(value = "", onValueChange = {}, label = "EMPTY", placeholder = "Placeholder")
            RetroTextField(value = "Locked", onValueChange = {}, enabled = false)
        }
    }
}

@Preview(name = "RetroCard", widthDp = 240)
@Composable
private fun RetroCardPreview() {
    RetroTheme {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp),
        ) {
            RetroCard {
                Column(modifier = Modifier.padding(16.dp)) {
                    com.funapp.retroui.core.ui.components.foundation.RetroText(
                        text = "CARD TITLE",
                        style = RetroTheme.typography.title,
                        color = RetroTheme.colors.textPrimary,
                    )
                }
            }
            RetroCard {
                Column(modifier = Modifier.padding(16.dp)) {
                    com.funapp.retroui.core.ui.components.foundation.RetroText(
                        text = "SECOND CARD",
                        style = RetroTheme.typography.bodySmall,
                        color = RetroTheme.colors.textSecondary,
                    )
                }
            }
        }
    }
}

@Preview(name = "RetroGameCard", widthDp = 180)
@Composable
private fun RetroGameCardPreview() {
    RetroTheme {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp),
        ) {
            RetroGameCard(
                title = "FLAME KNIGHT",
                cost = "3",
                type = "ATTACK",
                artworkIcon = RetroIcons.Star,
                description = "Deals 5 damage and burns the enemy.",
                rarity = RetroCardRarity.Legendary,
                footer = "ATK 5 · HP 3",
            )
            RetroGameCard(
                title = "ARCANE SLIME",
                cost = "1",
                type = "DEFENSE",
                artworkIcon = RetroIcons.Info,
                rarity = RetroCardRarity.Common,
            )
        }
    }
}

@Preview(name = "RetroProgressBar", widthDp = 240)
@Composable
private fun RetroProgressBarPreview() {
    RetroTheme {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp),
        ) {
            RetroProgressBar(progress = 0.72f, color = RetroProgressColor.Health, segments = 10)
            RetroProgressBar(progress = 0.4f, color = RetroProgressColor.Energy, segments = 5)
            RetroProgressBar(progress = 0.9f, color = RetroProgressColor.Xp, showValue = true)
        }
    }
}

@Preview(name = "RetroAvatar", widthDp = 200)
@Composable
private fun RetroAvatarPreview() {
    RetroTheme {
        Row(
            modifier = Modifier.padding(16.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp),
        ) {
            RetroAvatar(icon = RetroIcons.Star)
            RetroAvatar(icon = RetroIcons.Info, borderColor = RetroTheme.colors.info)
            RetroAvatar(circle = true, icon = RetroIcons.PlayArrow)
            RetroAvatar()
        }
    }
}

@Preview(name = "StatHud", widthDp = 240)
@Composable
private fun StatHudPreview() {
    RetroTheme {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp),
        ) {
            StatHud(
                stats = listOf(
                    HudStat(icon = RetroIcons.PlayArrow, value = "3", tint = RetroTheme.colors.primary),
                    HudStat(icon = RetroIcons.Star, value = "42", tint = RetroTheme.colors.secondary),
                    HudStat(icon = RetroIcons.Warning, value = "1", tint = RetroTheme.colors.error),
                ),
            )
            RetroStatusLabel(text = "STABLE", dotColor = RetroTheme.colors.success)
        }
    }
}

@Preview(name = "RetroBottomBar", widthDp = 320)
@Composable
private fun RetroBottomBarPreview() {
    RetroTheme {
        Column(modifier = Modifier.padding(16.dp)) {
            RetroBottomBar(
                items = listOf(
                    RetroBottomBarItem("HOME", RetroIcons.Home),
                    RetroBottomBarItem("CARDS", RetroIcons.Star),
                    RetroBottomBarItem("QUESTS", RetroIcons.PlayArrow),
                    RetroBottomBarItem("SETTINGS", RetroIcons.Settings),
                ),
                selectedIndex = 1,
                onSelect = {},
            )
        }
    }
}

@Preview(name = "RetroToastHost", widthDp = 280)
@Composable
private fun RetroToastPreview() {
    RetroTheme {
        val scope = rememberCoroutineScope()
        val controller = remember(scope) { RetroToastController(scope) }
        Column(modifier = Modifier.padding(16.dp)) {
            RetroButton(
                text = "SHOW TOAST",
                onClick = { controller.show("Deck saved!", type = com.funapp.retroui.core.ui.components.feedback.RetroToastType.Success) },
            )
            Spacer(modifier = Modifier.height(16.dp))
            RetroToastHost(controller = controller)
        }
    }
}

@Preview(name = "RetroScreen", widthDp = 280)
@Composable
private fun RetroScreenPreview() {
    RetroTheme {
        com.funapp.retroui.core.ui.components.surfaces.RetroScreenStatic {
            Column(
                modifier = Modifier.padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp),
            ) {
                com.funapp.retroui.core.ui.components.foundation.RetroText(
                    text = "SCREEN TITLE",
                    style = RetroTheme.typography.title,
                    color = RetroTheme.colors.primary,
                )
                RetroCard {
                    Column(modifier = Modifier.padding(16.dp)) {
                        com.funapp.retroui.core.ui.components.foundation.RetroText(
                            text = "A full screen template composed from the kit.",
                            style = RetroTheme.typography.bodySmall,
                            color = RetroTheme.colors.textSecondary,
                        )
                    }
                }
                RetroButton(text = "ACTION", onClick = {})
            }
        }
    }
}
