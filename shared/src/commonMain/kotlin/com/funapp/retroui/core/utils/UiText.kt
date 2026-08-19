package com.funapp.retroui.core.utils

import androidx.compose.runtime.Composable
import org.jetbrains.compose.resources.StringResource
import org.jetbrains.compose.resources.stringResource

/**
 * Text abstraction used across the UI layer.
 *
 * Strings live in the shared `composeResources/values/strings.xml` and are
 * referenced through [ResId]; [Dynamic] is reserved for runtime-only values
 * (player names, numbers, scores) that can never be localized statically.
 *
 * Rule: UI must never hold raw string literals for user-facing copy — go
 * through [UiText] so the common-resources folder stays the single source
 * of truth and future localization is trivial.
 */
sealed interface UiText {
    data class Dynamic(val value: String) : UiText
    data class ResId(val resource: StringResource) : UiText
}

/** Resolves [UiText] to the localized [String]. Only valid in composition. */
@Composable
fun UiText.asString(): String = when (this) {
    is UiText.Dynamic -> value
    is UiText.ResId -> stringResource(resource)
}
