package com.funapp.retroui.core.design

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Star
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.funapp.retroui.core.design.components.controls.RetroButton
import com.funapp.retroui.core.design.components.controls.RetroButtonVariant
import com.funapp.retroui.core.design.components.controls.RetroCheckbox
import com.funapp.retroui.core.design.components.controls.RetroChip
import com.funapp.retroui.core.design.components.controls.RetroChipVariant
import com.funapp.retroui.core.design.components.controls.RetroIconButton
import com.funapp.retroui.core.design.components.controls.RetroRadio
import com.funapp.retroui.core.design.components.controls.RetroSwitch
import com.funapp.retroui.core.design.components.controls.RetroTextField
import com.funapp.retroui.core.design.components.feedback.RetroEmptyState
import com.funapp.retroui.core.design.components.feedback.RetroLoadingIndicator
import com.funapp.retroui.core.design.components.feedback.RetroProgressBar
import com.funapp.retroui.core.design.components.feedback.RetroProgressColor
import com.funapp.retroui.core.design.components.feedback.RetroStatusLabel
import com.funapp.retroui.core.design.components.feedback.SpeechBubble
import com.funapp.retroui.core.design.components.foundation.RetroDivider
import com.funapp.retroui.core.design.components.foundation.RetroText
import com.funapp.retroui.core.design.components.foundation.retroHardShadow
import com.funapp.retroui.core.design.components.game.RetroAvatar
import com.funapp.retroui.core.design.components.game.RetroBattleLog
import com.funapp.retroui.core.design.components.game.RetroBattleLogEntry
import com.funapp.retroui.core.design.components.game.RetroBattleLogType
import com.funapp.retroui.core.design.components.game.RetroCardRarity
import com.funapp.retroui.core.design.components.game.RetroCardSlot
import com.funapp.retroui.core.design.components.game.RetroGameCard
import com.funapp.retroui.core.design.components.hud.HudStat
import com.funapp.retroui.core.design.components.hud.StatHud
import com.funapp.retroui.core.design.components.surfaces.RetroCard
import com.funapp.retroui.core.design.components.surfaces.RetroCardHeader
import com.funapp.retroui.core.design.components.surfaces.RetroCharacterCard
import com.funapp.retroui.core.design.components.surfaces.RetroDashedGroup
import com.funapp.retroui.core.design.components.surfaces.RetroPanel
import com.funapp.retroui.core.design.components.surfaces.RetroSection
import com.funapp.retroui.core.design.components.surfaces.RetroStatCard
import com.funapp.retroui.core.design.theme.RetroTheme

/**
 * Design-system showcase. Renders every token and component so the whole
 * retro library can be reviewed on one scrollable screen.
 */
@Composable
fun DesignSystemScreen() {
    val colors = RetroTheme.colors

    var text by remember { mutableStateOf("") }
    var search by remember { mutableStateOf("") }
    var switchOn by remember { mutableStateOf(true) }
    var checkboxOn by remember { mutableStateOf(true) }
    var radioChoice by remember { mutableIntStateOf(0) }
    var chipSelected by remember { mutableIntStateOf(0) }
    var health by remember { mutableFloatStateOf(0.66f) }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(colors.background),
        contentPadding = PaddingValues(bottom = RetroTheme.spacing.xxl),
    ) {
        item { Header() }

        item { SectionHeader("Colors") }
        item { ColorSwatches() }

        item { SectionHeader("Typography") }
        item {
            Column(modifier = Modifier.padding(horizontal = RetroTheme.spacing.lg)) {
                TypeRow("Display", RetroTheme.typography.display, colors.textPrimary, "RETRO UI")
                TypeRow("Headline", RetroTheme.typography.headline, colors.textPrimary, "GAME OVER")
                TypeRow("Heading", RetroTheme.typography.heading, colors.textPrimary, "TODAY'S QUEST")
                TypeRow("Title", RetroTheme.typography.title, colors.textPrimary, "Score 1250")
                TypeRow("Score", RetroTheme.typography.score, colors.textPrimary, "01250")
                TypeRow("Label", RetroTheme.typography.label, colors.textPrimary, "PLAY NOW")
                TypeRow("Button", RetroTheme.typography.button, colors.textPrimary, "START BATTLE")
                TypeRow("Body", RetroTheme.typography.body, colors.textSecondary, "Select a player to continue with the tournament.")
                TypeRow("Body small", RetroTheme.typography.bodySmall, colors.textSecondary, "The quick brown fox jumps over the lazy dog.")
                TypeRow("Caption", RetroTheme.typography.caption, colors.textMuted, "Muted helper text")
            }
        }

        item { SectionHeader("Spacing") }
        item { SpacingTokens() }

        item { SectionHeader("Radius & Elevation") }
        item { RadiusElevationTokens() }

        item { SectionHeader("Buttons") }
        item {
            Column(
                modifier = Modifier.padding(horizontal = RetroTheme.spacing.lg),
                verticalArrangement = Arrangement.spacedBy(RetroTheme.spacing.sm),
            ) {
                RetroButton("PLAY", onClick = {})
                RetroButton("JUMP", variant = RetroButtonVariant.Secondary, onClick = {})
                RetroButton("MAGIC", variant = RetroButtonVariant.Accent, onClick = {})
                RetroButton("ATTACK", variant = RetroButtonVariant.Danger, onClick = {})
                RetroButton("OUTLINE", variant = RetroButtonVariant.Outline, onClick = {})
                RetroButton("LOADING", loading = true, onClick = {})
                RetroButton("DISABLED", enabled = false, onClick = {})
                Row(horizontalArrangement = Arrangement.spacedBy(RetroTheme.spacing.sm)) {
                    RetroButton("SMALL", small = true, leadingIcon = Icons.Filled.Add, onClick = {})
                    RetroButton("SMALL", small = true, variant = RetroButtonVariant.Secondary, onClick = {})
                }
            }
        }

        item { SectionHeader("Icon buttons") }
        item {
            Row(
                modifier = Modifier.padding(horizontal = RetroTheme.spacing.lg),
                horizontalArrangement = Arrangement.spacedBy(RetroTheme.spacing.sm),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                RetroIconButton(imageVector = Icons.Filled.Home, contentDescription = "Home", onClick = {})
                RetroIconButton(
                    imageVector = Icons.Filled.Favorite,
                    contentDescription = "Favorite",
                    containerColor = colors.secondaryContainer,
                    tint = colors.onSecondaryContainer,
                    onClick = {},
                )
                RetroIconButton(
                    imageVector = Icons.Filled.Star,
                    contentDescription = "Star",
                    containerColor = colors.accentContainer,
                    tint = colors.onAccentContainer,
                    onClick = {},
                )
                RetroIconButton(imageVector = Icons.Filled.Search, contentDescription = "Search", enabled = false, onClick = {})
            }
        }

        item { SectionHeader("Chips") }
        item {
            Column(
                modifier = Modifier.padding(horizontal = RetroTheme.spacing.lg),
                verticalArrangement = Arrangement.spacedBy(RetroTheme.spacing.sm),
            ) {
                LazyRow(horizontalArrangement = Arrangement.spacedBy(RetroTheme.spacing.sm)) {
                    items(4) { index ->
                        RetroChip(
                            text = listOf("ALL", "HEROES", "ITEMS", "SPELLS")[index],
                            selected = chipSelected == index,
                            onClick = { chipSelected = index },
                        )
                    }
                }
                Row(horizontalArrangement = Arrangement.spacedBy(RetroTheme.spacing.sm)) {
                    RetroChip(text = "OUTLINE", variant = RetroChipVariant.Outline, selected = false, onClick = {})
                    RetroChip(text = "FILLED", variant = RetroChipVariant.Filled, selected = true, onClick = {})
                    RetroChip(text = "DISABLED", selected = false, enabled = false, onClick = {})
                }
            }
        }

        item { SectionHeader("Text fields") }
        item {
            Column(
                modifier = Modifier.padding(horizontal = RetroTheme.spacing.lg),
                verticalArrangement = Arrangement.spacedBy(RetroTheme.spacing.sm),
            ) {
                RetroTextField(
                    value = text,
                    onValueChange = { text = it },
                    label = "Player name",
                    placeholder = "Enter name",
                )
                RetroTextField(
                    value = search,
                    onValueChange = { search = it },
                    label = "Search",
                    placeholder = "Search items...",
                    leadingIcon = Icons.Filled.Search,
                )
                RetroTextField(
                    value = "Locked",
                    onValueChange = {},
                    label = "Disabled",
                    enabled = false,
                )
            }
        }

        item { SectionHeader("Selection") }
        item {
            Column(
                modifier = Modifier.padding(horizontal = RetroTheme.spacing.lg),
                verticalArrangement = Arrangement.spacedBy(RetroTheme.spacing.sm),
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(RetroTheme.spacing.sm),
                ) {
                    RetroSwitch(checked = switchOn, onCheckedChange = { switchOn = it })
                    RetroText("Sound FX", style = RetroTheme.typography.bodySmall, color = colors.textPrimary)
                }
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(RetroTheme.spacing.sm),
                ) {
                    RetroSwitch(checked = false, onCheckedChange = {}, enabled = false)
                    RetroText("Disabled", style = RetroTheme.typography.bodySmall, color = colors.textMuted)
                }
                RetroCheckbox(checked = checkboxOn, onCheckedChange = { checkboxOn = it }, label = "Remember me")
                RetroRadio(selected = radioChoice == 0, onSelect = { radioChoice = 0 }, label = "Warrior")
                RetroRadio(selected = radioChoice == 1, onSelect = { radioChoice = 1 }, label = "Mage")
                RetroRadio(selected = false, onSelect = {}, enabled = false, label = "Locked")
            }
        }

        item { SectionHeader("Cards") }
        item {
            Column(
                modifier = Modifier.padding(horizontal = RetroTheme.spacing.lg),
                verticalArrangement = Arrangement.spacedBy(RetroTheme.spacing.md),
            ) {
                RetroCard {
                    RetroText("Default card", style = RetroTheme.typography.title, color = colors.textPrimary)
                    RetroText(
                        "Cream surface, ink outline, hard offset shadow.",
                        style = RetroTheme.typography.bodySmall,
                        color = colors.textSecondary,
                        modifier = Modifier.padding(top = RetroTheme.spacing.xs),
                    )
                }
                RetroCard(
                    header = {
                        RetroCardHeader(
                            title = "Player card",
                            subtitle = "Level 12 · Warrior",
                            trailing = {
                                RetroStatusLabel(text = "LIVE", dotColor = colors.error)
                            },
                        )
                    },
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Box(
                            modifier = Modifier
                                .size(44.dp)
                                .clip(RoundedCornerShape(RetroTheme.shapes.lg))
                                .background(colors.surfaceMuted)
                                .padding(6.dp),
                            contentAlignment = Alignment.Center,
                        ) {
                            RetroText("??", style = RetroTheme.typography.label, color = colors.textSecondary)
                        }
                        Column(Modifier.padding(start = RetroTheme.spacing.md)) {
                            RetroText("Sir Pixel", style = RetroTheme.typography.title, color = colors.textPrimary)
                            RetroText("STR 15 · DEF 12", style = RetroTheme.typography.caption, color = colors.textMuted)
                        }
                    }
                    RetroDivider(thickness = 1.dp, modifier = Modifier.padding(vertical = RetroTheme.spacing.md))
                    StatHud(
                        stats = listOf(
                            HudStat(Icons.Filled.Favorite, "150", colors.error, "HP"),
                            HudStat(Icons.Filled.Build, "80", colors.secondary, "MP"),
                            HudStat(Icons.Filled.Star, "200", colors.accent, "XP"),
                        ),
                        spacing = RetroTheme.spacing.md,
                    )
                }
            }
        }

        item { SectionHeader("Dashed group") }
        item {
            RetroDashedGroup(
                modifier = Modifier.padding(horizontal = RetroTheme.spacing.lg),
            ) {
                RetroText("SETTINGS PANEL", style = RetroTheme.typography.label, color = colors.textSecondary)
                Spacer(modifier = Modifier.height(RetroTheme.spacing.md))
                RetroSwitch(checked = true, onCheckedChange = {})
                Spacer(modifier = Modifier.height(RetroTheme.spacing.sm))
                RetroText(
                    "Dashed containers group related controls into a game panel.",
                    style = RetroTheme.typography.bodySmall,
                    color = colors.textMuted,
                )
            }
        }

        item { SectionHeader("Progress bars") }
        item {
            Column(
                modifier = Modifier.padding(horizontal = RetroTheme.spacing.lg),
                verticalArrangement = Arrangement.spacedBy(RetroTheme.spacing.md),
            ) {
                RetroText("HEALTH", style = RetroTheme.typography.caption, color = colors.textSecondary)
                RetroProgressBar(progress = health, color = RetroProgressColor.Health, showValue = true)
                RetroText("ENERGY", style = RetroTheme.typography.caption, color = colors.textSecondary)
                RetroProgressBar(progress = 0.4f, color = RetroProgressColor.Energy, segments = 10, showValue = true)
                RetroText("EXPERIENCE", style = RetroTheme.typography.caption, color = colors.textSecondary)
                RetroProgressBar(progress = 0.8f, color = RetroProgressColor.Xp, segments = 20)
                RetroText("DANGER", style = RetroTheme.typography.caption, color = colors.textSecondary)
                RetroProgressBar(progress = 0.15f, color = RetroProgressColor.Danger, showValue = true, valueText = "LOW")
                RetroText("INFO", style = RetroTheme.typography.caption, color = colors.textSecondary)
                RetroProgressBar(progress = 0.55f, color = RetroProgressColor.Info)
            }
        }

        item { SectionHeader("Stat HUD") }
        item {
            StatHud(
                stats = listOf(
                    HudStat(Icons.Filled.Favorite, "150", colors.error, "HP"),
                    HudStat(Icons.Filled.Build, "80", colors.secondary, "MP"),
                    HudStat(Icons.Filled.Star, "200", colors.accent, "XP"),
                ),
                modifier = Modifier.padding(horizontal = RetroTheme.spacing.lg),
            )
        }

        item { SectionHeader("Status labels") }
        item {
            Row(
                modifier = Modifier.padding(horizontal = RetroTheme.spacing.lg),
                horizontalArrangement = Arrangement.spacedBy(RetroTheme.spacing.sm),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                RetroStatusLabel(text = "LIVE", dotColor = colors.error)
                RetroStatusLabel(text = "READY", dotColor = colors.success)
                RetroStatusLabel(text = "NEW", dotColor = colors.info)
                RetroStatusLabel(text = "SAVE", dotColor = colors.warning)
            }
        }

        item { SectionHeader("Speech bubble") }
        item {
            SpeechBubble(
                modifier = Modifier
                    .padding(horizontal = RetroTheme.spacing.lg)
                    .fillMaxWidth(),
            ) {
                RetroText(
                    "Select a player to continue with the tournament!",
                    style = RetroTheme.typography.bodySmall,
                    color = colors.textPrimary,
                )
            }
        }

        item { SectionHeader("Avatars") }
        item {
            Row(
                modifier = Modifier.padding(horizontal = RetroTheme.spacing.lg),
                horizontalArrangement = Arrangement.spacedBy(RetroTheme.spacing.md),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                RetroAvatar(label = "🎮", size = RetroTheme.dimensions.avatarLG)
                RetroAvatar(label = "⚔", size = RetroTheme.dimensions.avatarMD, borderColor = colors.error)
                RetroAvatar(label = "🛡", size = RetroTheme.dimensions.avatarMD, circle = true)
                RetroAvatar(label = "?", size = RetroTheme.dimensions.avatarSM, backgroundColor = colors.surfaceMuted)
            }
        }

        item { SectionHeader("Game cards") }
        item {
            LazyRow(
                modifier = Modifier.padding(horizontal = RetroTheme.spacing.lg),
                horizontalArrangement = Arrangement.spacedBy(RetroTheme.spacing.md),
            ) {
                items(4) { index ->
                    RetroGameCard(
                        title = listOf("FIRE PUNCH", "IRON GUARD", "MEGA BOLT", "PHOENIX RISE")[index],
                        cost = "${index + 1}",
                        type = listOf("ATTACK", "DEFENSE", "ATTACK", "HEAL")[index],
                        artwork = listOf("🔥", "🛡", "⚡", "🐦")[index],
                        description = "Strike with 25 damage.",
                        footer = listOf("DMG 25", "+30 SHIELD", "DMG 40", "+50 HP")[index],
                        rarity = listOf(
                            RetroCardRarity.Common,
                            RetroCardRarity.Rare,
                            RetroCardRarity.Epic,
                            RetroCardRarity.Legendary,
                        )[index],
                        selected = index == 1,
                    )
                }
            }
        }

        item { SectionHeader("Card slots") }
        item {
            Row(
                modifier = Modifier.padding(horizontal = RetroTheme.spacing.lg),
                horizontalArrangement = Arrangement.spacedBy(RetroTheme.spacing.md),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                RetroCardSlot()
                RetroCardSlot(empty = true)
                RetroCardSlot {
                    RetroText("x3", style = RetroTheme.typography.label, color = colors.textPrimary)
                }
            }
        }

        item { SectionHeader("Stat cards") }
        item {
            Row(
                modifier = Modifier.padding(horizontal = RetroTheme.spacing.lg),
                horizontalArrangement = Arrangement.spacedBy(RetroTheme.spacing.md),
            ) {
                RetroStatCard(
                    label = "HP",
                    value = "150",
                    progress = 0.75f,
                    progressColor = RetroProgressColor.Health,
                )
                RetroStatCard(
                    label = "MP",
                    value = "80",
                    progress = 0.4f,
                    progressColor = RetroProgressColor.Energy,
                )
                RetroStatCard(
                    label = "XP",
                    value = "2,450",
                    progress = 0.8f,
                    progressColor = RetroProgressColor.Xp,
                )
            }
        }

        item { SectionHeader("Character card") }
        item {
            RetroCharacterCard(
                name = "Sir Pixel",
                level = "12",
                avatarLabel = "🛡",
                avatarColor = colors.surfaceVariant,
                hp = 0.66f,
                shield = 0.3f,
                hpText = "132/200",
                modifier = Modifier.padding(horizontal = RetroTheme.spacing.lg),
            )
        }

        item { SectionHeader("Dashed group") }
        item {
            RetroDashedGroup(
                modifier = Modifier.padding(horizontal = RetroTheme.spacing.lg),
            ) {
                RetroText("SETTINGS PANEL", style = RetroTheme.typography.label, color = colors.textSecondary)
                Spacer(modifier = Modifier.height(RetroTheme.spacing.md))
                RetroSwitch(checked = true, onCheckedChange = {})
                Spacer(modifier = Modifier.height(RetroTheme.spacing.sm))
                RetroText(
                    "Dashed containers group related controls into a game panel.",
                    style = RetroTheme.typography.bodySmall,
                    color = colors.textMuted,
                )
            }
        }

        item { SectionHeader("Panels & sections") }
        item {
            Column(
                modifier = Modifier.padding(horizontal = RetroTheme.spacing.lg),
                verticalArrangement = Arrangement.spacedBy(RetroTheme.spacing.md),
            ) {
                RetroPanel(title = "TODAY'S QUEST", subtitle = "Win 2 battles") {
                    RetroText(
                        "Deal 100 damage with attack cards.",
                        style = RetroTheme.typography.bodySmall,
                        color = colors.textSecondary,
                    )
                    Spacer(modifier = Modifier.height(RetroTheme.spacing.sm))
                    RetroProgressBar(progress = 0.8f, color = RetroProgressColor.Xp)
                }
                RetroSection(title = "ACHIEVEMENTS") {
                    RetroText(
                        "Completed milestones and rewards.",
                        style = RetroTheme.typography.bodySmall,
                        color = colors.textSecondary,
                    )
                }
            }
        }

        item { SectionHeader("Battle log") }
        item {
            RetroBattleLog(
                modifier = Modifier.padding(horizontal = RetroTheme.spacing.lg),
                height = 140.dp,
                entries = listOf(
                    RetroBattleLogEntry("Fire Punch — 25 damage", RetroBattleLogType.Damage, "⚔"),
                    RetroBattleLogEntry("Iron Guard — +30 shield", RetroBattleLogType.Shield, "🛡"),
                    RetroBattleLogEntry("Healing Potion — +50 HP", RetroBattleLogType.Heal, "🧪"),
                    RetroBattleLogEntry("Turn 3 — Player ready", RetroBattleLogType.System, "▶"),
                ),
            )
        }

        item { SectionHeader("Loading & empty states") }
        item {
            Column(
                modifier = Modifier.padding(horizontal = RetroTheme.spacing.lg),
                verticalArrangement = Arrangement.spacedBy(RetroTheme.spacing.md),
            ) {
                RetroLoadingIndicator()
                RetroEmptyState(
                    title = "NO CARDS YET",
                    subtitle = "Complete battles to earn your first cards.",
                    action = { RetroButton("BATTLE", small = true, onClick = {}) },
                )
            }
        }

        item { SectionHeader("Dividers") }
        item {
            Column(modifier = Modifier.padding(horizontal = RetroTheme.spacing.lg)) {
                RetroDivider()
                Spacer(modifier = Modifier.height(RetroTheme.spacing.md))
                RetroDivider(thickness = 3.dp)
            }
        }
    }
}

@Composable
private fun Header() {
    val colors = RetroTheme.colors
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(colors.surface)
            .padding(RetroTheme.spacing.lg),
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            RetroText("RETRO UI", style = RetroTheme.typography.display, color = colors.primary)
        }
        RetroText(
            "Design system showcase",
            style = RetroTheme.typography.title,
            color = colors.textSecondary,
            modifier = Modifier.padding(top = RetroTheme.spacing.sm),
        )
        RetroText(
            "Tokens · Components · HUD · Arcade",
            style = RetroTheme.typography.caption,
            color = colors.textMuted,
            modifier = Modifier.padding(top = RetroTheme.spacing.sm),
        )
    }
    RetroDivider(thickness = 3.dp)
}

@Composable
private fun SectionHeader(title: String) {
    RetroText(
        text = title,
        style = RetroTheme.typography.title,
        color = RetroTheme.colors.primary,
        modifier = Modifier.padding(
            start = RetroTheme.spacing.lg,
            end = RetroTheme.spacing.lg,
            top = RetroTheme.spacing.xxl,
            bottom = RetroTheme.spacing.md,
        ),
    )
    RetroDivider(horizontalPadding = RetroTheme.spacing.lg)
    Spacer(modifier = Modifier.height(RetroTheme.spacing.md))
}

@Composable
private fun ColorSwatches() {
    val colors = RetroTheme.colors
    val semantic = listOf(
        "background" to colors.background,
        "surface" to colors.surface,
        "surfaceVariant" to colors.surfaceVariant,
        "surfaceMuted" to colors.surfaceMuted,
        "primary" to colors.primary,
        "secondary" to colors.secondary,
        "accent" to colors.accent,
        "error" to colors.error,
        "warning" to colors.warning,
        "info" to colors.info,
        "outline" to colors.outline,
        "textPrimary" to colors.textPrimary,
        "textSecondary" to colors.textSecondary,
        "textMuted" to colors.textMuted,
        "doodlePrimary" to colors.doodlePrimary,
        "doodleSecondary" to colors.doodleSecondary,
        "doodleAccent" to colors.doodleAccent,
    )
    Column(
        modifier = Modifier.padding(horizontal = RetroTheme.spacing.lg),
        verticalArrangement = Arrangement.spacedBy(RetroTheme.spacing.sm),
    ) {
        semantic.chunked(2).forEach { row ->
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(RetroTheme.spacing.sm),
            ) {
                row.forEach { (name, color) ->
                    Swatch(name = name, color = color, modifier = Modifier.weight(1f))
                }
                if (row.size == 1) {
                    Spacer(modifier = Modifier.weight(1f))
                }
            }
        }
    }
}

@Composable
private fun Swatch(name: String, color: Color, modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .height(48.dp)
            .clip(RoundedCornerShape(RetroTheme.shapes.sm))
            .background(color)
            .then(
                Modifier.border(2.dp, RetroTheme.colors.outline, RoundedCornerShape(RetroTheme.shapes.sm)),
            ),
    ) {
        RetroText(
            text = name,
            style = RetroTheme.typography.caption,
            color = findContrastingColor(color),
            modifier = Modifier
                .align(Alignment.BottomStart)
                .padding(RetroTheme.spacing.xs),
        )
    }
}

private fun findContrastingColor(color: Color): Color {
    val lum = 0.299f * color.red + 0.587f * color.green + 0.114f * color.blue
    return if (lum > 0.6f) Color(0xFF171717) else Color(0xFFF8F0DA)
}

@Composable
private fun TypeRow(name: String, style: TextStyle, color: Color, sample: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = RetroTheme.spacing.xs),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        RetroText(
            text = name,
            style = RetroTheme.typography.caption,
            color = RetroTheme.colors.textMuted,
            modifier = Modifier.width(88.dp),
        )
        RetroText(
            text = sample,
            style = style,
            color = color,
            modifier = Modifier.weight(1f),
        )
    }
}

@Composable
private fun SpacingTokens() {
    val spacing = RetroTheme.spacing
    val entries = listOf(
        "xxs" to spacing.xxs,
        "xs" to spacing.xs,
        "sm" to spacing.sm,
        "md" to spacing.md,
        "lg" to spacing.lg,
        "xl" to spacing.xl,
        "xxl" to spacing.xxl,
        "xxxl" to spacing.xxxl,
    )
    Column(
        modifier = Modifier.padding(horizontal = RetroTheme.spacing.lg),
        verticalArrangement = Arrangement.spacedBy(RetroTheme.spacing.sm),
    ) {
        entries.forEach { (name, value) ->
            Row(verticalAlignment = Alignment.CenterVertically) {
                RetroText(
                    text = name,
                    style = RetroTheme.typography.caption,
                    color = RetroTheme.colors.textMuted,
                    modifier = Modifier.width(56.dp),
                )
                Box(
                    modifier = Modifier
                        .width(value)
                        .height(24.dp)
                        .background(RetroTheme.colors.primary)
                        .border(2.dp, RetroTheme.colors.outline),
                )
                RetroText(
                    text = "${value.value.toInt()}dp",
                    style = RetroTheme.typography.caption,
                    color = RetroTheme.colors.textMuted,
                    modifier = Modifier.padding(start = RetroTheme.spacing.sm),
                )
            }
        }
    }
}

@Composable
private fun RadiusElevationTokens() {
    val shapes = RetroTheme.shapes
    val shapeTokens = RetroTheme.shapeTokens
    val colors = RetroTheme.colors

    Column(
        modifier = Modifier.padding(horizontal = RetroTheme.spacing.lg),
        verticalArrangement = Arrangement.spacedBy(RetroTheme.spacing.md),
    ) {
        RetroText("Radius scale", style = RetroTheme.typography.label, color = colors.textSecondary)
        Row(horizontalArrangement = Arrangement.spacedBy(RetroTheme.spacing.md)) {
            listOf("xs" to shapes.xs, "sm" to shapes.sm, "md" to shapes.md, "lg" to shapes.lg, "xl" to shapes.xl)
                .forEach { (name, radius) ->
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Box(
                            modifier = Modifier
                                .size(44.dp)
                                .clip(RoundedCornerShape(radius))
                                .background(colors.primary)
                                .border(2.dp, colors.outline, RoundedCornerShape(radius)),
                        )
                        RetroText(
                            text = name,
                            style = RetroTheme.typography.caption,
                            color = colors.textMuted,
                            modifier = Modifier.padding(top = RetroTheme.spacing.xs),
                        )
                    }
                }
        }

        RetroText("Shape tokens", style = RetroTheme.typography.label, color = colors.textSecondary)
        Row(
            horizontalArrangement = Arrangement.spacedBy(RetroTheme.spacing.md),
            verticalAlignment = Alignment.Bottom,
        ) {
            RetroShapeBox("button", shapeTokens.button, Modifier.size(width = 64.dp, height = 32.dp))
            RetroShapeBox("chip", shapeTokens.chip, Modifier.size(width = 64.dp, height = 32.dp))
            RetroShapeBox("card", shapeTokens.card, Modifier.size(width = 64.dp, height = 40.dp))
            RetroShapeBox("input", shapeTokens.input, Modifier.size(width = 64.dp, height = 40.dp))
        }

        RetroText("Hard shadow", style = RetroTheme.typography.label, color = colors.textSecondary)
        Row(
            horizontalArrangement = Arrangement.spacedBy(RetroTheme.spacing.xl),
            verticalAlignment = Alignment.Bottom,
        ) {
            RetroHardShadowBox(offsetX = 3.dp, offsetY = 4.dp, label = "default")
            RetroHardShadowBox(offsetX = 1.dp, offsetY = 1.dp, label = "pressed")
        }
    }
}

@Composable
private fun RetroShapeBox(
    name: String,
    shape: androidx.compose.foundation.shape.CornerBasedShape,
    modifier: Modifier = Modifier,
) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Box(
            modifier = modifier
                .clip(shape)
                .background(RetroTheme.colors.primary)
                .border(2.dp, RetroTheme.colors.outline, shape),
        )
        RetroText(
            text = name,
            style = RetroTheme.typography.caption,
            color = RetroTheme.colors.textMuted,
            modifier = Modifier.padding(top = RetroTheme.spacing.xs),
        )
    }
}

@Composable
private fun RetroHardShadowBox(offsetX: Dp, offsetY: Dp, label: String) {
    val shape = RetroTheme.shapeTokens.button
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Box(
            modifier = Modifier
                .size(width = 56.dp, height = 32.dp)
                .retroHardShadow(offsetX = offsetX, offsetY = offsetY, shape = shape)
                .clip(shape)
                .background(RetroTheme.colors.surface)
                .border(2.dp, RetroTheme.colors.outlineStrong, shape),
        )
        RetroText(
            text = label,
            style = RetroTheme.typography.caption,
            color = RetroTheme.colors.textMuted,
            modifier = Modifier.padding(top = RetroTheme.spacing.xs),
        )
    }
}