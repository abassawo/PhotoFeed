package com.lindenlabs.photofeed.android.screens.search.presentation.views

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.lindenlabs.photofeed.android.screens.search.presentation.entities.ImageResultViewEntity

@Composable
fun VerticalResults(results: List<ImageResultViewEntity>, navController: NavController) {
    LazyVerticalGrid(columns = GridCells.Adaptive(Dp(100f)), content = {
        items(results) { viewEntity ->
            val photo = viewEntity.photo.rawPhotoItem
            AsyncImage(
                model = viewEntity.photo.url,
                contentDescription = photo.title,
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable {
                        val route = "detail/${photo.server}/${photo.id}"
                        navController.navigate(route)
                    }
            )
        }
    })
}


@Composable
fun HorizontalResults(results: List<ImageResultViewEntity>, navController: NavController) {
    LazyRow {
        items(results) { viewEntity ->
            Column {
                with(viewEntity.photo) {
                    AsyncImage(
                        model = url,
                        contentDescription = rawPhotoItem.title,
                        modifier = Modifier
                            .height(100.dp)
                            .clickable {
                                Log.d("Result URL", url)
                                Log.d("Result ID", rawPhotoItem.id)
                                Log.d("Result Server", rawPhotoItem.server)
                                val route = "detail/${rawPhotoItem.server}/${rawPhotoItem.id}"
                                navController.navigate(route)
                            }
                    )
                }
            }
        }
    }
}