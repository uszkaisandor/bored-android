package com.uszkaisandor.bored.architecture

import android.app.Application
import com.uszkaisandor.bored.core.data.di.coreDataModule
import com.uszkaisandor.bored.core.database.di.databaseModule
import com.uszkaisandor.bored.leisure.data.di.leisureDataModule
import com.uszkaisandor.bored.leisure.presentation.di.leisurePresentationModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class BoredApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@BoredApplication)
            modules(
                coreDataModule,
                databaseModule,
                leisureDataModule,
                leisurePresentationModule,
            )
        }
    }
}
