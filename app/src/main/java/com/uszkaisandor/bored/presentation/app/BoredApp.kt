package com.uszkaisandor.bored.presentation.app

import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionLayout
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringArrayResource
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.ui.NavDisplay
import com.uszkaisandor.bored.R
import com.uszkaisandor.bored.leisure.presentation.navigation.HomeKey
import com.uszkaisandor.bored.leisure.presentation.navigation.LocalSharedTransitionScope
import com.uszkaisandor.bored.leisure.presentation.navigation.leisureEntry
import com.uszkaisandor.bored.navigation.BottomBar
import com.uszkaisandor.bored.settings.presentation.navigation.settingsEntry

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun BoredApp(modifier: Modifier = Modifier) {
    val backStack = rememberNavBackStack(HomeKey)

    Scaffold(
        modifier = modifier,
        bottomBar = { BottomBar(backStack) }
    ) { paddingValues ->
        Column(modifier = Modifier.padding(paddingValues)) {
            val tips = stringArrayResource(R.array.daily_tips)
            DailyTipBanner(tip = tips[DailyTipProvider.todayIndex(tips.size)])
            SharedTransitionLayout {
              CompositionLocalProvider(LocalSharedTransitionScope provides this) {
                NavDisplay(
                    backStack = backStack,
                    onBack = { if (backStack.size > 1) backStack.removeLastOrNull() },
                // Forward: the incoming screen fades and scales up into place.
                transitionSpec = {
                    (fadeIn(tween(320)) + scaleIn(tween(320), initialScale = 0.94f)) togetherWith
                        fadeOut(tween(220))
                },
                // Back: the outgoing screen fades and scales down to reveal the previous one.
                popTransitionSpec = {
                    fadeIn(tween(220)) togetherWith
                        (fadeOut(tween(320)) + scaleOut(tween(320), targetScale = 0.94f))
                },
                // Predictive back follows the gesture with the same shrink-to-reveal motion.
                predictivePopTransitionSpec = {
                    fadeIn(tween(220)) togetherWith
                        (fadeOut(tween(320)) + scaleOut(tween(320), targetScale = 0.90f))
                },
                    entryProvider = { key ->
                        leisureEntry(key, onNavigate = { backStack.add(it) })
                            ?: settingsEntry(key)
                            ?: error("Unknown navigation key: $key")
                    },
                )
              }
            }
        }
    }
}
