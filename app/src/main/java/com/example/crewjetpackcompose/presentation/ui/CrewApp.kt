package com.example.crewjetpackcompose.presentation.ui

import android.annotation.SuppressLint
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.windowInsetsBottomHeight
import androidx.compose.foundation.layout.windowInsetsTopHeight
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.crewjetpackcompose.BuildConfig
import com.example.crewjetpackcompose.R
import com.example.crewjetpackcompose.presentation.component.NavigationDrawerLabel
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class, ExperimentalAnimationApi::class)
@Composable
fun CrewApp(appState: CrewAppState = rememberCrewAppState()) {
    val systemUiController = rememberSystemUiController()
    val darkIcons = isSystemInDarkTheme()

    val localFocusManager = LocalFocusManager.current

    SideEffect {
        if (!appState.isCustomDarkMode) {
            systemUiController.setSystemBarsColor(color = Color.Transparent, darkIcons = !darkIcons)
        }
    }

    ModalNavigationDrawer(
        drawerState = appState.drawer,
        modifier = Modifier.pointerInput(Unit) {
            detectTapGestures {
                localFocusManager.clearFocus()
            }
        },
        gesturesEnabled = appState.shouldEnableGesture,
        drawerContent = {
            ModalDrawerSheet {
                CrewDrawerContent(selectedItem = appState.drawerItemSelected, onClickAllCrewInfo = {
                    if (!appState.currentDestinationIs(Screen.AllCrewInfo.route)) {
                        appState.navigateToAllCrewInfo()
                    } else {
                        appState.closeDrawer()
                    }
                }, onClickFilterCrew = {
                    if (!appState.currentDestinationIs(Screen.FilterCrew.route)) {
                        appState.navigateToFilterCrew()
                    } else {
                        appState.closeDrawer()
                    }
                })
            }
        },
    ) {
        Scaffold(
            modifier = Modifier.fillMaxSize(),
            containerColor = Color.Transparent,
            contentWindowInsets = WindowInsets(0, 0, 0, 0),
        ) {
            AnimatedNavHost(
                navController = appState.controller,
                startDestination = NestedGraph.SPLASH.route,
            ) {
                splash(appState)

                home(appState)
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ColumnScope.CrewDrawerContent(
    selectedItem: DrawerTab,
    onClickAllCrewInfo: () -> Unit = {},
    onClickFilterCrew: () -> Unit = {},
) {
    Spacer(modifier = Modifier.windowInsetsTopHeight(WindowInsets.statusBars))

    Row(verticalAlignment = Alignment.CenterVertically) {
        Image(
            modifier = Modifier.size(120.dp),
            painter = painterResource(id = R.drawable.ic_launcher_background),
            contentDescription = null,
        )

        Text(
            text = stringResource(id = R.string.app_name),
            style = MaterialTheme.typography.displaySmall,
        )
    }

    NavigationDrawerLabel {
        Text(text = stringResource(id = R.string.tab_feature))
    }

    NavigationDrawerItem(
        icon = {
            Icon(
                painter = painterResource(id = DrawerTab.ALL_CREW_INFO.icon),
                modifier = Modifier.size(24.dp),
                contentDescription = null,
            )
        },
        label = {
            Text(text = stringResource(id = R.string.tab_all_crew_info))
        },
        selected = DrawerTab.ALL_CREW_INFO == selectedItem,
        onClick = onClickAllCrewInfo,
        modifier = Modifier.padding(NavigationDrawerItemDefaults.ItemPadding),
    )

    NavigationDrawerItem(
        icon = {
            Icon(
                painter = painterResource(id = DrawerTab.FILTER_CREW.icon),
                modifier = Modifier.size(24.dp),
                contentDescription = null,
            )
        },
        label = {
            Text(text = stringResource(id = R.string.tab_filter_crew))
        },
        selected = DrawerTab.FILTER_CREW == selectedItem,
        onClick = onClickFilterCrew,
        modifier = Modifier.padding(NavigationDrawerItemDefaults.ItemPadding),
    )

    NavigationDrawerLabel {
        Text(text = stringResource(id = R.string.tab_another))
    }

    Spacer(modifier = Modifier.weight(1f))

    NavigationDrawerLabel {
        Text(text = "Build version: ${BuildConfig.VERSION_NAME}")
    }

    Spacer(modifier = Modifier.windowInsetsBottomHeight(WindowInsets.navigationBars))
}
