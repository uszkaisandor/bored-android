package com.uszkaisandor.bored.settings.presentation.navigation

import androidx.navigation3.runtime.NavEntry
import androidx.navigation3.runtime.NavKey
import com.uszkaisandor.bored.settings.presentation.SettingsScreen
import kotlinx.serialization.Serializable

@Serializable
data object SettingsKey : NavKey

/** Resolves this feature's keys to their [NavEntry], or null for keys it does not own. */
fun settingsEntry(key: NavKey): NavEntry<NavKey>? = when (key) {
    is SettingsKey -> NavEntry(key) { SettingsScreen() }
    else -> null
}
