package com.uszkaisandor.bored.leisure.presentation.home

import com.uszkaisandor.bored.core.ui.UiState
import com.uszkaisandor.bored.leisure.domain.LeisureActivity
import com.uszkaisandor.bored.leisure.domain.LeisureActivityType

data class HomeUiState(
    val currentLeisureActivity: LeisureActivity? = null,
    val selectedType: LeisureActivityType? = null,
    val isLoading: Boolean = false,
    val hasError: Boolean = false,
) : UiState
