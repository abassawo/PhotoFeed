package com.lindenlabs.photofeed.android.screens.main

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.lindenlabs.photofeed.android.screens.main.search.presentation.SearchScreen
import com.lindenlabs.photofeed.android.screens.main.search.presentation.SearchViewModel

@Composable
fun MainScreen(navController: NavController, viewModel: SearchViewModel) {
    Column(modifier = Modifier.fillMaxWidth()) {
        SearchScreen(viewModel = viewModel, navController)
    }
}