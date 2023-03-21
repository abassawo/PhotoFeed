package com.lindenlabs.photofeed.android.screens.feed.presentation

import androidx.lifecycle.ViewModel
import com.lindenlabs.photofeed.android.data.AppDataSource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class FeedViewModel @Inject constructor(val appDataSource: AppDataSource) : ViewModel() {

}
