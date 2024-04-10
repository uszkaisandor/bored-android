package com.uszkaisandor.bored.network.dto

import com.uszkaisandor.bored.domain.LeisureActivityType
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class LeisureActivityDto(
    val activity: String,
    val accessibility: Float,
    val type: LeisureActivityType,
    val participants: Int,
    val price: Float,
    val link: String,
    @SerialName("key") val id: String
)
