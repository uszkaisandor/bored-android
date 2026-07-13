package com.uszkaisandor.bored.leisure.presentation.detail

import com.uszkaisandor.bored.core.domain.result.DomainError
import com.uszkaisandor.bored.core.ui.UiState
import com.uszkaisandor.bored.leisure.domain.LeisureActivity

sealed interface ActivityDetailUiState : UiState {
    data object Loading : ActivityDetailUiState
    data class Content(val activity: LeisureActivity) : ActivityDetailUiState
    data class Error(val error: DomainError) : ActivityDetailUiState
}
