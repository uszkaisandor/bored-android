package com.uszkaisandor.bored.leisure.domain.repository

import androidx.paging.PagingData
import com.uszkaisandor.bored.leisure.domain.LeisureActivity
import com.uszkaisandor.bored.leisure.domain.LeisureActivityType
import kotlinx.coroutines.flow.Flow

interface LeisureActivityRepository {
    /** A random activity, optionally constrained to a single [type]. */
    fun getRandom(type: LeisureActivityType? = null): Flow<LeisureActivity>
    suspend fun setIsFavourite(id: String, checked: Boolean)
    fun getFavoriteActivities(): Flow<PagingData<LeisureActivity>>
}
