package com.uszkaisandor.bored.core.data.di

import com.uszkaisandor.bored.core.data.preferences.UserPreferencesRepositoryImpl
import com.uszkaisandor.bored.core.domain.coroutines.DefaultDispatcherProvider
import com.uszkaisandor.bored.core.domain.coroutines.DispatcherProvider
import com.uszkaisandor.bored.core.domain.preferences.UserPreferencesRepository
import kotlinx.serialization.json.Json
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val coreDataModule = module {
    single { Json { ignoreUnknownKeys = true } }
    single<DispatcherProvider> { DefaultDispatcherProvider() }
    single<UserPreferencesRepository> { UserPreferencesRepositoryImpl(androidContext()) }
}
