package com.uszkaisandor.bored.leisure.data

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.uszkaisandor.bored.core.domain.coroutines.DispatcherProvider
import com.uszkaisandor.bored.core.domain.result.DomainError
import com.uszkaisandor.bored.core.domain.result.Outcome
import com.uszkaisandor.bored.leisure.data.datasource.LeisureLocalDataSource
import com.uszkaisandor.bored.leisure.data.mapper.toDomain
import com.uszkaisandor.bored.leisure.data.mapper.toDomainError
import com.uszkaisandor.bored.leisure.data.seed.LeisureActivitySeeder
import com.uszkaisandor.bored.leisure.domain.LeisureActivity
import com.uszkaisandor.bored.leisure.domain.LeisureActivityType
import com.uszkaisandor.bored.leisure.domain.repository.LeisureActivityRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map

class LeisureActivityRepositoryImpl(
    private val localDataSource: LeisureLocalDataSource,
    private val seeder: LeisureActivitySeeder,
    private val dispatchers: DispatcherProvider,
) : LeisureActivityRepository {

    override fun getRandom(type: LeisureActivityType?): Flow<Outcome<LeisureActivity>> = flow {
        seeder.seedIfEmpty()
        val entity =
            if (type == null) localDataSource.getRandom() else localDataSource.getRandomByType(type.name)
        emit(
            if (entity == null) Outcome.Failure(DomainError.Empty)
            else Outcome.Success(entity.toDomain())
        )
    }.catch { emit(Outcome.Failure(it.toDomainError())) }.flowOn(dispatchers.io)

    override fun getActivity(id: String): Flow<Outcome<LeisureActivity>> =
        localDataSource.observeById(id)
            .map { entity ->
                if (entity == null) Outcome.Failure(DomainError.NotFound)
                else Outcome.Success(entity.toDomain())
            }
            .catch { emit(Outcome.Failure(it.toDomainError())) }
            .flowOn(dispatchers.io)

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
