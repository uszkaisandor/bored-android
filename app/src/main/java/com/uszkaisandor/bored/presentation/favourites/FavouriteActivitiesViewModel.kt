package com.uszkaisandor.bored.presentation.favourites

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.uszkaisandor.bored.domain.LeisureActivity
import com.uszkaisandor.bored.repository.LeisureActivityRepository
import com.uszkaisandor.bored.vm.common.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavouriteActivitiesViewModel @Inject constructor(
    private val repository: LeisureActivityRepository
) : BaseViewModel<FavouriteActivitiesUiState>, ViewModel() {

    private val _uiState = MutableStateFlow(FavouriteActivitiesUiState)
    override val uiState: StateFlow<FavouriteActivitiesUiState> = _uiState

    val activityPagingDataFlow: Flow<PagingData<LeisureActivity>> =
        repository.getFavoriteActivities()
            .flowOn(Dispatchers.IO)
            .cachedIn(viewModelScope)

    fun onFavouriteChecked(id: String) {
        viewModelScope.launch {
            repository.setIsFavourite(id, false)
        }
    }

}