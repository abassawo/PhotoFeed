package com.lindenlabs.photofeed.screens.saved

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.lindenlabs.photofeed.views.HorizontalResults
import presentation.FeedScreenContract

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun FeedScaffold(navController: NavHostController, viewModel: FeedViewModel) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text("Photo Feed")
                }
            )
        }) {
        Column(
            modifier = Modifier
                .padding(it)
                .fillMaxWidth()
        ) {
            FeedScreen(navController = navController, viewModel = viewModel)
        }
    }
}

@Composable
internal fun FeedScreen(
    navController: NavHostController,
    viewModel: FeedViewModel
) {
    val viewState = viewModel.viewState.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.refresh()
    }

    when(viewState.value) {
        is FeedScreenContract.ViewState.Content -> {
            val items = (viewState.value as FeedScreenContract.ViewState.Content).feedItems
            LazyColumn {
                items(items) {
                    Box(modifier = Modifier.padding(8.dp)) {
                        Column {
                            Text(text = it.query)
                            HorizontalResults(results = it.results, navController)
                        }
                    }
                }
            }
        }
        FeedScreenContract.ViewState.EmptyState -> {
            Box(modifier = Modifier.fillMaxSize().animateContentSize() ) {
                Text("No items saved yet")
            }
        }
        is FeedScreenContract.ViewState.Error -> {

        }
        FeedScreenContract.ViewState.Loading -> {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator(progress = 0.1f)
            }
        }
    }
}