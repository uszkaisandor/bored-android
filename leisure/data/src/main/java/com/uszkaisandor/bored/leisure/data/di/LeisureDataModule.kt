package com.uszkaisandor.bored.leisure.data.di

import com.uszkaisandor.bored.leisure.data.LeisureActivityRepositoryImpl
import com.uszkaisandor.bored.leisure.data.datasource.LeisureLocalDataSource
import com.uszkaisandor.bored.leisure.data.datasource.RoomLeisureLocalDataSource
import com.uszkaisandor.bored.leisure.data.seed.ActivitySeedReader
import com.uszkaisandor.bored.leisure.data.seed.AssetActivitySeedReader
import com.uszkaisandor.bored.leisure.data.seed.LeisureActivitySeeder
import com.uszkaisandor.bored.leisure.domain.repository.LeisureActivityRepository
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val leisureDataModule = module {
    single<LeisureLocalDataSource> { RoomLeisureLocalDataSource(get()) }
    single<ActivitySeedReader> { AssetActivitySeedReader(androidContext()) }
    single { LeisureActivitySeeder(get(), get(), get()) }
    single<LeisureActivityRepository> { LeisureActivityRepositoryImpl(get(), get(), get()) }
}
