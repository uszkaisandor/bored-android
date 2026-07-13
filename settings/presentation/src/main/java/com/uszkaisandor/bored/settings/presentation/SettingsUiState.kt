package com.uszkaisandor.bored.settings.presentation

import com.uszkaisandor.bored.core.domain.preferences.ThemeMode
import com.uszkaisandor.bored.core.ui.UiState

data class SettingsUiState(
    val themeMode: ThemeMode = ThemeMode.SYSTEM,
    val dynamicColor: Boolean = true,
) : UiState
