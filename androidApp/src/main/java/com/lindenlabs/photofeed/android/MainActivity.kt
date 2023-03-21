package com.lindenlabs.photofeed.android

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.lindenlabs.photofeed.android.screens.detail.DetailScreen
import com.lindenlabs.photofeed.android.screens.main.MainScreen
import com.lindenlabs.photofeed.android.screens.main.search.presentation.SearchViewModel
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
                        MainScreen(navController = navController, viewModel)
                    }
                    composable("search") {
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
        }
    }
}


@Composable
private fun BottomNavigation(
    navController: NavHostController
) {
    NavigationBar {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route ?: "saved"

        bottomNavigationItems.forEach { item ->
            NavigationBarItem(
                icon = {
                    Icon(
                        item.icon,
                        contentDescription = item.iconContentDescription
                    )
                },
                selected = currentRoute == item.route,
                onClick = {
                    navController.navigate(item.route)
                }
            )
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
