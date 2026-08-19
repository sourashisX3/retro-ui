package com.funapp.retroui.core.config.navigation

import kotlinx.serialization.Serializable

/**
 * Type-safe navigation destinations for Retro Duel.
 *
 * Every destination is `@Serializable` so navigation-compose generates a
 * route pattern per type — used with `composable<Route.X>()` and
 * `navController.navigate(Route.X)`. No string routes anywhere.
 */
@Serializable
sealed interface Route {

    @Serializable
    data object Splash : Route

    @Serializable
    data object Onboarding : Route

    @Serializable
    data object Login : Route

    @Serializable
    data object Register : Route

    @Serializable
    data object ForgotPassword : Route

    @Serializable
    data object Home : Route

    @Serializable
    data object Battle : Route

    @Serializable
    data object Matchmaking : Route

    @Serializable
    data object Collection : Route

    @Serializable
    data object DeckBuilder : Route

    @Serializable
    data object Quests : Route

    @Serializable
    data object Profile : Route

    @Serializable
    data object Settings : Route
}