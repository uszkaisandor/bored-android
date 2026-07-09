package com.uszkaisandor.bored.presentation.app

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.ui.NavDisplay
import com.uszkaisandor.bored.leisure.presentation.navigation.HomeKey
import com.uszkaisandor.bored.leisure.presentation.navigation.leisureEntry
import com.uszkaisandor.bored.navigation.BottomBar

@Composable
fun BoredApp(modifier: Modifier = Modifier) {
    val backStack = rememberNavBackStack(HomeKey)

    Scaffold(
        modifier = modifier,
        bottomBar = { BottomBar(backStack) }
    ) { paddingValues ->
        Column(modifier = Modifier.padding(paddingValues)) {
            DailyTipBanner(tip = DailyTipProvider.getTipForToday())
            NavDisplay(
                backStack = backStack,
                onBack = { if (backStack.size > 1) backStack.removeLastOrNull() },
                entryProvider = { key ->
                    leisureEntry(key) ?: error("Unknown navigation key: $key")
                },
            )
        }
    }
}
