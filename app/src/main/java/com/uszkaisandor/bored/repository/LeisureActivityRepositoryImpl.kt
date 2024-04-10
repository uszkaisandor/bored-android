package com.uszkaisandor.bored.repository

import com.uszkaisandor.bored.domain.LeisureActivity
import com.uszkaisandor.bored.domain.toLeisureActivity
import com.uszkaisandor.bored.network.api.BoredApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class LeisureActivityRepositoryImpl @Inject constructor(
    private val boredApi: BoredApi
) : LeisureActivityRepository {

    override fun getRandom(): Flow<LeisureActivity> = flow {
        emit(boredApi.getActivity().toLeisureActivity())
    }

    override suspend fun setIsFavourite(id: String, checked: Boolean) {

    }

}

