package com.uszkaisandor.bored.leisure.presentation.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.uszkaisandor.bored.core.domain.result.Outcome
import com.uszkaisandor.bored.core.ui.BaseViewModel
import com.uszkaisandor.bored.leisure.domain.repository.LeisureActivityRepository
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

class ActivityDetailViewModel(
    private val repository: LeisureActivityRepository,
    private val activityId: String,
) : ViewModel(), BaseViewModel<ActivityDetailUiState> {

    private val _uiState = MutableStateFlow<ActivityDetailUiState>(ActivityDetailUiState.Loading)
    override val uiState = _uiState.asStateFlow()

    private var observeJob: Job? = null

    init {
        observeActivity()
    }

    fun onRetry() = observeActivity()

    private fun observeActivity() {
        observeJob?.cancel()
        observeJob = viewModelScope.launch {
            repository.getActivity(activityId)
                .onStart { _uiState.value = ActivityDetailUiState.Loading }
                .collect { outcome ->
                    _uiState.value = when (outcome) {
                        is Outcome.Success -> ActivityDetailUiState.Content(outcome.data)
                        is Outcome.Failure -> ActivityDetailUiState.Error(outcome.error)
                    }
                }
        }
    }

    fun onFavouriteChecked(isChecked: Boolean) {
        viewModelScope.launch { repository.setIsFavourite(activityId, isChecked) }
    }
}
