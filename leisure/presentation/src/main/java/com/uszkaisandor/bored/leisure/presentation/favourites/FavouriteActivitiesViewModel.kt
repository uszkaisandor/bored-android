package com.uszkaisandor.bored.leisure.presentation.favourites

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.uszkaisandor.bored.leisure.domain.LeisureActivity
import com.uszkaisandor.bored.leisure.domain.repository.LeisureActivityRepository
import com.uszkaisandor.bored.core.ui.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch

class FavouriteActivitiesViewModel(
    private val repository: LeisureActivityRepository
) : ViewModel(), BaseViewModel<FavouriteActivitiesUiState> {

    private val _uiState = MutableStateFlow(FavouriteActivitiesUiState)
    override val uiState: StateFlow<FavouriteActivitiesUiState> = _uiState

    val activityPagingDataFlow: Flow<PagingData<LeisureActivity>> =
        repository.getFavoriteActivities()
            .flowOn(Dispatchers.IO)
            .cachedIn(viewModelScope)

    fun onSwipedToDelete(id: String) {
        viewModelScope.launch {
            repository.setIsFavourite(id, false)
        }
    }

}