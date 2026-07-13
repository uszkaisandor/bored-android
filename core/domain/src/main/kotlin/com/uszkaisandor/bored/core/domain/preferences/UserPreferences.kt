package com.uszkaisandor.bored.core.domain.preferences

/** How the app resolves light/dark. [SYSTEM] follows the device setting. */
enum class ThemeMode { SYSTEM, LIGHT, DARK }

/** User-controlled app preferences, persisted on-device. */
data class UserPreferences(
    val themeMode: ThemeMode = ThemeMode.SYSTEM,
    val dynamicColor: Boolean = true,
) {
    companion object {
        val DEFAULT = UserPreferences()
    }
}
