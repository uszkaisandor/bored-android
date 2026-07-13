package com.uszkaisandor.bored.core.domain.preferences

import kotlinx.coroutines.flow.Flow

/** Reads and writes the user's app preferences. Framework-agnostic (KMP-ready). */
interface UserPreferencesRepository {
    val userPreferences: Flow<UserPreferences>
    suspend fun setThemeMode(themeMode: ThemeMode)
    suspend fun setDynamicColor(enabled: Boolean)
}
