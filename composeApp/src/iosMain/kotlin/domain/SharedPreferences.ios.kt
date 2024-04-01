package domain

import org.koin.core.scope.Scope

actual fun Scope.makeSharedPreferences(): PreferenceUtil {
    return PreferenceUtil()
}

actual class PreferenceUtil {
    actual fun put(key: String, value: Set<String>) {
    }

    actual fun get(key: String): Set<String> {
        return setOf("test")
    }
}