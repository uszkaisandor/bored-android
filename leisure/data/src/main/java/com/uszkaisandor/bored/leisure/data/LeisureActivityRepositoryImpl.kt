package com.uszkaisandor.bored.leisure.data

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.uszkaisandor.bored.core.database.dao.LeisureActivityDao
import com.uszkaisandor.bored.leisure.data.mapper.toDomain
import com.uszkaisandor.bored.leisure.data.seed.LeisureActivitySeeder
import com.uszkaisandor.bored.leisure.domain.LeisureActivity
import com.uszkaisandor.bored.leisure.domain.repository.LeisureActivityRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map

class LeisureActivityRepositoryImpl(
    private val dao: LeisureActivityDao,
    private val seeder: LeisureActivitySeeder,
) : LeisureActivityRepository {

    override fun getRandom(): Flow<LeisureActivity> = flow {
        seeder.seedIfEmpty()
        val entity = dao.getRandom()
            ?: throw NoSuchElementException("No activities available in the local dataset")
        emit(entity.toDomain())
    }.flowOn(Dispatchers.Default)

    override suspend fun setIsFavourite(id: String, checked: Boolean) {
        dao.updateFavourite(id = id, favourite = checked)
    }

    override fun getFavoriteActivities(): Flow<PagingData<LeisureActivity>> = Pager(
        config = PagingConfig(pageSize = 20)
    ) {
        dao.getFavoriteActivities()
    }.flow.map { pagingData ->
        pagingData.map { it.toDomain() }
    }

}
