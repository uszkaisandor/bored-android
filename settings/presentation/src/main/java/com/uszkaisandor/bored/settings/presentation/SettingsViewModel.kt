package com.uszkaisandor.bored.settings.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.uszkaisandor.bored.core.domain.preferences.ThemeMode
import com.uszkaisandor.bored.core.domain.preferences.UserPreferencesRepository
import com.uszkaisandor.bored.core.ui.BaseViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class SettingsViewModel(
    private val repository: UserPreferencesRepository,
) : ViewModel(), BaseViewModel<SettingsUiState> {

    override val uiState: StateFlow<SettingsUiState> = repository.userPreferences
        .map { SettingsUiState(themeMode = it.themeMode, dynamicColor = it.dynamicColor) }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5_000), SettingsUiState())

    fun onThemeModeSelected(themeMode: ThemeMode) {
        viewModelScope.launch { repository.setThemeMode(themeMode) }
    }

    fun onDynamicColorToggled(enabled: Boolean) {
        viewModelScope.launch { repository.setDynamicColor(enabled) }
    }
}
