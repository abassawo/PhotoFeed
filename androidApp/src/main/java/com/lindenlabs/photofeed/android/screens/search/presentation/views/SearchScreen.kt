package com.lindenlabs.photofeed.android.screens.search.presentation.views

import android.util.Log
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
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.key.onKeyEvent
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.lindenlabs.photofeed.android.screens.search.presentation.SearchScreenContract
import com.lindenlabs.photofeed.android.screens.search.presentation.SearchViewModel

@Composable
internal fun SearchScreen(viewModel: SearchViewModel, navController: NavController) {

    Scaffold { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
        ) {
            when (viewModel.viewState.collectAsState().value) {
                is SearchScreenContract.ViewState.Initial -> InitialViewState(
                    viewModel,
                    navController = navController
                )
                is SearchScreenContract.ViewState.SearchLoading -> {
                    Log.d("SearchScreen", "Loading state")
                }
                is SearchScreenContract.ViewState.Error -> {}
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
@Composable
internal fun InitialViewState(viewModel: SearchViewModel, navController: NavController) {
    rememberSystemUiController().apply {
        setSystemBarsColor(
            color = Color.Transparent
        )
    }
    val viewState = viewModel.viewState.collectAsState()
    val initialState = (viewState.value as? SearchScreenContract.ViewState.Initial)
        ?: SearchScreenContract.ViewState.Initial()
    val keyboard = LocalSoftwareKeyboardController.current

    with(initialState) {
        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
            Row(modifier = Modifier.fillMaxWidth()) {
                SearchBar(
                    query = query,
                    onQueryChange = { viewModel.updateQuery(it) },
                    onSearch = {
                        viewModel.search(it)
                        keyboard?.hide()
                    },
                    active = isSearchActive,
                    onActiveChange = {},
                    modifier = Modifier
                        .onKeyEvent {
                            return@onKeyEvent when  {
                                (it.nativeKeyEvent.keyCode) == 66 -> {
                                    viewModel.search(query)
                                    keyboard?.hide()
                                    true
                                }
                                else -> false
                            }
                        }
                        .fillMaxWidth()
                        .padding(16.dp),
                    enabled = true
                ) {
                    ResultPage(initialState.results, navController)
                }
                AnimatedVisibility(
                    visible = true,
                    enter = fadeIn(),
                    exit = fadeOut()
                ) {
                    IconButton(onClick = {

                    }) {
                        Icon(
                            imageVector = Icons.Default.Close,
                            contentDescription = "Close search"
                        )
                    }
                }
                AnimatedVisibility(
                    visible = !isSearchActive,
                    enter = fadeIn(),
                    exit = fadeOut()
                ) {
                    IconButton(onClick = {

                    }) {
                        Icon(
                            imageVector = Icons.Default.Search,
                            contentDescription = "Open search"
                        )

                    }
                }
            }
        }
    }
}
