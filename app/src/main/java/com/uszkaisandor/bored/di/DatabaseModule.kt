package com.uszkaisandor.bored.di

import android.app.Application
import androidx.room.Room
import com.uszkaisandor.bored.persistence.database.AppDatabase
import com.uszkaisandor.bored.persistence.database.TypeConverters
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Singleton
    @Provides
    fun provideAppDatabase(
        application: Application,
        typeConverters: TypeConverters
    ) = Room.databaseBuilder(application, AppDatabase::class.java, "boredapp_database")
        .addTypeConverter(typeConverters)
        .build()

    @Provides
    @Singleton
    fun provideTypeConverters(json: Json): TypeConverters = TypeConverters(json)

    @Provides
    @Singleton
    fun provideLeisureActivityDao(database: AppDatabase) = database.leisureActivityDao()

}