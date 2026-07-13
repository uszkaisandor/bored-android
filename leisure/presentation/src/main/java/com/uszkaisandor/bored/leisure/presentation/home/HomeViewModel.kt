package com.uszkaisandor.bored.leisure.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.uszkaisandor.bored.core.domain.result.Outcome
import com.uszkaisandor.bored.core.ui.BaseViewModel
import com.uszkaisandor.bored.leisure.domain.LeisureActivityType
import com.uszkaisandor.bored.leisure.domain.repository.LeisureActivityRepository
import com.uszkaisandor.bored.leisure.presentation.home.HomeUiState.ActivityState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
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
        val type = uiState.value.selectedType
        viewModelScope.launch {
            repository.getRandom(type)
                .onStart { _uiState.update { it.copy(activityState = ActivityState.Loading) } }
                .collectLatest { outcome ->
                    _uiState.update {
                        it.copy(
                            activityState = when (outcome) {
                                is Outcome.Success -> ActivityState.Content(outcome.data)
                                is Outcome.Failure -> ActivityState.Error(outcome.error)
                            }
                        )
                    }
                }
        }
    }

    fun onButtonClicked() = getRandomActivity()

    fun onRetry() = getRandomActivity()

    fun onTypeSelected(type: LeisureActivityType?) {
        if (type == uiState.value.selectedType) return
        _uiState.update { it.copy(selectedType = type) }
        getRandomActivity()
    }

    fun onFavouriteChecked(isChecked: Boolean) {
        val state = uiState.value.activityState
        if (state !is ActivityState.Content) return
        val activity = state.activity
        // Optimistic toggle; roll back if persistence fails.
        _uiState.update {
            it.copy(activityState = ActivityState.Content(activity.copy(isFavourite = isChecked)))
        }
        viewModelScope.launch {
            runCatching { repository.setIsFavourite(activity.id, isChecked) }
                .onFailure {
                    _uiState.update { current ->
                        val now = current.activityState
                        if (now is ActivityState.Content && now.activity.id == activity.id) {
                            current.copy(activityState = ActivityState.Content(activity))
                        } else {
                            current
                        }
                    }
                }
        }
    }
}
