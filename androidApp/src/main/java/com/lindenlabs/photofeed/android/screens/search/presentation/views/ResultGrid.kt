package com.lindenlabs.photofeed.android.screens.search.presentation.views

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.lindenlabs.photofeed.android.screens.search.presentation.SearchScreenContract
import com.lindenlabs.photofeed.android.screens.search.presentation.SearchViewModel

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun ResultGrid(viewModel: SearchViewModel) {
    val viewState = (viewModel.viewState.collectAsState() as? SearchScreenContract.ViewState.Initial)
            ?: SearchScreenContract.ViewState.Initial()
    Log.d("SVM", viewState.results.toString())
    LazyColumn(modifier = Modifier.background(Color.Blue)) {
        items(viewState.results) { viewEntity ->
            Text(viewEntity.photo.rawPhotoItem.title)
            GlideImage(
                model = viewEntity.photo.url,
                contentDescription = "",
                modifier = Modifier.clickable(onClick = {}).fillParentMaxSize()
            )
        }
    }
}