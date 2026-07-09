package com.uszkaisandor.bored.settings.presentation.di

import com.uszkaisandor.bored.settings.presentation.SettingsViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val settingsPresentationModule = module {
    viewModel { SettingsViewModel(get()) }
}
