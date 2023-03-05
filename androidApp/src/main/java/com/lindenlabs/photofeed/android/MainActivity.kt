package com.lindenlabs.photofeed.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.lindenlabs.photofeed.android.screens.detail.DetailScreen
import com.lindenlabs.photofeed.android.screens.main.MainScreen
import com.lindenlabs.photofeed.android.screens.main.search.presentation.SearchViewModel
import com.lindenlabs.photofeed.android.ui.components.TopBar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            Scaffold(
                topBar = { TopBar() },
            ) { padding -> // We need to pass scaffold's inner padding to content. That's why we use Box.
                Box(modifier = Modifier.padding(padding)) {
                    Navigation(viewModel = hiltViewModel())
                }
            }
        }
    }
}

@Composable
fun Navigation(viewModel: SearchViewModel) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "main") {
        composable("main") {
            MainScreen(navController = navController, viewModel)
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

@Preview
@Composable
fun DefaultPreview() {
    MyApplicationTheme {
//        GreetingView("Hello, Android!")
    }
}
