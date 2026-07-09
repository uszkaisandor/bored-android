package com.uszkaisandor.bored.navigation

import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.navigation3.runtime.NavBackStack
import androidx.navigation3.runtime.NavKey

@Composable
fun BottomBar(backStack: NavBackStack<NavKey>) {
    val current = backStack.lastOrNull()

    NavigationBar {
        BottomBarDestination.entries.forEach { destination ->
            val isSelected = current == destination.key
            NavigationBarItem(
                selected = isSelected,
                onClick = {
                    if (!isSelected) {
                        backStack.clear()
                        backStack.add(destination.key)
                    }
                },
                icon = {
                    Icon(
                        imageVector = if (isSelected) destination.selectedIcon else destination.unSelectedIcon,
                        contentDescription = stringResource(destination.label)
                    )
                },
                label = { Text(stringResource(destination.label)) },
            )
        }
    }
}
