package com.funapp.retroui.features.settings.presentation.screens

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.funapp.retroui.core.design.components.controls.RetroButton
import com.funapp.retroui.core.design.components.controls.RetroButtonVariant
import com.funapp.retroui.core.design.components.controls.RetroSwitch
import com.funapp.retroui.core.design.components.feedback.RetroStatusLabel
import com.funapp.retroui.core.design.components.foundation.RetroText
import com.funapp.retroui.core.design.components.surfaces.RetroScreen
import com.funapp.retroui.core.design.components.surfaces.RetroSection
import com.funapp.retroui.core.design.theme.RetroTheme
import com.funapp.retroui.features.settings.presentation.components.SettingRow
import org.jetbrains.compose.resources.stringResource
import retroui.shared.generated.resources.Res
import retroui.shared.generated.resources.btn_log_out
import retroui.shared.generated.resources.screen_settings_subtitle
import retroui.shared.generated.resources.screen_settings_title
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
import retroui.shared.generated.resources.settings_account_title
import retroui.shared.generated.resources.settings_audio_title
import retroui.shared.generated.resources.settings_gameplay_title

/**
 * Settings. Audio, gameplay and account sections with live toggles.
 */
@Composable
fun SettingsScreen(
    onGoHome: () -> Unit,
    modifier: Modifier = Modifier,
) {
    var sound by remember { mutableStateOf(true) }
    var haptics by remember { mutableStateOf(true) }
    var music by remember { mutableStateOf(true) }
    var showEnemyHp by remember { mutableStateOf(true) }
    var quickBattle by remember { mutableStateOf(false) }

    RetroScreen(modifier = modifier) {
        item {
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
                    onClick = onGoHome,
                    modifier = Modifier.fillMaxWidth(),
                )
            }
        }
    }
}