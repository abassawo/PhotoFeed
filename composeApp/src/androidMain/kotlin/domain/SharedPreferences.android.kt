package domain

import android.content.Context
import android.content.SharedPreferences
import org.koin.android.ext.koin.androidContext
import org.koin.core.scope.Scope

actual fun Scope.makeSharedPreferences(): PreferenceUtil {
    return PreferenceUtil(androidContext().getSharedPreferences("history", Context.MODE_PRIVATE))
}


actual class PreferenceUtil (val sharedPreferences: SharedPreferences) {

    actual fun put(key: String, value: Set<String>) {
        sharedPreferences
            .edit()
            .putStringSet("history",  getHistory() + value) // add previous items with it
            .apply()
    }

    fun getHistory(): Set<String> =
        sharedPreferences.getStringSet("history", emptySet()) ?: emptySet()

    actual fun get(key: String): Set<String> {
        return sharedPreferences.getStringSet("history", emptySet()) ?: emptySet()
    }
}

