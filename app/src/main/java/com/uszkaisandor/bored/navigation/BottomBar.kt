package com.uszkaisandor.bored.navigation

import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import com.ramcosta.composedestinations.navigation.navigateTo
import com.uszkaisandor.bored.presentation.NavGraphs
import com.uszkaisandor.bored.presentation.appCurrentDestinationAsState
import com.uszkaisandor.bored.presentation.destinations.Destination
import com.uszkaisandor.bored.presentation.startAppDestination

@Composable
fun BottomBar(
    navController: NavController
) {
    val currentDestination: Destination = navController.appCurrentDestinationAsState().value
        ?: NavGraphs.root.startAppDestination

    NavigationBar {
        BottomBarDestination.entries.forEach { destination ->
            val isSelected = currentDestination == destination.direction
            NavigationBarItem(
                selected = isSelected,
                onClick = {
                    navController.navigateTo(destination.direction) {
                        launchSingleTop = true
                    }
                },
                icon = {
                    Icon(
                        if (isSelected) destination.selectedIcon else destination.unSelectedIcon,
                        contentDescription = stringResource(destination.label)
                    )
                },
                label = { Text(stringResource(destination.label)) },
            )
        }
    }
}


