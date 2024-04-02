package com.uszkaisandor.bored.vm.common

import kotlinx.coroutines.flow.StateFlow

interface BaseViewModel<T : UiState> {
    val uiState: StateFlow<T>
}