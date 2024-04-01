package com.lindenlabs.photofeed.screens.detail

import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.lindenlabs.photofeed.navigation.AppNavigator
import utils.ImageMemo

@Composable
fun TopAppBar(title: String, appNavigator: AppNavigator) {
    Row(
        Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(16.dp)) {
        Icon(Icons.Default.ArrowBack, contentDescription = "", Modifier.clickable {
            appNavigator.navigateUp()
        })
        Text(text = title)
    }
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun DetailScreen(appNavigator: AppNavigator, id: String, url: String) {
    val photo = ImageMemo.getImage(id)
    Scaffold(topBar =  { TopAppBar(photo?.rawPhotoItem?.title ?: "", appNavigator) }, modifier = Modifier.fillMaxSize() ) { padding ->
        Box(
            Modifier
                .fillMaxSize()
                .padding(padding), contentAlignment = Alignment.Center) {
            AsyncImage(model = url, contentDescription = "", Modifier.fillMaxSize())
        }
    }
}