package com.uszkaisandor.bored.presentation.common

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.uszkaisandor.bored.ui.theme.AppTheme
import com.uszkaisandor.bored.vm.common.BaseViewModel
import com.uszkaisandor.bored.vm.common.UiState

@Composable
inline fun <reified T : UiState> BaseScreen(
    viewModel: BaseViewModel<T>,
    modifier: Modifier = Modifier,
    crossinline content: @Composable (T) -> Unit,
) {
    val uiState = viewModel.uiState.collectAsStateWithLifecycle().value

    AppTheme {
        Scaffold(
            modifier = Modifier.fillMaxSize()
        ) { contentPadding ->
            Box(
                modifier = modifier
                    .fillMaxSize()
                    .padding(contentPadding)
            ) {
                content(uiState)
            }
        }
    }
}