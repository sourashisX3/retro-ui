package com.funapp.retroui.core.config.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.funapp.retroui.core.design.DesignSystemScreen
import com.funapp.retroui.features.auth.presentation.screens.ForgotPasswordScreen
import com.funapp.retroui.features.auth.presentation.screens.LoginScreen
import com.funapp.retroui.features.auth.presentation.screens.RegisterScreen
import com.funapp.retroui.features.battle.presentation.screens.BattleScreen
import com.funapp.retroui.features.collection.presentation.screens.CollectionScreen
import com.funapp.retroui.features.deck.presentation.screens.DeckBuilderScreen
import com.funapp.retroui.features.home.presentation.screens.HomeScreen
import com.funapp.retroui.features.onboarding.presentation.OnboardingScreen
import com.funapp.retroui.features.profile.presentation.screens.ProfileScreen
import com.funapp.retroui.features.quests.presentation.screens.QuestsScreen
import com.funapp.retroui.features.settings.presentation.screens.SettingsScreen
import com.funapp.retroui.features.splash.presentation.SplashScreen

/**
 * App-wide typed NavHost. Every destination is a [Route] `@Serializable`
 * type — no string routes. The design-system showcase is the current start
 * destination until the splash screen lands; placeholders keep every route
 * navigable end-to-end.
 */
@Composable
fun AppNavHost(
    navController: NavHostController = rememberNavController(),
) {
    NavHost(
        navController = navController,
        startDestination = Route.DesignSystem,
    ) {
        composable<Route.DesignSystem> {
            DesignSystemScreen()
        }

        composable<Route.Splash> {
            SplashScreen(
                onGoDesignSystem = {
                    navController.navigate(Route.DesignSystem) {
                        popUpTo(Route.Splash) { inclusive = true }
                    }
                },
            )
        }

        composable<Route.Onboarding> {
            OnboardingScreen(
                onGoHome = {
                    navController.navigate(Route.Home) {
                        popUpTo(Route.Onboarding) { inclusive = true }
                    }
                },
                onGoLogin = {
                    navController.navigate(Route.Login) {
                        popUpTo(Route.Onboarding) { inclusive = true }
                    }
                },
            )
        }

        composable<Route.Login> {
            LoginScreen(
                onGoHome = {
                    navController.navigate(Route.Home) {
                        popUpTo(Route.Login) { inclusive = true }
                    }
                },
                onGoRegister = { navController.navigate(Route.Register) },
                onGoForgotPassword = { navController.navigate(Route.ForgotPassword) },
            )
        }

        composable<Route.Register> {
            RegisterScreen(
                onGoHome = {
                    navController.navigate(Route.Home) {
                        popUpTo(Route.Register) { inclusive = true }
                    }
                },
                onGoLogin = { navController.popBackStack() },
            )
        }

        composable<Route.ForgotPassword> {
            ForgotPasswordScreen(
                onGoLogin = { navController.popBackStack() },
            )
        }

        composable<Route.Home> {
            HomeScreen(
                onGoBattle = { navController.navigate(Route.Battle) },
                onGoCollection = { navController.navigate(Route.Collection) },
                onGoQuests = { navController.navigate(Route.Quests) },
                onGoProfile = { navController.navigate(Route.Profile) },
                onGoSettings = { navController.navigate(Route.Settings) },
            )
        }

        composable<Route.Battle> {
            BattleScreen(
                onGoHome = { navController.popBackStack() },
            )
        }

        composable<Route.Collection> {
            CollectionScreen(
                onGoDeckBuilder = { navController.navigate(Route.DeckBuilder) },
                onGoHome = { navController.popBackStack() },
            )
        }

        composable<Route.DeckBuilder> {
            DeckBuilderScreen(
                onGoCollection = { navController.popBackStack() },
                onGoHome = {
                    navController.navigate(Route.Home) {
                        popUpTo(Route.Home) { inclusive = false }
                        launchSingleTop = true
                    }
                },
            )
        }

        composable<Route.Quests> {
            QuestsScreen(
                onGoHome = { navController.popBackStack() },
            )
        }

        composable<Route.Profile> {
            ProfileScreen(
                onGoSettings = { navController.navigate(Route.Settings) },
                onGoHome = { navController.popBackStack() },
            )
        }

        composable<Route.Settings> {
            SettingsScreen(
                onGoDesignSystem = { navController.navigate(Route.DesignSystem) },
                onGoHome = { navController.popBackStack() },
            )
        }
    }
}