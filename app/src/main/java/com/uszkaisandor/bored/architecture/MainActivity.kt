package com.uszkaisandor.bored.architecture

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import com.uszkaisandor.bored.ui.theme.BoredTheme
import com.uszkaisandor.bored.views.ActivityRecommendation
import com.uszkaisandor.bored.vm.ActivitiesViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel: ActivitiesViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BoredTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val currentActivity = viewModel.activity.collectAsState()
                    currentActivity.value?.let {
                        ActivityRecommendation(it, onButtonClick = viewModel::onButtonClicked)
                    }
                }
            }
        }
    }
}