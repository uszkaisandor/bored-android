package com.uszkaisandor.bored.presentation.app

import android.annotation.SuppressLint
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.ramcosta.composedestinations.DestinationsNavHost
import com.uszkaisandor.bored.navigation.BottomBar
import com.uszkaisandor.bored.presentation.NavGraphs

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun BoredApp(modifier: Modifier) {
    val navController = rememberNavController()

    Scaffold(
        modifier = modifier,
        bottomBar = {
            BottomBar(navController)
        }
        //...
    ) { paddingValues ->
        DestinationsNavHost(
            navController = navController,
            startRoute = NavGraphs.root.startRoute,
            navGraph = NavGraphs.root
        )
    }

}