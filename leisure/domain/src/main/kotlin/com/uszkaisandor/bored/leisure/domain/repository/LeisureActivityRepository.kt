package com.uszkaisandor.bored.leisure.domain.repository

import androidx.paging.PagingData
import com.uszkaisandor.bored.leisure.domain.LeisureActivity
import kotlinx.coroutines.flow.Flow

interface LeisureActivityRepository {
    fun getRandom(): Flow<LeisureActivity>
    suspend fun setIsFavourite(id: String, checked: Boolean)
    fun getFavoriteActivities(): Flow<PagingData<LeisureActivity>>
}
