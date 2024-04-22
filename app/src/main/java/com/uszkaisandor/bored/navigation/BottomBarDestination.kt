package com.uszkaisandor.bored.navigation

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.Home
import androidx.compose.ui.graphics.vector.ImageVector
import com.ramcosta.composedestinations.spec.DirectionDestinationSpec
import com.uszkaisandor.bored.R
import com.uszkaisandor.bored.presentation.destinations.FavouriteActivitiesScreenDestination
import com.uszkaisandor.bored.presentation.destinations.HomeScreenDestination

enum class BottomBarDestination(
    val direction: DirectionDestinationSpec,
    val unSelectedIcon: ImageVector,
    val selectedIcon: ImageVector,
    @StringRes val label: Int
) {
    Home(
        direction = HomeScreenDestination,
        unSelectedIcon = Icons.Outlined.Home,
        selectedIcon = Icons.Filled.Home,
        label = R.string.home_tab_title
    ),
    Favourites(
        direction = FavouriteActivitiesScreenDestination,
        unSelectedIcon = Icons.Outlined.FavoriteBorder,
        selectedIcon = Icons.Filled.Favorite,
        label = R.string.favourite_tab_title
    ),
}

