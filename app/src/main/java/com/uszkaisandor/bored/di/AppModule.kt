package com.uszkaisandor.bored.di

import com.uszkaisandor.bored.repository.LeisureActivityRepository
import com.uszkaisandor.bored.repository.LeisureActivityRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module(
    includes = [
        AppModule.DelegateBindings::class
    ]
)

@InstallIn(SingletonComponent::class)
class AppModule {

    @Module
    @InstallIn(SingletonComponent::class)
    interface DelegateBindings {

        @Binds
        @Singleton
        fun provideLeisureActivityRepository(delegate: LeisureActivityRepositoryImpl): LeisureActivityRepository

    }

}