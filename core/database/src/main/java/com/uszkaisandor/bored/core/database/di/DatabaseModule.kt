package com.uszkaisandor.bored.core.database.di

import androidx.room.Room
import com.uszkaisandor.bored.core.database.AppDatabase
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val databaseModule = module {
    single {
        Room.databaseBuilder(androidContext(), AppDatabase::class.java, "boredapp_database")
            .build()
    }
    single { get<AppDatabase>().leisureActivityDao() }
}
