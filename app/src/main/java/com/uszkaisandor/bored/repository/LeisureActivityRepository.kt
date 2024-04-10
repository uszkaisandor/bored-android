package com.uszkaisandor.bored.repository

import com.uszkaisandor.bored.domain.LeisureActivity
import kotlinx.coroutines.flow.Flow

interface LeisureActivityRepository {
    fun getRandom(): Flow<LeisureActivity>
    suspend fun setIsFavourite(id: String, checked: Boolean)
}