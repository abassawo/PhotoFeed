package com.lindenlabs.photofeed.android.screens.feed

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.lindenlabs.photofeed.android.screens.feed.presentation.FeedViewModel

@Composable
fun FeedScreen(
    navController: NavHostController,
    viewModel: FeedViewModel
) {
    val viewState = viewModel.viewState.collectAsState()
    when(viewState.value) {
        is FeedScreenContract.ViewState.Content -> {

        }
        FeedScreenContract.ViewState.EmptyState -> {
            Box(modifier = Modifier.fillMaxSize()) {
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