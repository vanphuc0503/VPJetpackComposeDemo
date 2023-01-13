package com.example.crewjetpackcompose.presentation.ui.allcrewinfo

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import coil.compose.AsyncImage
import com.example.crewjetpackcompose.R
import com.example.crewjetpackcompose.data.model.Crew
import com.example.crewjetpackcompose.presentation.component.TopBarDefault
import com.example.crewjetpackcompose.presentation.ui.CrewAppState

@OptIn(ExperimentalLifecycleComposeApi::class)
@Composable
fun AllCrewInfo(
    appState: CrewAppState,
    viewModel: AllCrewInfoViewModel,
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    val crews = viewModel.crews?.collectAsLazyPagingItems()

    AllCrewInfoScreen(
        onDrawer = { appState.openDrawer() }, state, crews
    )
}

@SuppressLint("ResourceType")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AllCrewInfoScreen(
    onDrawer: () -> Unit = {},
    state: List<Crew> = emptyList(),
    crews: LazyPagingItems<Crew>?,
) {
    Scaffold(modifier = Modifier.fillMaxSize(), topBar = {
        TopBarDefault(
            modifier = Modifier.statusBarsPadding(),
            title = "List Crew",
            leftIcon = R.drawable.ic_menu,
            onLeftIconClick = onDrawer
        )
    }) { paddingValues ->
        Box(modifier = Modifier.padding(paddingValues)) {
            LazyColumn {
                items(items = crews?.itemSnapshotList ?: emptyList()) {
                    Row(Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
                        AsyncImage(
                            model = it?.image,
                            contentDescription = null,
                            modifier = Modifier
                                .padding(end = 16.dp)
                                .size(56.dp)
                                .clip(CircleShape),
                            contentScale = ContentScale.Crop
                        )
                        Text(text = "Name: ${it?.name}")
                    }
                    Spacer(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(16.dp)
                            .background(Color.Blue)
                    )
                }

                when (val state = crews?.loadState?.refresh) { //FIRST LOAD
                    is LoadState.Error -> {
                        //TODO Error Item
                        //state.error to get error message
                    }
                    is LoadState.Loading -> { // Loading UI
                        item {
                            Column(
                                modifier = Modifier
                                    .fillParentMaxSize(),
                                horizontalAlignment = Alignment.CenterHorizontally,
                                verticalArrangement = Arrangement.Center,
                            ) {
                                Text(
                                    modifier = Modifier
                                        .padding(8.dp),
                                    text = "Refresh Loading"
                                )

                                CircularProgressIndicator(color = Color.Black)
                            }
                        }
                    }
                    else -> {}
                }

                when (val state = crews?.loadState?.append) { // Pagination
                    is LoadState.Error -> {
                        //TODO Pagination Error Item
                        //state.error to get error message
                    }
                    is LoadState.Loading -> { // Pagination Loading UI
                        item {
                            Column(
                                modifier = Modifier
                                    .fillMaxWidth(),
                                horizontalAlignment = Alignment.CenterHorizontally,
                                verticalArrangement = Arrangement.Center,
                            ) {
                                Text(text = "Pagination Loading")

                                CircularProgressIndicator(color = Color.Black)
                            }
                        }
                    }
                    else -> {}
                }
            }
        }
    }
}

@Preview
@Composable
fun AllCrewInfo_Preview() {
//    AllCrewInfoScreen(state = emptyList())
}
