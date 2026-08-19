package com.funapp.retroui.features.settings.presentation.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.funapp.retroui.core.config.theme.ThemeMode
import com.funapp.retroui.core.design.components.controls.RetroButton
import com.funapp.retroui.core.design.components.controls.RetroButtonVariant
import com.funapp.retroui.core.design.components.controls.RetroChip
import com.funapp.retroui.core.design.components.controls.RetroIconButton
import com.funapp.retroui.core.design.components.controls.RetroSwitch
import com.funapp.retroui.core.design.components.feedback.RetroStatusLabel
import com.funapp.retroui.core.design.components.foundation.RetroText
import com.funapp.retroui.core.design.components.surfaces.RetroDialog
import com.funapp.retroui.core.design.components.surfaces.RetroDialogVariant
import com.funapp.retroui.core.design.components.surfaces.RetroScreen
import com.funapp.retroui.core.design.components.surfaces.RetroSection
import com.funapp.retroui.core.design.theme.RetroTheme
import com.funapp.retroui.core.di.LocalAppContainer
import com.funapp.retroui.features.settings.presentation.components.SettingRow
import org.jetbrains.compose.resources.stringResource
import retroui.shared.generated.resources.Res
import retroui.shared.generated.resources.btn_confirm_logout
import retroui.shared.generated.resources.btn_log_out
import retroui.shared.generated.resources.common_back
import retroui.shared.generated.resources.dialog_cancel
import retroui.shared.generated.resources.log_out_confirm_body
import retroui.shared.generated.resources.log_out_confirm_title
import retroui.shared.generated.resources.screen_settings_subtitle
import retroui.shared.generated.resources.screen_settings_title
import retroui.shared.generated.resources.settings_account_title
import retroui.shared.generated.resources.settings_audio_title
import retroui.shared.generated.resources.settings_gameplay_title
import retroui.shared.generated.resources.settings_theme_subtitle
import retroui.shared.generated.resources.settings_theme_title
import retroui.shared.generated.resources.theme_option_dark
import retroui.shared.generated.resources.theme_option_light
import retroui.shared.generated.resources.theme_option_system
import retroui.shared.generated.resources.setting_enemy_hp_subtitle
import retroui.shared.generated.resources.setting_enemy_hp_title
import retroui.shared.generated.resources.setting_haptics_subtitle
import retroui.shared.generated.resources.setting_haptics_title
import retroui.shared.generated.resources.setting_language_title
import retroui.shared.generated.resources.setting_language_value
import retroui.shared.generated.resources.setting_music_subtitle
import retroui.shared.generated.resources.setting_music_title
import retroui.shared.generated.resources.setting_quick_subtitle
import retroui.shared.generated.resources.setting_quick_title
import retroui.shared.generated.resources.setting_sound_subtitle
import retroui.shared.generated.resources.setting_sound_title
import retroui.shared.generated.resources.setting_version_title
import retroui.shared.generated.resources.setting_version_value

private val themeOptions: List<ThemeMode> = ThemeMode.entries

/**
 * Settings. Audio, gameplay and account sections with live toggles.
 */
@Composable
fun SettingsScreen(
    onGoHome: () -> Unit,
    onBack: () -> Unit,
    modifier: Modifier = Modifier,
) {
    var sound by remember { mutableStateOf(true) }
    var haptics by remember { mutableStateOf(true) }
    var music by remember { mutableStateOf(true) }
    var showEnemyHp by remember { mutableStateOf(true) }
    var quickBattle by remember { mutableStateOf(false) }
    var showLogoutDialog by remember { mutableStateOf(false) }
    val settingsRepository = LocalAppContainer.current.settingsRepository
    val themeMode by settingsRepository.themeMode.collectAsState()

    RetroScreen(modifier = modifier) {
        item {
            Row(verticalAlignment = Alignment.CenterVertically) {
                RetroIconButton(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = stringResource(Res.string.common_back),
                    onClick = onBack,
                    containerColor = RetroTheme.colors.surfaceVariant,
                )
                Spacer(modifier = Modifier.width(RetroTheme.spacing.sm))
                Column(modifier = Modifier.weight(1f)) {
                    RetroText(
                        text = stringResource(Res.string.screen_settings_title),
                        style = RetroTheme.typography.heading,
                        color = RetroTheme.colors.textPrimary,
                    )
                    RetroText(
                        text = stringResource(Res.string.screen_settings_subtitle),
                        style = RetroTheme.typography.caption,
                        color = RetroTheme.colors.textMuted,
                    )
                }
            }
        }
        item {
            Spacer(modifier = Modifier.height(RetroTheme.spacing.lg))
        }
        item {
            RetroSection(title = stringResource(Res.string.settings_theme_title)) {
                RetroText(
                    text = stringResource(Res.string.settings_theme_subtitle),
                    style = RetroTheme.typography.caption,
                    color = RetroTheme.colors.textMuted,
                )
                Spacer(modifier = Modifier.height(RetroTheme.spacing.sm))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(RetroTheme.spacing.xs),
                ) {
                    themeOptions.forEach { option ->
                        RetroChip(
                            text = when (option) {
                                ThemeMode.System -> stringResource(Res.string.theme_option_system)
                                ThemeMode.Light -> stringResource(Res.string.theme_option_light)
                                ThemeMode.Dark -> stringResource(Res.string.theme_option_dark)
                            },
                            onClick = { settingsRepository.setThemeMode(option) },
                            selected = themeMode == option,
                            modifier = Modifier.weight(1f),
                        )
                    }
                }
            }
        }
        item {
            Spacer(modifier = Modifier.height(RetroTheme.spacing.lg))
        }
        item {
            RetroSection(title = stringResource(Res.string.settings_audio_title)) {
                SettingRow(
                    title = stringResource(Res.string.setting_sound_title),
                    subtitle = stringResource(Res.string.setting_sound_subtitle),
                    trailing = {
                        RetroSwitch(checked = sound, onCheckedChange = { sound = it })
                    },
                )
                Spacer(modifier = Modifier.height(RetroTheme.spacing.md))
                SettingRow(
                    title = stringResource(Res.string.setting_haptics_title),
                    subtitle = stringResource(Res.string.setting_haptics_subtitle),
                    trailing = {
                        RetroSwitch(checked = haptics, onCheckedChange = { haptics = it })
                    },
                )
                Spacer(modifier = Modifier.height(RetroTheme.spacing.md))
                SettingRow(
                    title = stringResource(Res.string.setting_music_title),
                    subtitle = stringResource(Res.string.setting_music_subtitle),
                    trailing = {
                        RetroSwitch(checked = music, onCheckedChange = { music = it })
                    },
                )
            }
        }
        item {
            Spacer(modifier = Modifier.height(RetroTheme.spacing.lg))
        }
        item {
            RetroSection(title = stringResource(Res.string.settings_gameplay_title)) {
                SettingRow(
                    title = stringResource(Res.string.setting_enemy_hp_title),
                    subtitle = stringResource(Res.string.setting_enemy_hp_subtitle),
                    trailing = {
                        RetroSwitch(checked = showEnemyHp, onCheckedChange = { showEnemyHp = it })
                    },
                )
                Spacer(modifier = Modifier.height(RetroTheme.spacing.md))
                SettingRow(
                    title = stringResource(Res.string.setting_quick_title),
                    subtitle = stringResource(Res.string.setting_quick_subtitle),
                    trailing = {
                        RetroSwitch(checked = quickBattle, onCheckedChange = { quickBattle = it })
                    },
                )
                Spacer(modifier = Modifier.height(RetroTheme.spacing.md))
                SettingRow(
                    title = stringResource(Res.string.setting_language_title),
                    trailing = {
                        RetroStatusLabel(
                            text = stringResource(Res.string.setting_language_value),
                            container = RetroTheme.colors.surfaceVariant,
                        )
                    },
                )
            }
        }
        item {
            Spacer(modifier = Modifier.height(RetroTheme.spacing.lg))
        }
        item {
            RetroSection(title = stringResource(Res.string.settings_account_title)) {
                SettingRow(
                    title = stringResource(Res.string.setting_version_title),
                    trailing = {
                        RetroStatusLabel(
                            text = stringResource(Res.string.setting_version_value),
                            container = RetroTheme.colors.surfaceVariant,
                        )
                    },
                )
                Spacer(modifier = Modifier.height(RetroTheme.spacing.md))
                RetroButton(
                    text = stringResource(Res.string.btn_log_out),
                    variant = RetroButtonVariant.Danger,
                    onClick = { showLogoutDialog = true },
                    modifier = Modifier.fillMaxWidth(),
                )
            }
        }
    }

    RetroDialog(
        visible = showLogoutDialog,
        onDismiss = { showLogoutDialog = false },
        title = stringResource(Res.string.log_out_confirm_title),
        message = stringResource(Res.string.log_out_confirm_body),
        icon = Icons.Filled.ExitToApp,
        variant = RetroDialogVariant.Danger,
        confirmText = stringResource(Res.string.btn_confirm_logout),
        onConfirm = {
            showLogoutDialog = false
            onGoHome()
        },
        dismissText = stringResource(Res.string.dialog_cancel),
    )
}