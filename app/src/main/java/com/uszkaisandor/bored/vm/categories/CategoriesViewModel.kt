package com.uszkaisandor.bored.vm.categories

import androidx.lifecycle.ViewModel
import com.uszkaisandor.bored.domain.LeisureActivityType
import com.uszkaisandor.bored.vm.common.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class CategoriesViewModel @Inject constructor() : BaseViewModel<CategoriesUiState>, ViewModel() {

    private val _uiState = MutableStateFlow(
        CategoriesUiState(LeisureActivityType.entries.toList())
    )
    override val uiState: StateFlow<CategoriesUiState> = _uiState.asStateFlow()

}