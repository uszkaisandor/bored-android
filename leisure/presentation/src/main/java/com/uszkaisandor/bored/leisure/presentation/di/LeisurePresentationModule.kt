package com.uszkaisandor.bored.leisure.presentation.di

import com.uszkaisandor.bored.leisure.presentation.favourites.FavouriteActivitiesViewModel
import com.uszkaisandor.bored.leisure.presentation.home.HomeViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val leisurePresentationModule = module {
    viewModel { HomeViewModel(get()) }
    viewModel { FavouriteActivitiesViewModel(get()) }
}
