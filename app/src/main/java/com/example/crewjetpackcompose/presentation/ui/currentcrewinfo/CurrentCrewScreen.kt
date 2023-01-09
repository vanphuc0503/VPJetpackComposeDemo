package com.example.crewjetpackcompose.presentation.ui.currentcrewinfo

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.example.crewjetpackcompose.presentation.ui.CrewAppState

@Composable
fun CurrentCrewInfo(
    appState: CrewAppState,
    viewModel: CurrentCrewViewModel,
) {
    CurrentCrewInfoScreen()
}

@Composable
fun CurrentCrewInfoScreen() {
    Box(
        Modifier
            .fillMaxSize()
            .background(Color.Yellow), contentAlignment = Alignment.Center
    ) {
        Text(text = "CurrentCrewInfoScreen")
    }
}

@Preview
@Composable
fun CurrentCrewInfo_Preview() {
    CurrentCrewInfoScreen()
}
