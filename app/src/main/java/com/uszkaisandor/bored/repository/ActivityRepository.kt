package com.uszkaisandor.bored.repository

import com.uszkaisandor.bored.domain.Activity
import com.uszkaisandor.bored.network.api.BoredApi
import com.uszkaisandor.bored.network.dto.ActivityDto
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class ActivityRepository @Inject constructor(
    private val boredApi: BoredApi
) {
    fun getActivity(): Flow<Activity> = flow {
        emit(boredApi.getActivity().toActivity())
    }


    private fun ActivityDto.toActivity() = Activity(
        name = activity,
        accessibility = accessibility,
        type = type,
        participants = participants,
        price = price,
        link = link,
        key = key
    )

}

