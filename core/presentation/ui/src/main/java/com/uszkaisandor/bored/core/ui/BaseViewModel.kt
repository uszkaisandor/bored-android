package com.uszkaisandor.bored.core.ui

import kotlinx.coroutines.flow.StateFlow

interface BaseViewModel<T : UiState> {
    val uiState: StateFlow<T>
}