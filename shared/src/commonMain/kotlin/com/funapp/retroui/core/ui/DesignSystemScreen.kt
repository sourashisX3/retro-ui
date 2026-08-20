package com.funapp.retroui.core.ui
import com.funapp.retroui.core.ui.icons.RetroIcons

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import com.funapp.retroui.core.ui.icons.AccountCircle
import com.funapp.retroui.core.ui.icons.Add
import com.funapp.retroui.core.ui.icons.AddCircle
import com.funapp.retroui.core.ui.icons.ArrowBack
import com.funapp.retroui.core.ui.icons.Build
import com.funapp.retroui.core.ui.icons.Card
import com.funapp.retroui.core.ui.icons.Check
import com.funapp.retroui.core.ui.icons.Create
import com.funapp.retroui.core.ui.icons.Crown
import com.funapp.retroui.core.ui.icons.Edit
import com.funapp.retroui.core.ui.icons.ExitToApp
import com.funapp.retroui.core.ui.icons.Eye
import com.funapp.retroui.core.ui.icons.EyeOff
import com.funapp.retroui.core.ui.icons.Face
import com.funapp.retroui.core.ui.icons.Favorite
import com.funapp.retroui.core.ui.icons.Flag
import com.funapp.retroui.core.ui.icons.Gamepad
import com.funapp.retroui.core.ui.icons.Home
import com.funapp.retroui.core.ui.icons.Info
import com.funapp.retroui.core.ui.icons.Joystick
import com.funapp.retroui.core.ui.icons.Lock
import com.funapp.retroui.core.ui.icons.MailOutline
import com.funapp.retroui.core.ui.icons.Notifications
import com.funapp.retroui.core.ui.icons.Person
import com.funapp.retroui.core.ui.icons.PlayArrow
import com.funapp.retroui.core.ui.icons.Search
import com.funapp.retroui.core.ui.icons.Settings
import com.funapp.retroui.core.ui.icons.Shield
import com.funapp.retroui.core.ui.icons.Smile
import com.funapp.retroui.core.ui.icons.Star
import com.funapp.retroui.core.ui.icons.Sword
import com.funapp.retroui.core.ui.icons.ThumbUp
import com.funapp.retroui.core.ui.icons.Trophy
import com.funapp.retroui.core.ui.icons.Warning
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.key
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.funapp.retroui.core.ui.animation.RetroEntranceStyle
import com.funapp.retroui.core.ui.animation.retroEntrance
import com.funapp.retroui.core.ui.animation.retroPopPress
import com.funapp.retroui.core.ui.components.foundation.retroTactilePress
import com.funapp.retroui.core.ui.components.controls.RetroButton
import com.funapp.retroui.core.ui.components.controls.RetroButtonVariant
import com.funapp.retroui.core.ui.components.controls.RetroCheckbox
import com.funapp.retroui.core.ui.components.controls.RetroChip
import com.funapp.retroui.core.ui.components.controls.RetroChipVariant
import com.funapp.retroui.core.ui.components.controls.RetroIconButton
import com.funapp.retroui.core.ui.components.controls.RetroRadio
import com.funapp.retroui.core.ui.components.controls.RetroSwitch
import com.funapp.retroui.core.ui.components.controls.RetroTextField
import com.funapp.retroui.core.ui.components.branding.RetroAppIcon
import com.funapp.retroui.core.ui.components.branding.RetroBolt
import com.funapp.retroui.core.ui.components.branding.RetroDuelLogo
import com.funapp.retroui.core.ui.components.branding.RetroLogoSize
import com.funapp.retroui.core.ui.components.branding.RetroSparkle
import com.funapp.retroui.core.ui.components.branding.RetroStar
import com.funapp.retroui.core.ui.components.feedback.RetroEmptyState
import com.funapp.retroui.core.ui.components.feedback.RetroLoadingIndicator
import com.funapp.retroui.core.ui.components.feedback.RetroProgressBar
import com.funapp.retroui.core.ui.components.feedback.RetroProgressColor
import com.funapp.retroui.core.ui.components.feedback.RetroStatusLabel
import com.funapp.retroui.core.ui.components.feedback.RetroToastController
import com.funapp.retroui.core.ui.components.feedback.RetroToastHost
import com.funapp.retroui.core.ui.components.feedback.RetroToastType
import com.funapp.retroui.core.ui.components.feedback.SpeechBubble
import com.funapp.retroui.core.ui.components.foundation.RetroDivider
import com.funapp.retroui.core.ui.components.foundation.RetroText
import com.funapp.retroui.core.ui.components.foundation.retroHardShadow
import com.funapp.retroui.core.ui.components.game.RetroAvatar
import com.funapp.retroui.core.ui.components.game.RetroBattleLog
import com.funapp.retroui.core.ui.components.game.RetroBattleLogEntry
import com.funapp.retroui.core.ui.components.game.RetroBattleLogType
import com.funapp.retroui.core.ui.components.game.RetroCardRarity
import com.funapp.retroui.core.ui.components.game.RetroCardSlot
import com.funapp.retroui.core.ui.components.game.RetroGameCard
import com.funapp.retroui.core.ui.components.hud.HudStat
import com.funapp.retroui.core.ui.components.hud.StatHud
import com.funapp.retroui.core.ui.components.navigation.RetroBottomBar
import com.funapp.retroui.core.ui.components.navigation.RetroBottomBarItem
import com.funapp.retroui.core.ui.components.surfaces.RetroCard
import com.funapp.retroui.core.ui.components.surfaces.RetroCardHeader
import com.funapp.retroui.core.ui.components.surfaces.RetroCharacterCard
import com.funapp.retroui.core.ui.components.surfaces.RetroDashedGroup
import com.funapp.retroui.core.ui.components.surfaces.RetroDialog
import com.funapp.retroui.core.ui.components.surfaces.RetroDialogVariant
import com.funapp.retroui.core.ui.components.surfaces.RetroPanel
import com.funapp.retroui.core.ui.components.surfaces.RetroScreen
import com.funapp.retroui.core.ui.components.surfaces.RetroSection
import com.funapp.retroui.core.ui.components.surfaces.RetroStatCard
import com.funapp.retroui.core.ui.theme.RetroTheme
import com.funapp.retroui.core.ui.token.RetroMotion
import kotlinx.coroutines.launch

/**
 * Atomic-design levels the showcase is organized by.
 */
enum class AtomicLevel(val label: String, val tagline: String) {
    Tokens("TOKENS", "colors, type, spacing, radius, motion — the raw material"),
    Atoms("ATOMS", "smallest interactive pieces: icons, buttons, fields, switches"),
    Molecules("MOLECULES", "small combos: cards, avatars, chips, progress, labels"),
    Organisms("ORGANISMS", "big structures: panels, HUDs, game cards, dock, dialogs"),
    Templates("TEMPLATES & PAGES", "full screens composed from the kit"),
}

/**
 * Maturity of a component inside the kit.
 */
enum class ComponentStatus(val label: String) {
    Stable("STABLE"),
    Beta("BETA"),
}

/** Semantic version of the design system. */
const val DESIGN_SYSTEM_VERSION = "1.0.0"

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

    RetroScreen(
        contentPadding = PaddingValues(bottom = RetroTheme.spacing.xxl),
    ) {
        item { Header() }

        item { AtomicIntro() }

        item { SectionHeader("Branding") }
        item {
            Column(
                modifier = Modifier.padding(horizontal = RetroTheme.spacing.lg),
                verticalArrangement = Arrangement.spacedBy(RetroTheme.spacing.md),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                RetroDuelLogo()
                RetroDuelLogo(size = RetroLogoSize.Medium, fill = colors.accent, decorated = false)
                RetroDuelLogo(size = RetroLogoSize.Small, fill = colors.secondary, decorated = false)
                Row(
                    horizontalArrangement = Arrangement.spacedBy(RetroTheme.spacing.lg),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    RetroAppIcon(size = RetroTheme.dimensions.avatarXL)
                    RetroAppIcon(
                        size = 56.dp,
                        backgroundColor = colors.secondary,
                        monogramColor = colors.onSecondary,
                    )
                    RetroAppIcon(
                        size = 48.dp,
                        backgroundColor = colors.primary,
                        monogramColor = colors.onPrimary,
                    )
                }
                Row(
                    horizontalArrangement = Arrangement.spacedBy(RetroTheme.spacing.md),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    RetroStar(modifier = Modifier.size(RetroTheme.dimensions.iconLG), color = colors.warning)
                    RetroSparkle(modifier = Modifier.size(RetroTheme.dimensions.iconLG), color = colors.info)
                    RetroBolt(modifier = Modifier.size(RetroTheme.dimensions.iconLG), color = colors.error)
                }
            }
        }

        item { SectionHeader("Colors", level = AtomicLevel.Tokens) }
        item { ColorSwatches() }

        item { SectionHeader("Typography", level = AtomicLevel.Tokens) }
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

        item { SectionHeader("Spacing", level = AtomicLevel.Tokens) }
        item { SpacingTokens() }

        item { SectionHeader("Radius & Elevation", level = AtomicLevel.Tokens) }
        item { RadiusElevationTokens() }

        item { SectionHeader("Motion", level = AtomicLevel.Tokens) }
        item { MotionSection() }

        item { SectionHeader("Buttons", level = AtomicLevel.Atoms, status = ComponentStatus.Stable) }
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
                    RetroButton("SMALL", small = true, leadingIcon = RetroIcons.Add, onClick = {})
                    RetroButton("SMALL", small = true, variant = RetroButtonVariant.Secondary, onClick = {})
                }
            }
        }

        item { SectionHeader("Icon buttons", level = AtomicLevel.Atoms, status = ComponentStatus.Stable) }
        item {
            Row(
                modifier = Modifier.padding(horizontal = RetroTheme.spacing.lg),
                horizontalArrangement = Arrangement.spacedBy(RetroTheme.spacing.sm),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                RetroIconButton(imageVector = RetroIcons.Home, contentDescription = "Home", onClick = {})
                RetroIconButton(
                    imageVector = RetroIcons.Favorite,
                    contentDescription = "Favorite",
                    containerColor = colors.secondaryContainer,
                    tint = colors.onSecondaryContainer,
                    onClick = {},
                )
                RetroIconButton(
                    imageVector = RetroIcons.Star,
                    contentDescription = "Star",
                    containerColor = colors.accentContainer,
                    tint = colors.onAccentContainer,
                    onClick = {},
                )
                RetroIconButton(imageVector = RetroIcons.Search, contentDescription = "Search", enabled = false, onClick = {})
            }
        }

        item { SectionHeader("Icons", level = AtomicLevel.Atoms) }
        item {
            val icons = listOf(
                "Star" to RetroIcons.Star,
                "PlayArrow" to RetroIcons.PlayArrow,
                "Person" to RetroIcons.Person,
                "Check" to RetroIcons.Check,
                "Favorite" to RetroIcons.Favorite,
                "Face" to RetroIcons.Face,
                "ThumbUp" to RetroIcons.ThumbUp,
                "Home" to RetroIcons.Home,
                "Search" to RetroIcons.Search,
                "Lock" to RetroIcons.Lock,
                "MailOutline" to RetroIcons.MailOutline,
                "AccountCircle" to RetroIcons.AccountCircle,
                "Settings" to RetroIcons.Settings,
                "Edit" to RetroIcons.Edit,
                "Build" to RetroIcons.Build,
                "Create" to RetroIcons.Create,
                "ExitToApp" to RetroIcons.ExitToApp,
                "Warning" to RetroIcons.Warning,
                "Add" to RetroIcons.Add,
                "Info" to RetroIcons.Info,
                "Notifications" to RetroIcons.Notifications,
                "AddCircle" to RetroIcons.AddCircle,
                "ArrowBack" to RetroIcons.ArrowBack,
                "Gamepad" to RetroIcons.Gamepad,
                "Trophy" to RetroIcons.Trophy,
                "Shield" to RetroIcons.Shield,
                "Sword" to RetroIcons.Sword,
                "Crown" to RetroIcons.Crown,
                "Card" to RetroIcons.Card,
                "Joystick" to RetroIcons.Joystick,
                "Flag" to RetroIcons.Flag,
                "Eye" to RetroIcons.Eye,
                "EyeOff" to RetroIcons.EyeOff,
                "Smile" to RetroIcons.Smile,
            )
            Column(
                modifier = Modifier.padding(horizontal = RetroTheme.spacing.lg),
                verticalArrangement = Arrangement.spacedBy(RetroTheme.spacing.md),
            ) {
                icons.chunked(7).forEach { row ->
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                    ) {
                        row.forEach { (name, icon) ->
                            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                                Icon(
                                    imageVector = icon,
                                    contentDescription = name,
                                    tint = colors.primary,
                                    modifier = Modifier.size(RetroTheme.dimensions.iconLG),
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
                }
                RetroText(
                    text = "Pixelarticons (MIT) — generated from icons/pixelarticons/ via svg-to-compose",
                    style = RetroTheme.typography.caption,
                    color = colors.textMuted,
                )
            }
        }

        item { SectionHeader("Chips", level = AtomicLevel.Atoms, status = ComponentStatus.Stable) }
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

        item { SectionHeader("Text fields", level = AtomicLevel.Atoms, status = ComponentStatus.Stable) }
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
                    leadingIcon = RetroIcons.Search,
                )
                RetroTextField(
                    value = "Locked",
                    onValueChange = {},
                    label = "Disabled",
                    enabled = false,
                )
            }
        }

        item { SectionHeader("Selection", level = AtomicLevel.Atoms, status = ComponentStatus.Stable) }
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

        item { SectionHeader("Cards", level = AtomicLevel.Molecules, status = ComponentStatus.Stable) }
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
                            HudStat(RetroIcons.Favorite, "150", colors.error, "HP"),
                            HudStat(RetroIcons.Build, "80", colors.secondary, "MP"),
                            HudStat(RetroIcons.Star, "200", colors.accent, "XP"),
                        ),
                        spacing = RetroTheme.spacing.md,
                    )
                }
            }
        }

        item { SectionHeader("Dashed group", level = AtomicLevel.Molecules) }
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

        item { SectionHeader("Progress bars", level = AtomicLevel.Atoms, status = ComponentStatus.Stable) }
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

        item { SectionHeader("Stat HUD", level = AtomicLevel.Organisms, status = ComponentStatus.Stable) }
        item {
            StatHud(
                stats = listOf(
                    HudStat(RetroIcons.Favorite, "150", colors.error, "HP"),
                    HudStat(RetroIcons.Build, "80", colors.secondary, "MP"),
                    HudStat(RetroIcons.Star, "200", colors.accent, "XP"),
                ),
                modifier = Modifier.padding(horizontal = RetroTheme.spacing.lg),
            )
        }

        item { SectionHeader("Status labels", level = AtomicLevel.Atoms, status = ComponentStatus.Stable) }
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

        item { SectionHeader("Speech bubble", level = AtomicLevel.Molecules) }
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

        item { SectionHeader("Avatars", level = AtomicLevel.Molecules, status = ComponentStatus.Stable) }
        item {
            Row(
                modifier = Modifier.padding(horizontal = RetroTheme.spacing.lg),
                horizontalArrangement = Arrangement.spacedBy(RetroTheme.spacing.md),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                RetroAvatar(icon = RetroIcons.Star, size = RetroTheme.dimensions.avatarLG)
                RetroAvatar(icon = RetroIcons.PlayArrow, size = RetroTheme.dimensions.avatarMD, borderColor = colors.error)
                RetroAvatar(icon = RetroIcons.Favorite, size = RetroTheme.dimensions.avatarMD, circle = true)
                RetroAvatar(icon = null, size = RetroTheme.dimensions.avatarSM, backgroundColor = colors.surfaceMuted)
            }
        }

        item { SectionHeader("Game cards", level = AtomicLevel.Organisms, status = ComponentStatus.Stable) }
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
                        artworkIcon = listOf(
                            RetroIcons.Favorite,
                            RetroIcons.Star,
                            RetroIcons.PlayArrow,
                            RetroIcons.Face,
                        )[index],
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

        item { SectionHeader("Card slots", level = AtomicLevel.Organisms) }
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

        item { SectionHeader("Stat cards", level = AtomicLevel.Molecules, status = ComponentStatus.Stable) }
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

        item { SectionHeader("Character card", level = AtomicLevel.Molecules, status = ComponentStatus.Stable) }
        item {
            RetroCharacterCard(
                name = "Sir Pixel",
                level = "12",
                avatarIcon = RetroIcons.Favorite,
                avatarColor = colors.surfaceVariant,
                hp = 0.66f,
                hpText = "132/200",
                modifier = Modifier.padding(horizontal = RetroTheme.spacing.lg),
            )
        }

        item { SectionHeader("Dashed group", level = AtomicLevel.Molecules) }
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

        item { SectionHeader("Panels & sections", level = AtomicLevel.Organisms, status = ComponentStatus.Stable) }
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

        item { SectionHeader("Battle log", level = AtomicLevel.Molecules, status = ComponentStatus.Stable) }
        item {
            RetroBattleLog(
                modifier = Modifier.padding(horizontal = RetroTheme.spacing.lg),
                height = 140.dp,
                entries = listOf(
                    RetroBattleLogEntry(
                        text = "Fire Punch — 25 damage",
                        type = RetroBattleLogType.Damage,
                        icon = RetroIcons.PlayArrow,
                    ),
                    RetroBattleLogEntry(
                        text = "Iron Guard — +30 shield",
                        type = RetroBattleLogType.Shield,
                        icon = RetroIcons.Star,
                    ),
                    RetroBattleLogEntry(
                        text = "Healing Potion — +50 HP",
                        type = RetroBattleLogType.Heal,
                        icon = RetroIcons.Favorite,
                    ),
                    RetroBattleLogEntry(
                        text = "Turn 3 — Player ready",
                        type = RetroBattleLogType.System,
                        icon = RetroIcons.Info,
                    ),
                ),
            )
        }

        item { SectionHeader("Loading & empty states", level = AtomicLevel.Molecules, status = ComponentStatus.Stable) }
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

        item { SectionHeader("Dividers", level = AtomicLevel.Atoms) }
        item {
            Column(modifier = Modifier.padding(horizontal = RetroTheme.spacing.lg)) {
                RetroDivider()
                Spacer(modifier = Modifier.height(RetroTheme.spacing.md))
                RetroDivider(thickness = 3.dp)
            }
        }

        item { SectionHeader("Bottom bar", level = AtomicLevel.Organisms, status = ComponentStatus.Stable) }
        item { BottomBarDemo() }

        item { SectionHeader("Dialog", level = AtomicLevel.Organisms, status = ComponentStatus.Stable) }
        item { DialogDemo() }

        item { SectionHeader("Toast", level = AtomicLevel.Organisms, status = ComponentStatus.Stable) }
        item { ToastDemo() }

        item { SectionHeader("Templates & pages", level = AtomicLevel.Templates) }
        item { TemplatesOverview() }
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
            "The full arcade kit behind DECKRON — shared across Android, iOS, Web & Desktop.",
            style = RetroTheme.typography.caption,
            color = colors.textMuted,
            modifier = Modifier.padding(top = RetroTheme.spacing.sm),
        )
        Row(
            modifier = Modifier.padding(top = RetroTheme.spacing.md),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            RetroStatusLabel(
                text = "v$DESIGN_SYSTEM_VERSION",
                container = colors.surfaceVariant,
                modifier = Modifier.weight(1f),
            )
            Spacer(modifier = Modifier.width(RetroTheme.spacing.sm))
            RetroStatusLabel(
                text = "4 platforms",
                container = colors.surfaceVariant,
                modifier = Modifier.weight(1f),
            )
        }
    }
    RetroDivider(thickness = 3.dp)
}

@Composable
private fun AtomicIntro() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(RetroTheme.colors.surfaceMuted)
            .padding(
                start = RetroTheme.spacing.lg,
                end = RetroTheme.spacing.lg,
                top = RetroTheme.spacing.md,
                bottom = RetroTheme.spacing.md,
            ),
        verticalArrangement = Arrangement.spacedBy(RetroTheme.spacing.sm),
    ) {
        RetroText("ORGANIZED BY ATOMIC DESIGN", style = RetroTheme.typography.caption, color = RetroTheme.colors.textMuted)
        AtomicLevel.entries.forEach { level ->
            Row {
                RetroText(level.label, style = RetroTheme.typography.caption, color = RetroTheme.colors.primary)
                RetroText(
                    "  ·  ${level.tagline}",
                    style = RetroTheme.typography.caption,
                    color = RetroTheme.colors.textSecondary,
                )
            }
        }
        RetroText(
            "STABLE = in production · BETA = evolving",
            style = RetroTheme.typography.caption,
            color = RetroTheme.colors.textMuted,
        )
    }
}

@Composable
private fun SectionHeader(title: String, level: AtomicLevel? = null, status: ComponentStatus? = null) {
    val colors = RetroTheme.colors
    Row(
        modifier = Modifier.padding(
            start = RetroTheme.spacing.lg,
            end = RetroTheme.spacing.lg,
            top = RetroTheme.spacing.xxl,
            bottom = RetroTheme.spacing.md,
        ),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Column(modifier = Modifier.weight(1f)) {
            if (level != null) {
                RetroText(
                    text = level.label,
                    style = RetroTheme.typography.caption,
                    color = colors.textMuted,
                )
            }
            RetroText(
                text = title,
                style = RetroTheme.typography.title,
                color = colors.primary,
            )
        }
        if (status != null) {
            RetroStatusLabel(
                text = status.label,
                container = colors.surfaceVariant,
                dotColor = if (status == ComponentStatus.Stable) colors.success else colors.warning,
            )
        }
    }
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
        "decorPrimary" to colors.decorPrimary,
        "decorSecondary" to colors.decorSecondary,
        "decorAccent" to colors.decorAccent,
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

@Composable
private fun MotionSection() {
    val colors = RetroTheme.colors
    val shape = RetroTheme.shapeTokens.button

    Column(
        modifier = Modifier.padding(horizontal = RetroTheme.spacing.lg),
        verticalArrangement = Arrangement.spacedBy(RetroTheme.spacing.md),
    ) {
        Row(horizontalArrangement = Arrangement.spacedBy(RetroTheme.spacing.sm)) {
            RetroStatusLabel(text = "FAST ${RetroMotion.FastMs}ms", container = colors.surfaceVariant)
            RetroStatusLabel(text = "NORMAL ${RetroMotion.NormalMs}ms", container = colors.surfaceVariant)
            RetroStatusLabel(text = "SLOW ${RetroMotion.SlowMs}ms", container = colors.surfaceVariant)
            RetroStatusLabel(text = "EXPRESSIVE ${RetroMotion.ExpressiveMs}ms", container = colors.surfaceVariant)
        }

        RetroText(
            text = "ENTRANCE STYLES",
            style = RetroTheme.typography.caption,
            color = colors.textSecondary,
        )
        var entranceStyle by remember { mutableStateOf(RetroEntranceStyle.Pop) }
        var replay by remember { mutableIntStateOf(0) }
        Row(
            modifier = Modifier.horizontalScroll(rememberScrollState()),
            horizontalArrangement = Arrangement.spacedBy(RetroTheme.spacing.xs),
        ) {
            RetroEntranceStyle.entries.forEach { style ->
                RetroChip(
                    text = style.name.uppercase(),
                    onClick = { entranceStyle = style },
                    selected = entranceStyle == style,
                )
            }
        }
        RetroButton(text = "REPLAY", small = true, onClick = { replay++ })
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(120.dp)
                .background(colors.surfaceMuted, shape)
                .border(2.dp, colors.outlineStrong, shape),
            contentAlignment = Alignment.Center,
        ) {
            key(replay) {
                Box(
                    modifier = Modifier
                        .size(width = 96.dp, height = 64.dp)
                        .retroEntrance(style = entranceStyle)
                        .background(colors.primary, shape)
                        .border(2.dp, colors.outlineStrong, shape),
                )
            }
        }

        RetroText(
            text = "PRESS FEEDBACK",
            style = RetroTheme.typography.caption,
            color = colors.textSecondary,
        )
        Row(horizontalArrangement = Arrangement.spacedBy(RetroTheme.spacing.lg)) {
            val tactileSource = remember { MutableInteractionSource() }
            Box(
                modifier = Modifier
                    .size(width = 112.dp, height = 56.dp)
                    .retroTactilePress(tactileSource, shape, colors.shadow)
                    .background(colors.primary, shape)
                    .border(2.dp, colors.outlineStrong, shape),
                contentAlignment = Alignment.Center,
            ) {
                RetroText("TACTILE", style = RetroTheme.typography.caption, color = colors.onPrimary)
            }
            val popSource = remember { MutableInteractionSource() }
            Box(
                modifier = Modifier
                    .size(width = 112.dp, height = 56.dp)
                    .retroPopPress(popSource)
                    .background(colors.secondary, shape)
                    .border(2.dp, colors.outlineStrong, shape),
                contentAlignment = Alignment.Center,
            ) {
                RetroText("POP", style = RetroTheme.typography.caption, color = colors.onSecondary)
            }
        }

        RetroText(
            text = "press · pop · bounce · shake · slide · fade · flip · cardReveal · liquid · arcade",
            style = RetroTheme.typography.caption,
            color = colors.textMuted,
        )
    }
}

@Composable
private fun BottomBarDemo() {
    var selected by remember { mutableIntStateOf(0) }
    Column(modifier = Modifier.padding(horizontal = RetroTheme.spacing.lg)) {
        RetroBottomBar(
            items = listOf(
                RetroBottomBarItem("HOME", RetroIcons.Home),
                RetroBottomBarItem("CARDS", RetroIcons.Star),
                RetroBottomBarItem("QUESTS", RetroIcons.PlayArrow),
            ),
            selectedIndex = selected,
            onSelect = { selected = it },
        )
    }
}

@Composable
private fun DialogDemo() {
    var showDialog by remember { mutableStateOf(false) }
    Column(
        modifier = Modifier.padding(horizontal = RetroTheme.spacing.lg),
        verticalArrangement = Arrangement.spacedBy(RetroTheme.spacing.sm),
    ) {
        RetroButton(text = "OPEN DIALOG", onClick = { showDialog = true })
        RetroDialog(
            visible = showDialog,
            onDismiss = { showDialog = false },
            title = "RESTART MATCH?",
            message = "Your progress will be lost. Are you sure?",
            icon = RetroIcons.Warning,
            variant = RetroDialogVariant.Danger,
            confirmText = "YES",
            onConfirm = { showDialog = false },
            dismissText = "NO",
        )
    }
}

@Composable
private fun ToastDemo() {
    val scope = rememberCoroutineScope()
    val toastController = remember(scope) { RetroToastController(scope) }
    Column(
        modifier = Modifier.padding(horizontal = RetroTheme.spacing.lg),
        verticalArrangement = Arrangement.spacedBy(RetroTheme.spacing.sm),
    ) {
        RetroButton(text = "SUCCESS TOAST", onClick = { toastController.show("Deck saved!", type = RetroToastType.Success) })
        RetroButton(
            text = "ERROR TOAST",
            variant = RetroButtonVariant.Danger,
            onClick = { toastController.show("Out of energy!", type = RetroToastType.Error) },
        )
        RetroButton(
            text = "INFO TOAST",
            variant = RetroButtonVariant.Secondary,
            onClick = { toastController.show("Matchmaking…", type = RetroToastType.Info) },
        )
        RetroToastHost(controller = toastController)
    }
}

@Composable
private fun TemplatesOverview() {
    val colors = RetroTheme.colors
    Column(
        modifier = Modifier.padding(horizontal = RetroTheme.spacing.lg),
        verticalArrangement = Arrangement.spacedBy(RetroTheme.spacing.sm),
    ) {
        RetroCard {
            Column(
                modifier = Modifier.padding(RetroTheme.spacing.md),
                verticalArrangement = Arrangement.spacedBy(RetroTheme.spacing.sm),
            ) {
                RetroText("HOME — Hero, status rail, quick battle, card shelf", style = RetroTheme.typography.caption, color = colors.textPrimary)
                RetroText("COLLECTION — Greedy card grid, search, sort, gyro tilt", style = RetroTheme.typography.caption, color = colors.textPrimary)
                RetroText("QUEST LOG — Progress bars, reward chips, empty states", style = RetroTheme.typography.caption, color = colors.textPrimary)
                RetroText("BATTLE — Stat HUDs, battle log, speech bubbles, dialogs", style = RetroTheme.typography.caption, color = colors.textPrimary)
                RetroText("SETTINGS — Selection rows, switches, sound/haptics toggles", style = RetroTheme.typography.caption, color = colors.textPrimary)
            }
        }
        RetroText(
            "Every screen in DECKRON is assembled from the tokens, atoms and molecules above.",
            style = RetroTheme.typography.caption,
            color = colors.textMuted,
        )
    }
}
