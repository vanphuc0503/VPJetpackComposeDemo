package com.example.crewjetpackcompose.presentation.ui.splash

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.example.crewjetpackcompose.R
import com.example.crewjetpackcompose.presentation.ui.CrewAppState

@Composable
fun Splash(
    appState: CrewAppState,
    viewModel: SplashViewModel,
) {
    SplashScreen(
        onNavigateHome = appState::navigateToHome
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun SplashScreen(
    onNavigateHome: () -> Unit = {},
) {
    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.logo_animation))
    val logoAnimationState = animateLottieCompositionAsState(composition = composition)

    if (logoAnimationState.isAtEnd && logoAnimationState.isPlaying) {
        LaunchedEffect(true) {
            onNavigateHome.invoke()
        }
    }

    Scaffold(
        modifier = Modifier.fillMaxSize()
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            LottieAnimation(modifier = Modifier.align(Alignment.Center),
                composition = composition,
                progress = { logoAnimationState.progress })
        }
    }
}

@Preview
@Composable
fun Splash_Preview() {
    SplashScreen()
}
