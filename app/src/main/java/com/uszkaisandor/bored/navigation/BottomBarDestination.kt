package com.uszkaisandor.bored.navigation

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.Home
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation3.runtime.NavKey
import com.uszkaisandor.bored.R
import com.uszkaisandor.bored.leisure.presentation.navigation.FavouritesKey
import com.uszkaisandor.bored.leisure.presentation.navigation.HomeKey

enum class BottomBarDestination(
    val key: NavKey,
    val unSelectedIcon: ImageVector,
    val selectedIcon: ImageVector,
    @StringRes val label: Int,
) {
    Home(
        key = HomeKey,
        unSelectedIcon = Icons.Outlined.Home,
        selectedIcon = Icons.Filled.Home,
        label = R.string.home_tab_title
    ),
    Favourites(
        key = FavouritesKey,
        unSelectedIcon = Icons.Outlined.FavoriteBorder,
        selectedIcon = Icons.Filled.Favorite,
        label = R.string.favourite_tab_title
    ),
}
