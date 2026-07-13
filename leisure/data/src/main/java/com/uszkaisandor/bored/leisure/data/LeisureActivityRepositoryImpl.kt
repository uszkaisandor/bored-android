package com.uszkaisandor.bored.leisure.data

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.uszkaisandor.bored.leisure.data.datasource.LeisureLocalDataSource
import com.uszkaisandor.bored.leisure.data.mapper.toDomain
import com.uszkaisandor.bored.leisure.data.seed.LeisureActivitySeeder
import com.uszkaisandor.bored.leisure.domain.LeisureActivity
import com.uszkaisandor.bored.leisure.domain.LeisureActivityType
import com.uszkaisandor.bored.leisure.domain.repository.LeisureActivityRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map

class LeisureActivityRepositoryImpl(
    private val localDataSource: LeisureLocalDataSource,
    private val seeder: LeisureActivitySeeder,
) : LeisureActivityRepository {

    override fun getRandom(type: LeisureActivityType?): Flow<LeisureActivity> = flow {
        seeder.seedIfEmpty()
        val entity =
            if (type == null) localDataSource.getRandom() else localDataSource.getRandomByType(type.name)
        entity ?: throw NoSuchElementException("No activities available for the current selection")
        emit(entity.toDomain())
    }.flowOn(Dispatchers.IO)

    override fun getActivity(id: String): Flow<LeisureActivity?> =
        localDataSource.observeById(id)
            .map { entity -> entity?.toDomain() }
            .flowOn(Dispatchers.IO)

    override suspend fun setIsFavourite(id: String, checked: Boolean) {
        localDataSource.setFavourite(id = id, favourite = checked)
    }

    override fun getFavoriteActivities(): Flow<PagingData<LeisureActivity>> = Pager(
        config = PagingConfig(pageSize = 20)
    ) {
        localDataSource.favouritesPagingSource()
    }.flow.map { pagingData ->
        pagingData.map { it.toDomain() }
    }

}
