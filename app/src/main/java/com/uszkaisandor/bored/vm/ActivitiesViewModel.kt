package com.uszkaisandor.bored.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.uszkaisandor.bored.domain.Activity
import com.uszkaisandor.bored.repository.ActivityRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ActivitiesViewModel @Inject constructor(
    private val repository: ActivityRepository
) : ViewModel() {

    private val _activity = MutableStateFlow<Activity?>(null)
    val activity = _activity.asStateFlow()

    init {
        getRandomActivity()
    }

    private fun getRandomActivity() {
        viewModelScope.launch {
            repository.getActivity()
                .catch {
                    it
                    // todo handle error
                }
                .collectLatest {
                    _activity.value = it
                }
        }
    }

}