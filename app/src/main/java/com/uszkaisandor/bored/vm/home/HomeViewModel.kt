package com.uszkaisandor.bored.vm.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.uszkaisandor.bored.repository.LeisureActivityRepository
import com.uszkaisandor.bored.vm.common.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: LeisureActivityRepository
) : ViewModel(), BaseViewModel<HomeUiState> {

    private val _uiState = MutableStateFlow(HomeUiState())
    override val uiState = _uiState.asStateFlow()

    init {
        getRandomActivity()
    }

    private fun getRandomActivity() {
        viewModelScope.launch {
            repository.getRandom()
                .catch {
                    // todo work on error handling
                }
                .collectLatest {
                    _uiState.update { state ->
                        state.copy(currentLeisureActivity = it)
                    }
                }
        }
    }

    fun onButtonClicked() {
        getRandomActivity()
    }

}