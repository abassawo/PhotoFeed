package com.lindenlabs.photofeed.android

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.lindenlabs.photofeed.android.screens.detail.DetailScreen
import com.lindenlabs.photofeed.android.screens.feed.presentation.views.FeedScaffold
import com.lindenlabs.photofeed.android.screens.search.presentation.views.SearchScaffold
import com.lindenlabs.photofeed.android.screens.search.presentation.SearchViewModel
import com.lindenlabs.photofeed.android.ui.components.BottomNavigation
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val navController = rememberNavController()
            Scaffold(
                bottomBar = { BottomNavigation(navController = navController) },
                contentWindowInsets = WindowInsets(0, 0, 0, 0),
            ) {

                val viewModel: SearchViewModel = hiltViewModel()
                NavHost(navController = navController, startDestination = "saved") {
                    composable("saved") {
                        FeedScaffold(navController = navController)
                    }
                    composable("search") {
                        SearchScaffold(navController = navController, viewModel)
                    }
                    composable(
                        "detail/{imageId}",
                        arguments = listOf(navArgument("imageId") { type = NavType.StringType })
                    ) { backStackEntry ->
                        backStackEntry.arguments?.getString("imageId")?.let { imageId ->
                            DetailScreen(imageId = imageId)
                        }
                    }
                }
            }
        }
    }
}



@Preview
@Composable
fun DefaultPreview() {
    MyApplicationTheme {
//        GreetingView("Hello, Android!")
    }
}
