package com.uszkaisandor.bored.navigation

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.ui.graphics.vector.ImageVector
import com.ramcosta.composedestinations.spec.DirectionDestinationSpec
import com.uszkaisandor.bored.R
import com.uszkaisandor.bored.presentation.destinations.CategoriesScreenDestination
import com.uszkaisandor.bored.presentation.destinations.FavouritesScreenDestination
import com.uszkaisandor.bored.presentation.destinations.HomeScreenDestination

enum class BottomBarDestination(
    val direction: DirectionDestinationSpec,
    val icon: ImageVector,
    @StringRes val label: Int
) {
    Home(HomeScreenDestination, Icons.Default.Home, R.string.home_tab_title),
    Categories(CategoriesScreenDestination, Icons.Default.Search, R.string.categories_tab_title),
    Favourites(FavouritesScreenDestination, Icons.Default.Favorite, R.string.favourite_tab_title),
}

