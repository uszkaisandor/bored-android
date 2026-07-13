package com.uszkaisandor.bored.leisure.presentation.navigation

import androidx.navigation3.runtime.NavEntry
import androidx.navigation3.runtime.NavKey
import com.uszkaisandor.bored.leisure.presentation.detail.ActivityDetailScreen
import com.uszkaisandor.bored.leisure.presentation.favourites.FavouriteActivitiesScreen
import com.uszkaisandor.bored.leisure.presentation.home.HomeScreen
import kotlinx.serialization.Serializable

/** Navigation keys this feature exposes to the app's back stack. */
@Serializable
data object HomeKey : NavKey

@Serializable
data object FavouritesKey : NavKey

@Serializable
data class ActivityDetailKey(val id: String) : NavKey

/**
 * Resolves this feature's keys to their [NavEntry], or null for keys it does
 * not own. [onNavigate] pushes a new key onto the app's back stack.
 */
fun leisureEntry(
    key: NavKey,
    onNavigate: (NavKey) -> Unit,
): NavEntry<NavKey>? = when (key) {
    is HomeKey -> NavEntry(key) { HomeScreen() }
    is FavouritesKey -> NavEntry(key) {
        FavouriteActivitiesScreen(onActivityClick = { id -> onNavigate(ActivityDetailKey(id)) })
    }
    is ActivityDetailKey -> NavEntry(key) { ActivityDetailScreen(activityId = key.id) }
    else -> null
}
