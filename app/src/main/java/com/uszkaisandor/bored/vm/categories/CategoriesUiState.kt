package com.uszkaisandor.bored.vm.categories

import com.uszkaisandor.bored.domain.LeisureActivityType
import com.uszkaisandor.bored.vm.common.UiState

data class CategoriesUiState(
    val categories: List<LeisureActivityType> = emptyList()
) : UiState