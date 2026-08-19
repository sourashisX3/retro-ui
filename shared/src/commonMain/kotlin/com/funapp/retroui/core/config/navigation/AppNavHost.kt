package com.funapp.retroui.core.config.navigation

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
import com.funapp.retroui.core.ui.icons.Trophy
import com.funapp.retroui.core.ui.icons.Star
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
import com.funapp.retroui.core.ui.token.RetroAnimation
import com.funapp.retroui.features.auth.presentation.screens.ForgotPasswordScreen
import com.funapp.retroui.features.auth.presentation.screens.LoginScreen
import com.funapp.retroui.features.auth.presentation.screens.RegisterScreen
import com.funapp.retroui.features.battle.presentation.screens.BattleScreen
import com.funapp.retroui.features.battle.presentation.screens.MatchmakingScreen
import com.funapp.retroui.features.collection.presentation.screens.CollectionScreen
import com.funapp.retroui.features.leaderboard.presentation.screens.LeaderboardScreen
import com.funapp.retroui.features.deck.presentation.screens.DeckBuilderScreen
import com.funapp.retroui.features.home.presentation.screens.HomeScreen
import com.funapp.retroui.features.onboarding.presentation.OnboardingScreen
import com.funapp.retroui.features.profile.presentation.screens.ProfileScreen
import com.funapp.retroui.features.quests.presentation.screens.QuestsScreen
import com.funapp.retroui.features.settings.presentation.screens.SettingsScreen
import com.funapp.retroui.features.splash.presentation.SplashScreen
import com.funapp.retroui.core.data.mock.MockChampion
import com.funapp.retroui.core.data.mock.mockChampionRoster
import com.funapp.retroui.features.battle.data.MockMatchmakingRepository
import org.jetbrains.compose.resources.stringResource
import retroui.shared.generated.resources.Res
import retroui.shared.generated.resources.nav_cards
import retroui.shared.generated.resources.nav_home
import retroui.shared.generated.resources.nav_leaderboard
import retroui.shared.generated.resources.nav_profile
import retroui.shared.generated.resources.nav_quests

/** Tabs shown in the retro bottom bar, mapped to their [Route] types. */
@Composable
private fun bottomBarTabs(): List<Pair<RetroBottomBarItem, Route>> = listOf(
    RetroBottomBarItem(stringResource(Res.string.nav_home), RetroIcons.Home) to Route.Home,
    RetroBottomBarItem(stringResource(Res.string.nav_cards), RetroIcons.Star) to Route.Collection,
    RetroBottomBarItem(stringResource(Res.string.nav_quests), RetroIcons.PlayArrow) to Route.Quests,
    RetroBottomBarItem(stringResource(Res.string.nav_profile), RetroIcons.Person) to Route.Profile,
    RetroBottomBarItem(stringResource(Res.string.nav_leaderboard), RetroIcons.Trophy) to Route.Leaderboard,
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

    val matchmakingRoster = mockChampionRoster()
    val matchmakingRepository = remember { MockMatchmakingRepository(matchmakingRoster) }
    var matchedOpponent by remember { mutableStateOf<MockChampion?>(null) }

    val selectedTabIndex = bottomBarTabs().indexOfFirst { (_, route) ->
        currentDestination?.hasRoute(route::class) == true
    }
    val showBottomBar = selectedTabIndex >= 0

    Box(modifier = Modifier.fillMaxSize()) {
        Column(modifier = Modifier.fillMaxSize()) {
            NavHost(
                navController = navController,
                startDestination = Route.Splash,
                enterTransition = {
                    slideInHorizontally(RetroAnimation.arcadeOffset) { it / 3 } + fadeIn(RetroAnimation.fade)
                },
                exitTransition = {
                    slideOutHorizontally(RetroAnimation.arcadeOffset) { -it / 3 } + fadeOut(RetroAnimation.fade)
                },
                popEnterTransition = {
                    slideInHorizontally(RetroAnimation.arcadeOffset) { -it / 3 } + fadeIn(RetroAnimation.fade)
                },
                popExitTransition = {
                    slideOutHorizontally(RetroAnimation.arcadeOffset) { it / 3 } + fadeOut(RetroAnimation.fade)
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
                        onGoBattle = { navController.navigate(Route.Matchmaking) },
                        onGoCollection = { navController.navigate(Route.Collection) },
                        onGoQuests = { navController.navigate(Route.Quests) },
                        onGoProfile = { navController.navigate(Route.Profile) },
                        onGoSettings = { navController.navigate(Route.Settings) },
                        onGoDeckBuilder = { navController.navigate(Route.DeckBuilder) },
                    )
                }

                composable<Route.Matchmaking> {
                    MatchmakingScreen(
                        repository = matchmakingRepository,
                        onFound = { opponent ->
                            matchedOpponent = opponent
                            navController.navigate(Route.Battle) {
                                popUpTo(Route.Matchmaking) { inclusive = true }
                            }
                        },
                        onCancel = { navController.popBackStack() },
                    )
                }

                composable<Route.Battle> {
                    BattleScreen(
                        opponent = matchedOpponent ?: matchmakingRoster.first(),
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

                composable<Route.Leaderboard> {
                    LeaderboardScreen()
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
            val tabs = bottomBarTabs()
            RetroBottomBar(
                items = tabs.map { it.first },
                selectedIndex = selectedTabIndex,
                onSelect = { index ->
                    if (index == selectedTabIndex) return@RetroBottomBar
                    val route = tabs[index].second
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
