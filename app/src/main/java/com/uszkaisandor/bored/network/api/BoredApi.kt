package com.uszkaisandor.bored.network.api

import com.uszkaisandor.bored.network.dto.LeisureActivityDto
import retrofit2.http.GET

interface BoredApi {
    @GET("/api/activity")
    suspend fun getActivity(): LeisureActivityDto
}