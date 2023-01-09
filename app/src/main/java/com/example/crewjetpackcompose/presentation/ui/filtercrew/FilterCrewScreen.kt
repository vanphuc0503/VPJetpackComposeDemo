package com.example.crewjetpackcompose.presentation.ui.filtercrew

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.crewjetpackcompose.R
import com.example.crewjetpackcompose.presentation.component.TopBarDefault
import com.example.crewjetpackcompose.presentation.ui.CrewAppState

@Composable
fun FilterCrewInfo(
    appState: CrewAppState,
    viewModel: FilterCrewViewModel,
) {
    FilterCrewInfoScreen(onDrawer = { appState.openDrawer() })
}

@SuppressLint("ResourceType")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FilterCrewInfoScreen(
    onDrawer: () -> Unit = {},
) {
    Scaffold(modifier = Modifier.fillMaxSize(), topBar = {
        TopBarDefault(
            modifier = Modifier.statusBarsPadding(),
            title = "Filter Screen",
            leftIcon = R.drawable.ic_menu,
            onLeftIconClick = onDrawer
        )
    }) { paddingValues ->
        Box(
            modifier = Modifier.padding(paddingValues), contentAlignment = Alignment.BottomCenter
        ) {
            Text(text = "Filter Screen")
        }
    }
}

@Preview
@Composable
fun FilterCrewInfoScreen_Preview() {
    FilterCrewInfoScreen()
}
