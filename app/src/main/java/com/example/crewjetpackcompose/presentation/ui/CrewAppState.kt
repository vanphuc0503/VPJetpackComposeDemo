package com.example.crewjetpackcompose.presentation.ui

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.navArgument
import androidx.navigation.navigation
import com.example.crewjetpackcompose.R
import com.example.crewjetpackcompose.presentation.ui.allcrewinfo.AllCrewInfo
import com.example.crewjetpackcompose.presentation.ui.currentcrewinfo.CurrentCrewInfo
import com.example.crewjetpackcompose.presentation.ui.filtercrew.FilterCrewInfo
import com.example.crewjetpackcompose.presentation.ui.splash.Splash
import com.example.crewjetpackcompose.utils.Constants
import com.google.accompanist.navigation.animation.composable
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

sealed class Screen(val route: String) {
    object Splash : Screen("splash")

    object AllCrewInfo : Screen("all_crew_info")

    object CurrentCrewInfo : Screen("current_crew_info")

    object FilterCrew : Screen("filter_crew")
}

enum class NestedGraph(val route: String) {
    HOME(route = "home_nav"), SPLASH(route = "splash_nav")
}

enum class DrawerTab(
    val route: String,
    @StringRes val title: Int,
    @DrawableRes val icon: Int,
) {
    ALL_CREW_INFO(
        route = Screen.AllCrewInfo.route,
        title = R.string.tab_all_crew_info,
        icon = R.drawable.ic_all_crew,
    ),
    FILTER_CREW(
        route = Screen.FilterCrew.route,
        title = R.string.tab_filter_crew,
        icon = R.drawable.ic_filter,
    )
}

@OptIn(ExperimentalAnimationApi::class)
fun NavGraphBuilder.splash(appState: CrewAppState) {
    navigation(
        route = NestedGraph.SPLASH.route, startDestination = Screen.Splash.route
    ) {
        composable(
            route = Screen.Splash.route,
            enterTransition = {
                slideIntoContainer(
                    AnimatedContentScope.SlideDirection.Left, animationSpec = tween(700)
                )
            },
            exitTransition = {
                slideOutOfContainer(
                    AnimatedContentScope.SlideDirection.Left, animationSpec = tween(700)
                )
            },
            popEnterTransition = {
                slideIntoContainer(
                    AnimatedContentScope.SlideDirection.Right, animationSpec = tween(700)
                )
            },
            popExitTransition = {
                slideOutOfContainer(
                    AnimatedContentScope.SlideDirection.Right, animationSpec = tween(700)
                )
            },
        ) {
            Splash(
                appState = appState, viewModel = hiltViewModel()
            )
        }
    }
}

@OptIn(ExperimentalAnimationApi::class)
fun NavGraphBuilder.home(appState: CrewAppState) {
    navigation(
        route = NestedGraph.HOME.route, startDestination = Screen.AllCrewInfo.route
    ) {
        composable(
            route = Screen.AllCrewInfo.route,
            enterTransition = {
                slideIntoContainer(
                    AnimatedContentScope.SlideDirection.Left, animationSpec = tween(700)
                )
            },
            exitTransition = {
                slideOutOfContainer(
                    AnimatedContentScope.SlideDirection.Left, animationSpec = tween(700)
                )
            },
            popEnterTransition = {
                slideIntoContainer(
                    AnimatedContentScope.SlideDirection.Right, animationSpec = tween(700)
                )
            },
            popExitTransition = {
                slideOutOfContainer(
                    AnimatedContentScope.SlideDirection.Right, animationSpec = tween(700)
                )
            },
        ) {
            AllCrewInfo(
                appState = appState,
                viewModel = hiltViewModel(),
            )
        }

        composable(
            route = "${Screen.CurrentCrewInfo.route}?id={${Constants.Key.ID}}",
            arguments = listOf(navArgument(Constants.Key.ID) { type = NavType.StringType }),
            enterTransition = {
                slideIntoContainer(
                    AnimatedContentScope.SlideDirection.Left, animationSpec = tween(700)
                )
            },
            exitTransition = {
                slideOutOfContainer(
                    AnimatedContentScope.SlideDirection.Left, animationSpec = tween(700)
                )
            },
            popEnterTransition = {
                slideIntoContainer(
                    AnimatedContentScope.SlideDirection.Right, animationSpec = tween(700)
                )
            },
            popExitTransition = {
                slideOutOfContainer(
                    AnimatedContentScope.SlideDirection.Right, animationSpec = tween(700)
                )
            },
        ) {
            CurrentCrewInfo(
                appState = appState,
                viewModel = hiltViewModel(),
            )
        }

        composable(
            route = Screen.FilterCrew.route,
            enterTransition = {
                slideIntoContainer(
                    AnimatedContentScope.SlideDirection.Left, animationSpec = tween(700)
                )
            },
            exitTransition = {
                slideOutOfContainer(
                    AnimatedContentScope.SlideDirection.Left, animationSpec = tween(700)
                )
            },
            popEnterTransition = {
                slideIntoContainer(
                    AnimatedContentScope.SlideDirection.Right, animationSpec = tween(700)
                )
            },
            popExitTransition = {
                slideOutOfContainer(
                    AnimatedContentScope.SlideDirection.Right, animationSpec = tween(700)
                )
            },
        ) {
            FilterCrewInfo(
                appState = appState,
                viewModel = hiltViewModel(),
            )
        }
    }
}

@OptIn(ExperimentalAnimationApi::class, ExperimentalMaterial3Api::class)
@Composable
fun rememberCrewAppState(
    coroutineScope: CoroutineScope = rememberCoroutineScope(),
    controller: NavHostController = rememberAnimatedNavController(),
    drawer: DrawerState = rememberDrawerState(initialValue = DrawerValue.Closed),
    snackbarHost: SnackbarHostState = remember { SnackbarHostState() },
): CrewAppState = remember(coroutineScope, controller, snackbarHost) {
    CrewAppState(coroutineScope, controller, drawer, snackbarHost)
}

@OptIn(ExperimentalMaterial3Api::class)
class CrewAppState(
    private val coroutineScope: CoroutineScope,
    val controller: NavHostController,
    val drawer: DrawerState,
    val snackbarHost: SnackbarHostState
) {

    private val drawerTabs = DrawerTab.values()

    val shouldEnableGesture: Boolean
        @Composable get() = controller.currentBackStackEntryAsState().value?.destination?.route in drawerTabs.map { it.route }

    val isCustomDarkMode: Boolean
        get() {
            val listScreenCustomizeDarkMode = listOf(Screen.CurrentCrewInfo.route)
            val route = controller.currentBackStackEntry?.destination?.route
            return route in listScreenCustomizeDarkMode
        }

    val drawerItemSelected: DrawerTab
        @Composable get() {
            val route = controller.currentBackStackEntryAsState().value?.destination?.route
            return drawerTabs.firstOrNull { it.route == route } ?: DrawerTab.ALL_CREW_INFO
        }

    fun currentDestinationIs(route: String): Boolean =
        controller.currentBackStackEntry?.destination?.route == route

    fun closeDrawer() {
        coroutineScope.launch {
            drawer.close()
        }
    }

    fun openDrawer() {
        coroutineScope.launch {
            drawer.open()
        }
    }

    fun navigateToHome() {
        controller.navigate(route = Screen.AllCrewInfo.route) {
            popUpTo(Screen.Splash.route) {
                inclusive = true
            }
        }
    }

    fun navigateToAllCrewInfo() {
        closeDrawer()
        controller.navigate(route = Screen.AllCrewInfo.route) {
            popUpTo(Screen.AllCrewInfo.route) {
                inclusive = true
            }
        }
    }

    fun navigateToCurrentCrewInfo(id: String) {
        closeDrawer()
        controller.navigate(route = "${Screen.CurrentCrewInfo.route}?id=${id}")
    }

    fun navigateToFilterCrew() {
        closeDrawer()
        controller.navigate(route = Screen.FilterCrew.route)
    }

    fun popBackStack(popToRoute: String? = null, params: Map<String, Any>? = null) {
        if (popToRoute == null) {
            params?.forEach { data ->
                controller.previousBackStackEntry?.savedStateHandle?.set(data.key, data.value)
            }
            controller.popBackStack()
        } else {
            params?.forEach { data ->
                controller.getBackStackEntry(popToRoute).savedStateHandle[data.key] = data.value
            }
            controller.popBackStack(route = popToRoute, inclusive = false)
        }
    }
}