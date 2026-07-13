package com.uszkaisandor.bored.leisure.presentation.home

import com.uszkaisandor.bored.core.domain.result.DomainError
import com.uszkaisandor.bored.core.ui.UiState
import com.uszkaisandor.bored.leisure.domain.LeisureActivity
import com.uszkaisandor.bored.leisure.domain.LeisureActivityType

/**
 * The category filter row is always visible ([selectedType]); the card area swaps between
 * the [ActivityState] variants.
 */
data class HomeUiState(
    val selectedType: LeisureActivityType? = null,
    val activityState: ActivityState = ActivityState.Loading,
) : UiState {

    sealed interface ActivityState {
        data object Loading : ActivityState
        data class Content(val activity: LeisureActivity) : ActivityState
        data class Error(val error: DomainError) : ActivityState
    }
}
