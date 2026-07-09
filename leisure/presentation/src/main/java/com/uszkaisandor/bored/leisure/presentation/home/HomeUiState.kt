package com.uszkaisandor.bored.leisure.presentation.home

import com.uszkaisandor.bored.core.ui.UiState
import com.uszkaisandor.bored.leisure.domain.LeisureActivity

data class HomeUiState(
    val currentLeisureActivity: LeisureActivity? = null,
    val isLoading: Boolean = false,
    val hasError: Boolean = false,
) : UiState
