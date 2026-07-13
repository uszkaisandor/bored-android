package com.uszkaisandor.bored.leisure.data.datasource

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.uszkaisandor.bored.core.database.entity.LeisureActivityEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flow

/** In-memory [LeisureLocalDataSource] for unit tests — no Room, no Android. */
class FakeLeisureLocalDataSource : LeisureLocalDataSource {

    val stored = mutableListOf<LeisureActivityEntity>()

    /** When set, read operations raise this instead of returning data. */
    var failWith: Throwable? = null

    private val observed = MutableStateFlow<LeisureActivityEntity?>(null)

    fun emitObserved(entity: LeisureActivityEntity?) {
        observed.value = entity
    }

    override suspend fun getRandom(): LeisureActivityEntity? {
        failWith?.let { throw it }
        return stored.firstOrNull()
    }

    override suspend fun getRandomByType(type: String): LeisureActivityEntity? {
        failWith?.let { throw it }
        return stored.firstOrNull { it.type == type }
    }

    override fun observeById(id: String): Flow<LeisureActivityEntity?> =
        failWith?.let { error -> flow<LeisureActivityEntity?> { throw error } } ?: observed

    override suspend fun setFavourite(id: String, favourite: Boolean) {
        val index = stored.indexOfFirst { it.id == id }
        if (index >= 0) stored[index] = stored[index].copy(isFavourite = favourite)
    }

    override fun favouritesPagingSource(): PagingSource<Int, LeisureActivityEntity> =
        object : PagingSource<Int, LeisureActivityEntity>() {
            override fun getRefreshKey(state: PagingState<Int, LeisureActivityEntity>): Int? = null
            override suspend fun load(params: LoadParams<Int>) =
                LoadResult.Page<Int, LeisureActivityEntity>(
                    data = stored.filter { it.isFavourite },
                    prevKey = null,
                    nextKey = null,
                )
        }

    override suspend fun count(): Int = stored.size

    override suspend fun insertAll(entities: List<LeisureActivityEntity>) {
        stored += entities
    }
}
