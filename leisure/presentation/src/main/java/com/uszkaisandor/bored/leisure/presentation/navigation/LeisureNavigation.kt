package com.uszkaisandor.bored.leisure.presentation.navigation

import androidx.navigation3.runtime.NavEntry
import androidx.navigation3.runtime.NavKey
import com.uszkaisandor.bored.leisure.presentation.favourites.FavouriteActivitiesScreen
import com.uszkaisandor.bored.leisure.presentation.home.HomeScreen
import kotlinx.serialization.Serializable

/** Navigation keys this feature exposes to the app's back stack. */
@Serializable
data object HomeKey : NavKey

@Serializable
data object FavouritesKey : NavKey

/**
 * Resolves this feature's keys to their [NavEntry], or null for keys it does
 * not own — so the app can compose feature entry providers.
 */
fun leisureEntry(key: NavKey): NavEntry<NavKey>? = when (key) {
    is HomeKey -> NavEntry(key) { HomeScreen() }
    is FavouritesKey -> NavEntry(key) { FavouriteActivitiesScreen() }
    else -> null
}
