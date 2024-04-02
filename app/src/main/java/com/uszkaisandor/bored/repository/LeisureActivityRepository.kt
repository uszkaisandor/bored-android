package com.uszkaisandor.bored.repository

import com.uszkaisandor.bored.domain.LeisureActivity
import com.uszkaisandor.bored.network.api.BoredApi
import com.uszkaisandor.bored.network.dto.LeisureActivityDto
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class LeisureActivityRepository @Inject constructor(
    private val boredApi: BoredApi
) {
    fun getRandom(): Flow<LeisureActivity> = flow {
        emit(boredApi.getActivity().toLeisureActivity())
    }


    private fun LeisureActivityDto.toLeisureActivity() = LeisureActivity(
        name = activity,
        accessibility = accessibility,
        type = type,
        participants = participants,
        priceRange = price,
        link = link,
        key = key
    )

}

