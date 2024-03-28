package com.uszkaisandor.bored.architecture

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import com.uszkaisandor.bored.presentation.app.BoredApp
import com.uszkaisandor.bored.ui.theme.BoredTheme
import com.uszkaisandor.bored.vm.ActivitiesViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel: ActivitiesViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BoredTheme {
                CompositionLocalProvider {
                    BoredApp(modifier = Modifier.safeDrawingPadding())
                }
            }
        }
    }
}