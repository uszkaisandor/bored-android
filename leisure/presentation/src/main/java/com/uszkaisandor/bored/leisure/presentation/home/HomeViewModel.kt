package com.uszkaisandor.bored.leisure.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.uszkaisandor.bored.core.ui.BaseViewModel
import com.uszkaisandor.bored.leisure.domain.repository.LeisureActivityRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class HomeViewModel(
    private val repository: LeisureActivityRepository,
) : ViewModel(), BaseViewModel<HomeUiState> {

    private val _uiState = MutableStateFlow(HomeUiState())
    override val uiState = _uiState.asStateFlow()

    init {
        getRandomActivity()
    }

    private fun getRandomActivity() {
        viewModelScope.launch {
            repository.getRandom()
                .onStart { _uiState.update { it.copy(isLoading = true, hasError = false) } }
                .catch { _uiState.update { it.copy(isLoading = false, hasError = true) } }
                .collectLatest { activity ->
                    _uiState.update {
                        it.copy(
                            currentLeisureActivity = activity,
                            isLoading = false,
                            hasError = false,
                        )
                    }
                }
        }
    }

    fun onButtonClicked() {
        getRandomActivity()
    }

    fun onFavouriteChecked(isChecked: Boolean) {
        val current = uiState.value.currentLeisureActivity ?: return
        viewModelScope.launch {
            _uiState.update { it.copy(currentLeisureActivity = current.copy(isFavourite = isChecked)) }
            repository.setIsFavourite(current.id, isChecked)
        }
    }

}
