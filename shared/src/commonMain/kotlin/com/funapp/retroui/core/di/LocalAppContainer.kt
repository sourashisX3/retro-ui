package com.funapp.retroui.core.di

import androidx.compose.runtime.staticCompositionLocalOf

/**
 * CompositionLocal handing the root [AppContainer] to any screen in the
 * graph without threading callbacks through the navigation routes.
 */
val LocalAppContainer = staticCompositionLocalOf<AppContainer> {
    error("LocalAppContainer not provided — App() must provide it.")
}