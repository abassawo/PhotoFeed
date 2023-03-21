package com.lindenlabs.photofeed.android.screens.feed.presentation.views


import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.lindenlabs.photofeed.android.screens.feed.FeedScreen
import com.lindenlabs.photofeed.android.screens.feed.presentation.FeedViewModel

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