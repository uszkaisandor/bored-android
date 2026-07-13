package com.uszkaisandor.bored.leisure.presentation.di

import com.uszkaisandor.bored.leisure.presentation.detail.ActivityDetailViewModel
import com.uszkaisandor.bored.leisure.presentation.favourites.FavouriteActivitiesViewModel
import com.uszkaisandor.bored.leisure.presentation.home.HomeViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val leisurePresentationModule = module {
    viewModel { HomeViewModel(get()) }
    viewModel { FavouriteActivitiesViewModel(get()) }
    viewModel { (id: String) -> ActivityDetailViewModel(get(), id) }
}
