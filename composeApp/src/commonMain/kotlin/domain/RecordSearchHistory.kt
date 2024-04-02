package domain


class RecordSearchHistory (private val sharedPreferences: PreferenceUtil) {

    operator fun invoke(query: String) {
        val history = getHistory() + query
        sharedPreferences.put("history", history)
    }

    fun getHistory(): Set<String> =
        sharedPreferences.get("history")

}