package com.uszkaisandor.bored.architecture

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.getValue
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.uszkaisandor.bored.core.designsystem.AppTheme
import com.uszkaisandor.bored.core.domain.preferences.ThemeMode
import com.uszkaisandor.bored.core.domain.preferences.UserPreferences
import com.uszkaisandor.bored.core.domain.preferences.UserPreferencesRepository
import com.uszkaisandor.bored.presentation.app.BoredApp
import org.koin.compose.koinInject

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen().setKeepOnScreenCondition { false }
        super.onCreate(savedInstanceState)
        // Draw behind the system bars; the Scaffold in BoredApp applies the insets.
        enableEdgeToEdge()
        setContent {
            val preferencesRepository = koinInject<UserPreferencesRepository>()
            val preferences by preferencesRepository.userPreferences
                .collectAsStateWithLifecycle(initialValue = UserPreferences.DEFAULT)

            val darkTheme = when (preferences.themeMode) {
                ThemeMode.LIGHT -> false
                ThemeMode.DARK -> true
                ThemeMode.SYSTEM -> isSystemInDarkTheme()
            }

            AppTheme(darkTheme = darkTheme, dynamicColor = preferences.dynamicColor) {
                BoredApp()
            }
        }
    }
}