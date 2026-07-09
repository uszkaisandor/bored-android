package com.uszkaisandor.bored.leisure.presentation.detail

import com.uszkaisandor.bored.core.ui.UiState
import com.uszkaisandor.bored.leisure.domain.LeisureActivity

data class ActivityDetailUiState(
    val activity: LeisureActivity? = null,
    val isLoading: Boolean = true,
    val notFound: Boolean = false,
) : UiState
