package com.uszkaisandor.bored.network.dto

import com.uszkaisandor.bored.domain.ActivityType
import kotlinx.serialization.Serializable

@Serializable
data class ActivityDto(
    val activity: String,
    val accessibility: Float,
    val type: ActivityType,
    val participants: Int,
    val price: Float,
    val link: String,
    val key: String
)
