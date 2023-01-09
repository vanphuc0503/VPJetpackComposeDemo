package com.example.crewjetpackcompose.presentation.ui.allcrewinfo

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.crewjetpackcompose.R
import com.example.crewjetpackcompose.presentation.component.TopBarDefault
import com.example.crewjetpackcompose.presentation.ui.CrewAppState

@Composable
fun AllCrewInfo(
    appState: CrewAppState,
    viewModel: AllCrewInfoViewModel,
) {
    AllCrewInfoScreen(
        onDrawer = { appState.openDrawer() }
    )
}

@SuppressLint("ResourceType")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AllCrewInfoScreen(
    onDrawer: () -> Unit = {},
) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopBarDefault(
                modifier = Modifier.statusBarsPadding(),
                title = "List Crew",
                leftIcon = R.drawable.ic_menu,
                onLeftIconClick = onDrawer
            )
        }
    ) { paddingValues ->
        Box(modifier = Modifier.padding(paddingValues)) {
            LazyColumn {
                items(5) {
                    Text(text = "item $it")
                    Spacer(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(16.dp)
                            .background(Color.Blue)
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun AllCrewInfo_Preview() {
    AllCrewInfoScreen()
}
