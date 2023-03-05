package com.lindenlabs.photofeed.android.screens.main.search.presentation.views

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.lindenlabs.photofeed.android.screens.main.search.presentation.entities.ImageResultViewEntity

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun ResultGrid(results: List<ImageResultViewEntity>, navController: NavController) {
    Log.d("SVM", results.toString())
    LazyColumn {
        items(results) { viewEntity ->
            Text(viewEntity.photo.rawPhotoItem.title)
            GlideImage(
                model = viewEntity.photo.url,
                contentDescription = "",
                modifier = Modifier
                    .width(100.dp)
                    .height(100.dp)
                    .clickable(onClick = {}).fillParentMaxSize()
            )
        }
    }
}