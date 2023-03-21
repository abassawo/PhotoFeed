package com.lindenlabs.photofeed.android

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.ui.graphics.vector.ImageVector

data class BottomNavigationItem(
    val route: String,
    val icon: ImageVector,
    val iconContentDescription: String
)

sealed class Screen(val title: String) {
    object FeedScreen : Screen("saved")
    object SearchScreen : Screen("search")
}

val bottomNavigationItems = listOf(
    BottomNavigationItem(
        Screen.FeedScreen.title,
        Icons.Default.AddCircle,
        ""
    ),
    BottomNavigationItem(
        Screen.SearchScreen.title,
        Icons.Filled.Search,
        ""
    )
)