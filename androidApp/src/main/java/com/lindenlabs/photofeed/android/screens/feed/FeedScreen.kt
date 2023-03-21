package com.lindenlabs.photofeed.android.screens.feed

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.lindenlabs.photofeed.android.screens.feed.presentation.FeedViewModel
import com.lindenlabs.photofeed.android.screens.search.presentation.views.HorizontalResults

@Composable
internal fun FeedScreen(
    navController: NavHostController,
    viewModel: FeedViewModel
) {
    val viewState = viewModel.viewState.collectAsState()
    when(viewState.value) {
        is FeedScreenContract.ViewState.Content -> {
            val items = (viewState.value as FeedScreenContract.ViewState.Content).feedItems
            LazyColumn {
                items(items) {
                    Box(modifier = Modifier.padding(8.dp)) {
                        Column {
                            Text(text = it.query)
                            HorizontalResults(results = it.results , navController = navController)
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
            Box(modifier = Modifier.fillMaxSize()) {
                CircularProgressIndicator(progress = 0.1f)
            }
        }
    }
}