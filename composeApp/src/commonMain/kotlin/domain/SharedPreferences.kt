package domain

import org.koin.core.scope.Scope

expect fun Scope.makeSharedPreferences(): PreferenceUtil

expect class PreferenceUtil {
    fun put(key: String = "history", value: Set<String>)
    fun get(key: String): Set<String>
}