package com.uszkaisandor.bored.core.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle

/**
 * Collects [viewModel]'s state with lifecycle awareness and hands it to [content].
 * Theming (AppTheme) and the app Scaffold are provided by the host
 * (MainActivity / BoredApp), so screens don't nest their own.
 */
@Composable
inline fun <reified T : UiState> BaseScreen(
    viewModel: BaseViewModel<T>,
    modifier: Modifier = Modifier,
    crossinline content: @Composable (T) -> Unit,
) {
    val uiState = viewModel.uiState.collectAsStateWithLifecycle().value

    Box(modifier = modifier.fillMaxSize()) {
        content(uiState)
    }
}
