package domain

import org.koin.core.scope.Scope
import platform.Foundation.NSUserDefaults
import platform.Foundation.setValue

actual fun Scope.makeSharedPreferences(): PreferenceUtil {
    return PreferenceUtil()
}

actual class PreferenceUtil {
    val userDefaults = NSUserDefaults.standardUserDefaults

    actual fun put(key: String, value: Set<String>) {
            val history = get("history").toList() + value.toList()
            NSUserDefaults.standardUserDefaults.setValue(history, forKey = "history")
    }

    actual fun get(key: String): Set<String> {
        val keyExists = NSUserDefaults.standardUserDefaults.stringArrayForKey(key) != null
        if (keyExists) {
            return (NSUserDefaults.standardUserDefaults.stringArrayForKey(key) as List<String>).toSet()
        } else {
            return emptySet()
        }
    }
}
