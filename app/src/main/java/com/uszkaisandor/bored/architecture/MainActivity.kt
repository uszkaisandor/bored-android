package com.uszkaisandor.bored.architecture

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.uszkaisandor.bored.presentation.app.BoredApp
import com.uszkaisandor.bored.core.designsystem.AppTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen().setKeepOnScreenCondition { false }
        super.onCreate(savedInstanceState)
        // Draw behind the system bars; the Scaffold in BoredApp applies the insets.
        enableEdgeToEdge()
        setContent {
            AppTheme {
                BoredApp()
            }
        }
    }
}