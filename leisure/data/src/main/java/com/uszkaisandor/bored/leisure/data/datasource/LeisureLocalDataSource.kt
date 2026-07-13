package com.uszkaisandor.bored.leisure.data.datasource

import androidx.paging.PagingSource
import com.uszkaisandor.bored.core.database.entity.LeisureActivityEntity
import kotlinx.coroutines.flow.Flow

/**
 * Persistence seam for leisure activities. The repository and seeder depend on this
 * abstraction instead of the Room `LeisureActivityDao` directly, so the framework DAO
 * stays confined to [RoomLeisureLocalDataSource] and both callers become unit-testable
 * with a plain fake.
 */
interface LeisureLocalDataSource {

    suspend fun getRandom(): LeisureActivityEntity?

    suspend fun getRandomByType(type: String): LeisureActivityEntity?

    fun observeById(id: String): Flow<LeisureActivityEntity?>

    suspend fun setFavourite(id: String, favourite: Boolean)

    fun favouritesPagingSource(): PagingSource<Int, LeisureActivityEntity>

    suspend fun count(): Int

    suspend fun insertAll(entities: List<LeisureActivityEntity>)
}
