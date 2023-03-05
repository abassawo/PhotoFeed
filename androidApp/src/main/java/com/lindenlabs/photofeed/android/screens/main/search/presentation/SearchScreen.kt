package com.lindenlabs.photofeed.android.screens.main.search.presentation

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.lindenlabs.photofeed.android.screens.main.search.presentation.views.ResultGrid

@Composable
fun SearchScreen(viewModel: SearchViewModel, navController: NavController) {

    Scaffold { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
        ) {
            when (viewModel.viewState.collectAsState().value) {
                is SearchScreenContract.ViewState.Initial -> InitialViewState(viewModel, navController = navController)
                is SearchScreenContract.ViewState.SearchLoading -> {}
                is SearchScreenContract.ViewState.Error -> {}
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InitialViewState(viewModel: SearchViewModel, navController: NavController) {
    rememberSystemUiController().apply {
        setSystemBarsColor(
            color = Color.Transparent
        )
    }
    val viewState = viewModel.viewState.collectAsState()
    val initialState = (viewState.value as? SearchScreenContract.ViewState.Initial)
        ?: SearchScreenContract.ViewState.Initial()

    with(initialState) {
        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
            SearchBar(
                query = query,
                onQueryChange = { viewModel.updateQuery(it) },
                onSearch = { viewModel.search(it) },
                active = isSearchActive,
                onActiveChange = {},
                modifier = Modifier.fillMaxWidth(),
                enabled = true
            ) {
                ResultGrid(initialState.results, navController)
            }
        }
    }
}
