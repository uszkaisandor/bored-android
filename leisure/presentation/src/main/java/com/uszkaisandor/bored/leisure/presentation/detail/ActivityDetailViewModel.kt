package com.uszkaisandor.bored.leisure.presentation.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.uszkaisandor.bored.core.ui.BaseViewModel
import com.uszkaisandor.bored.leisure.domain.repository.LeisureActivityRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ActivityDetailViewModel(
    private val repository: LeisureActivityRepository,
    private val activityId: String,
) : ViewModel(), BaseViewModel<ActivityDetailUiState> {

    private val _uiState = MutableStateFlow(ActivityDetailUiState())
    override val uiState = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            repository.getActivity(activityId).collect { activity ->
                _uiState.update {
                    it.copy(activity = activity, isLoading = false, notFound = activity == null)
                }
            }
        }
    }

    fun onFavouriteChecked(isChecked: Boolean) {
        viewModelScope.launch { repository.setIsFavourite(activityId, isChecked) }
    }
}
