package com.lindenlabs.photofeed.android.screens.search.domain

import android.content.SharedPreferences
import javax.inject.Inject

class RecordSearchHistory @Inject constructor(private val sharedPreferences: SharedPreferences) {

    operator fun invoke(query: String) {
        val history = getHistory() + query
        sharedPreferences
            .edit()
            .putStringSet("history", history)
            .apply()
    }

    fun getHistory(): Set<String> =
        sharedPreferences.getStringSet("history", emptySet()) ?: emptySet()

}