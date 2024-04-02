package com.uszkaisandor.bored.vm.home

import com.uszkaisandor.bored.domain.LeisureActivity
import com.uszkaisandor.bored.vm.common.UiState

data class HomeUiState(
    val currentLeisureActivity: LeisureActivity? = null
) : UiState