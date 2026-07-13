package com.uszkaisandor.bored.leisure.data.datasource

import androidx.paging.PagingSource
import com.uszkaisandor.bored.core.database.dao.LeisureActivityDao
import com.uszkaisandor.bored.core.database.entity.LeisureActivityEntity
import kotlinx.coroutines.flow.Flow

/**
 * Room-backed [LeisureLocalDataSource]. The only place in the app that references the
 * Room `LeisureActivityDao`.
 */
internal class RoomLeisureLocalDataSource(
    private val dao: LeisureActivityDao,
) : LeisureLocalDataSource {

    override suspend fun getRandom(): LeisureActivityEntity? = dao.getRandom()

    override suspend fun getRandomByType(type: String): LeisureActivityEntity? =
        dao.getRandomByType(type)

    override fun observeById(id: String): Flow<LeisureActivityEntity?> = dao.getById(id)

    override suspend fun setFavourite(id: String, favourite: Boolean) =
        dao.updateFavourite(id = id, favourite = favourite)

    override fun favouritesPagingSource(): PagingSource<Int, LeisureActivityEntity> =
        dao.getFavoriteActivities()

    override suspend fun count(): Int = dao.count()

    override suspend fun insertAll(entities: List<LeisureActivityEntity>) =
        dao.insertAll(entities)
}
