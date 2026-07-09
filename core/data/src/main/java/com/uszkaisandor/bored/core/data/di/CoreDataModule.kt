package com.uszkaisandor.bored.core.data.di

import kotlinx.serialization.json.Json
import org.koin.dsl.module

val coreDataModule = module {
    single { Json { ignoreUnknownKeys = true } }
}
