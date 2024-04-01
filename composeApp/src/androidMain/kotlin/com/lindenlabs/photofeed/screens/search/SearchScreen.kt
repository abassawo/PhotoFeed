package com.lindenlabs.photofeed.screens.search

//noinspection UsingMaterialAndMaterial3Libraries
//noinspection UsingMaterialAndMaterial3Libraries
//noinspection UsingMaterialAndMaterial3Libraries
import android.util.Log
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material.Button
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Switch
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.rememberScaffoldState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.SearchBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.key.onKeyEvent
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.lindenlabs.photofeed.views.VerticalResults
import presentation.FeedScreenContract
import presentation.MainViewModel

@Composable
internal fun SearchScreen(viewModel: SearchViewModel, navController: NavController) {
    androidx.compose.material3.Scaffold { padding ->
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
//                is FeedScreenContract.ViewState.Content -> TODO()
//                FeedScreenContract.ViewState.EmptyState -> TODO()
//                is FeedScreenContract.ViewState.Error -> TODO()
//                FeedScreenContract.ViewState.Loading -> TODO()
            }
        }
    }
}

@Composable
fun SearchScreenContent(viewModel: MainViewModel, navController: NavController) {
    val shouldFilterFavourites = viewModel.shouldFilterFavourites.collectAsState()

    val query = viewModel.searchTerm
    val keyboard = LocalSoftwareKeyboardController.current
    val scaffoldState = rememberScaffoldState()
    val results = (viewModel.viewState.collectAsState().value as? FeedScreenContract.ViewState.Content)?.feedItems ?: emptyList()

    Scaffold(scaffoldState = scaffoldState) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
        ) {

            Row(
                Modifier
                    .wrapContentWidth(Alignment.End)
                    .padding(8.dp)
            ) {
                Text(text = "Filter favourites")
                Switch(
                    checked = shouldFilterFavourites.value,
                    modifier = Modifier.padding(horizontal = 8.dp),
                    onCheckedChange = { viewModel.onToggleFavouriteFilter() }
                )
                Column {
                    TextField(value = viewModel.searchTerm.value, singleLine = true, onValueChange = {
                        viewModel.searchTerm.value = it
                    })
                    Button(enabled = viewModel.isSearchButtonEnabled(), onClick = {
                        viewModel.search()
                    }) {
                        Text(text = "Search")
                    }

                }

            }

            val hasResults = results.isNotEmpty()
            AnimatedVisibility(
                visible = hasResults,
                enter = fadeIn(),
                exit = fadeOut()
            ) {
                VerticalResults(results.first().results, navController) // todo - differentiate btwn favorites and search mode
            }
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
@Composable
internal fun InitialViewState(viewModel: SearchViewModel, navController: NavController) {
//    rememberSystemUiController().apply {
//        setSystemBarsColor(
//            color = Color.Transparent
//        )
//    }
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
                    VerticalResults(initialState.results, navController)
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
