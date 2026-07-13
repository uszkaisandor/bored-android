package com.uszkaisandor.bored.leisure.presentation.util

import androidx.paging.PagingData
import com.uszkaisandor.bored.core.domain.result.Outcome
import com.uszkaisandor.bored.leisure.domain.LeisureActivity
import com.uszkaisandor.bored.leisure.domain.LeisureActivityType
import com.uszkaisandor.bored.leisure.domain.repository.LeisureActivityRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class FakeLeisureActivityRepository : LeisureActivityRepository {

    var randomProvider: (LeisureActivityType?) -> Flow<Outcome<LeisureActivity>> =
        { flowOf(Outcome.Success(sampleActivity)) }
    var activityProvider: (String) -> Flow<Outcome<LeisureActivity>> =
        { flowOf(Outcome.Success(sampleActivity)) }

    var setFavouriteError: Throwable? = null
    val favouriteCalls = mutableListOf<Pair<String, Boolean>>()
    var lastRequestedType: LeisureActivityType? = null

    override fun getRandom(type: LeisureActivityType?): Flow<Outcome<LeisureActivity>> {
        lastRequestedType = type
        return randomProvider(type)
    }

    override fun getActivity(id: String): Flow<Outcome<LeisureActivity>> = activityProvider(id)

    override suspend fun setIsFavourite(id: String, checked: Boolean) {
        setFavouriteError?.let { throw it }
        favouriteCalls += id to checked
    }

    override fun getFavoriteActivities(): Flow<PagingData<LeisureActivity>> =
        flowOf(PagingData.empty())

    companion object {
        val sampleActivity = LeisureActivity(
            id = "1",
            name = "Learn guitar",
            type = LeisureActivityType.MUSIC,
            participants = 1,
            accessibility = 0.3f,
            priceRange = 0.7f,
            link = null,
            isFavourite = false,
        )
    }
}
