package com.uszkaisandor.bored.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.uszkaisandor.bored.domain.LeisureActivity
import com.uszkaisandor.bored.domain.toLeisureActivity
import com.uszkaisandor.bored.network.api.BoredApi
import com.uszkaisandor.bored.persistence.dao.LeisureActivityDao
import com.uszkaisandor.bored.persistence.entity.toEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class LeisureActivityRepositoryImpl @Inject constructor(
    private val boredApi: BoredApi,
    private val leisureActivityDao: LeisureActivityDao,
) : LeisureActivityRepository {

    override fun getRandom(): Flow<LeisureActivity> = flow {
        val dto = boredApi.getActivity()
        val existingEntity = leisureActivityDao.get(dto.id).firstOrNull()

        val entity = dto.toEntity()
        if (existingEntity != null) {
            leisureActivityDao.update(entity.copy(isFavourite = existingEntity.isFavourite)) // Update the existing activity
        } else {
            leisureActivityDao.insert(entity)
        }

        val leisureActivity = leisureActivityDao.get(dto.id).filterNotNull().first().toLeisureActivity()
        emit(leisureActivity)
    }.flowOn(Dispatchers.Default)

    override suspend fun setIsFavourite(id: String, checked: Boolean) {
        leisureActivityDao.updateFavourite(id = id, favourite = checked)
    }

    override fun getFavoriteActivities(): Flow<PagingData<LeisureActivity>> = Pager(
        config = PagingConfig(pageSize = 20)
    ) {
        leisureActivityDao.getFavoriteActivities()
    }.flow.map { pagingData ->
        pagingData.map { it.toLeisureActivity() }
    }

}

