package com.funapp.retroui.features.settings.presentation.screens
import com.funapp.retroui.core.ui.icons.RetroIcons

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import com.funapp.retroui.core.ui.icons.ArrowBack
import com.funapp.retroui.core.ui.icons.ExitToApp
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.funapp.retroui.core.ui.theme.ThemeMode
import com.funapp.retroui.core.ui.animation.RetroEntranceStyle
import com.funapp.retroui.core.ui.animation.retroEntrance
import com.funapp.retroui.core.ui.components.controls.RetroButton
import com.funapp.retroui.core.ui.components.controls.RetroButtonVariant
import com.funapp.retroui.core.ui.components.controls.RetroChip
import com.funapp.retroui.core.ui.components.controls.RetroIconButton
import com.funapp.retroui.core.ui.components.controls.RetroSwitch
import com.funapp.retroui.core.ui.components.feedback.RetroStatusLabel
import com.funapp.retroui.core.ui.components.foundation.RetroText
import com.funapp.retroui.core.ui.components.surfaces.RetroDialog
import com.funapp.retroui.core.ui.components.surfaces.RetroDialogVariant
import com.funapp.retroui.core.ui.components.surfaces.RetroScreen
import com.funapp.retroui.core.ui.components.surfaces.RetroSection
import com.funapp.retroui.core.ui.theme.RetroTheme
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
import retroui.shared.generated.resources.setting_gyro_tilt_subtitle
import retroui.shared.generated.resources.setting_gyro_tilt_title
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
    val settingsRepository = LocalAppContainer.current.settingsRepository
    val themeMode by settingsRepository.themeMode.collectAsState()
    val soundEnabled by settingsRepository.soundEnabled.collectAsState()
    val hapticsEnabled by settingsRepository.hapticsEnabled.collectAsState()
    val musicEnabled by settingsRepository.musicEnabled.collectAsState()
    val showEnemyHp by settingsRepository.showEnemyHp.collectAsState()
    val quickBattle by settingsRepository.quickBattle.collectAsState()
    val gyroTiltEnabled by settingsRepository.gyroTiltEnabled.collectAsState()
    var showLogoutDialog by remember { mutableStateOf(false) }

    RetroScreen(modifier = modifier) {
        item {
            Row(
                modifier = Modifier.retroEntrance(delayMillis = 0),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                RetroIconButton(
                    imageVector = RetroIcons.ArrowBack,
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
            RetroSection(
                title = stringResource(Res.string.settings_theme_title), modifier = Modifier.retroEntrance(style = RetroEntranceStyle.Pop, delayMillis = 60),
            ) {
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
            RetroSection(
                title = stringResource(Res.string.settings_audio_title),
                modifier = Modifier.retroEntrance(delayMillis = 120),
            ) {
                SettingRow(
                    title = stringResource(Res.string.setting_sound_title),
                    subtitle = stringResource(Res.string.setting_sound_subtitle),
                    trailing = {
                        RetroSwitch(
                            checked = soundEnabled,
                            onCheckedChange = { settingsRepository.setSoundEnabled(it) },
                        )
                    },
                )
                Spacer(modifier = Modifier.height(RetroTheme.spacing.md))
                SettingRow(
                    title = stringResource(Res.string.setting_haptics_title),
                    subtitle = stringResource(Res.string.setting_haptics_subtitle),
                    trailing = {
                        RetroSwitch(
                            checked = hapticsEnabled,
                            onCheckedChange = { settingsRepository.setHapticsEnabled(it) },
                        )
                    },
                )
                Spacer(modifier = Modifier.height(RetroTheme.spacing.md))
                SettingRow(
                    title = stringResource(Res.string.setting_music_title),
                    subtitle = stringResource(Res.string.setting_music_subtitle),
                    trailing = {
                        RetroSwitch(
                            checked = musicEnabled,
                            onCheckedChange = { settingsRepository.setMusicEnabled(it) },
                        )
                    },
                )
            }
        }
        item {
            Spacer(modifier = Modifier.height(RetroTheme.spacing.lg))
        }
        item {
            RetroSection(
                title = stringResource(Res.string.settings_gameplay_title),
                modifier = Modifier.retroEntrance(delayMillis = 180),
            ) {
                SettingRow(
                    title = stringResource(Res.string.setting_enemy_hp_title),
                    subtitle = stringResource(Res.string.setting_enemy_hp_subtitle),
                    trailing = {
                        RetroSwitch(
                            checked = showEnemyHp,
                            onCheckedChange = { settingsRepository.setShowEnemyHp(it) },
                        )
                    },
                )
                Spacer(modifier = Modifier.height(RetroTheme.spacing.md))
                SettingRow(
                    title = stringResource(Res.string.setting_quick_title),
                    subtitle = stringResource(Res.string.setting_quick_subtitle),
                    trailing = {
                        RetroSwitch(
                            checked = quickBattle,
                            onCheckedChange = { settingsRepository.setQuickBattle(it) },
                        )
                    },
                )
                Spacer(modifier = Modifier.height(RetroTheme.spacing.md))
                SettingRow(
                    title = stringResource(Res.string.setting_gyro_tilt_title),
                    subtitle = stringResource(Res.string.setting_gyro_tilt_subtitle),
                    trailing = {
                        RetroSwitch(
                            checked = gyroTiltEnabled,
                            onCheckedChange = { settingsRepository.setGyroTiltEnabled(it) },
                        )
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
            RetroSection(
                title = stringResource(Res.string.settings_account_title),
                modifier = Modifier.retroEntrance(delayMillis = 240),
            ) {
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
        icon = RetroIcons.ExitToApp,
        variant = RetroDialogVariant.Danger,
        confirmText = stringResource(Res.string.btn_confirm_logout),
        onConfirm = {
            showLogoutDialog = false
            onGoHome()
        },
        dismissText = stringResource(Res.string.dialog_cancel),
    )
}
