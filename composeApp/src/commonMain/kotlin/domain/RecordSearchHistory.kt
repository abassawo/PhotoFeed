package com.lindenlabs.photofeed.android.screens.search.domain

import domain.PreferenceUtil


class RecordSearchHistory (private val sharedPreferences: PreferenceUtil) {

    operator fun invoke(query: String) {
        val history = getHistory() + query
        sharedPreferences.put("history", history)
    }

    fun getHistory(): Set<String> =
        sharedPreferences.get("history") ?: emptySet()

}