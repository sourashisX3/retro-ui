     package com.funapp.retroui.core.config.navigation

import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import com.funapp.retroui.core.ui.icons.Home
import com.funapp.retroui.core.ui.icons.Person
import com.funapp.retroui.core.ui.icons.PlayArrow
import com.funapp.retroui.core.ui.icons.RetroIcons
import com.funapp.retroui.core.ui.icons.Star
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.funapp.retroui.core.ui.components.navigation.RetroBottomBar
import com.funapp.retroui.core.ui.components.navigation.RetroBottomBarItem
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

/** Tabs shown in the retro bottom bar, mapped to their [Route] types. */
private val bottomBarTabs: List<Pair<RetroBottomBarItem, Route>> = listOf(
    RetroBottomBarItem("HOME", RetroIcons.Home) to Route.Home,
    RetroBottomBarItem("CARDS", RetroIcons.Star) to Route.Collection,
    RetroBottomBarItem("QUESTS", RetroIcons.PlayArrow) to Route.Quests,
    RetroBottomBarItem("PROFILE", RetroIcons.Person) to Route.Profile,
)

/**
 * App-wide typed NavHost. Every destination is a [Route] `@Serializable`
 * type — no string routes.
 *
 * Flow: Splash → Onboarding → Login (auth: Register / ForgotPassword) →
 * main tabs. Every screen is reachable after onboarding; the retro bottom
 * bar appears on the main sections (Home, Collection, Quests, Profile) and
 * is hidden on auth/battle/detail screens.
 */
@Composable
fun AppNavHost(
    navController: NavHostController = rememberNavController(),
) {
    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = backStackEntry?.destination

    val selectedTabIndex = bottomBarTabs.indexOfFirst { (_, route) ->
        currentDestination?.hasRoute(route::class) == true
    }
    val showBottomBar = selectedTabIndex >= 0

    Box(modifier = Modifier.fillMaxSize()) {
        Column(modifier = Modifier.fillMaxSize()) {
            NavHost(
                navController = navController,
                startDestination = Route.Splash,
                enterTransition = {
                    fadeIn(tween(300)) + slideInHorizontally(tween(300)) { it / 8 }
                },
                exitTransition = {
                    fadeOut(tween(200)) + slideOutHorizontally(tween(200)) { -it / 8 }
                },
                popEnterTransition = { fadeIn(tween(300)) },
                popExitTransition = {
                    fadeOut(tween(200)) + slideOutHorizontally(tween(200)) { it / 8 }
                },
                modifier = Modifier
                    .fillMaxSize()
                    .weight(1f),
            ) {
                composable<Route.Splash> {
                    SplashScreen(
                        onFinished = {
                            navController.navigate(Route.Onboarding) {
                                popUpTo(Route.Splash) { inclusive = true }
                            }
                        },
                    )
                }

                composable<Route.Onboarding> {
                    OnboardingScreen(
                        onStart = {
                            navController.navigate(Route.Register) {
                                popUpTo(Route.Onboarding) { inclusive = true }
                            }
                        },
                        onLogin = {
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
                        onGoHome = {
                            navController.navigate(Route.Home) {
                                popUpTo(Route.Login) { inclusive = true }
                                launchSingleTop = true
                            }
                        },
                    )
                }

                composable<Route.Home> {
                    HomeScreen(
                        onGoBattle = { navController.navigate(Route.Battle) },
                        onGoCollection = { navController.navigate(Route.Collection) },
                        onGoQuests = { navController.navigate(Route.Quests) },
                        onGoProfile = { navController.navigate(Route.Profile) },
                        onGoSettings = { navController.navigate(Route.Settings) },
                        onGoDeckBuilder = { navController.navigate(Route.DeckBuilder) },
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
                        onBack = { navController.popBackStack() },
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
                        onGoHome = { navController.popBackStack() },
                        onBack = { navController.popBackStack() },
                    )
                }
            }
        }

        if (showBottomBar) {
            RetroBottomBar(
                items = bottomBarTabs.map { it.first },
                selectedIndex = selectedTabIndex,
                onSelect = { index ->
                    val route = bottomBarTabs[index].second
                    navController.navigate(route) {
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                },
                modifier = Modifier.align(Alignment.BottomCenter),
            )
        }
    }
}
