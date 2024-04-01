package com.lindenlabs.photofeed

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.lindenlabs.photofeed.android.screens.search.domain.GetSearchScreenUi
import com.lindenlabs.photofeed.navigation.AppNavigator
import com.lindenlabs.photofeed.screens.detail.DetailScreen
import com.lindenlabs.photofeed.screens.saved.FeedScaffold
import com.lindenlabs.photofeed.screens.saved.FeedViewModel
import com.lindenlabs.photofeed.screens.search.SearchScaffold
import com.lindenlabs.photofeed.screens.search.SearchViewModel
import com.lindenlabs.photofeed.views.BottomNavigation
import com.lindenlabs.shared.data.AppRepository
import domain.GetSearchResultViewEntities
import domain.SearchViewMapper
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : ComponentActivity() {
    val appDataSource = AppRepository()
    private val searcher =
        GetSearchResultViewEntities(SearchViewMapper(), GetSearchScreenUi(appDataSource))
//    private val viewModel by viewModels<com.lindenlabs.photofeed.MainViewModel>()
    private val searchViewModel by viewModel<SearchViewModel>()
    private val feedViewModel by viewModel<FeedViewModel>()

    @SuppressLint("UnusedMaterialScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val navController = rememberNavController()
            val appNavigator = AppNavigator(navController)

            MaterialTheme {
                Scaffold(bottomBar = { BottomNavigation(navController) }) { _ ->
                    NavHost(navController = navController, startDestination = "search") {
                        composable("search") {
                            SearchScaffold(
                                navController = navController,
                                searchViewModel
                            )
                        }
                        composable("saved") {
                            FeedScaffold(
                                navController = navController,
                                feedViewModel
                            )
                        }
                        composable(
                            "detail/{server}/{secret}/{imageId}",
                            arguments = listOf(
                                navArgument("server") {
                                    type = NavType.StringType
                                },
                                navArgument("secret") {
                                    type = NavType.StringType
                                },
                                navArgument("imageId") { type = NavType.StringType })
                        ) { backStackEntry ->
                            val server = backStackEntry.arguments?.getString("server") ?: ""
                            val secret = backStackEntry.arguments?.getString("secret") ?: ""
                            val imageId = backStackEntry.arguments?.getString("imageId") ?: ""
                            DetailScreen(
                                appNavigator,
                                imageId,
                                "https://live.staticflickr.com/${server}/${imageId}_$secret.jpg"
                            )
                        }
                    }
                }
            }
        }


    }
}

@Preview
@Composable
fun AppAndroidPreview() {
}