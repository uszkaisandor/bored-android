package com.uszkaisandor.bored.domain

import com.uszkaisandor.bored.network.dto.LeisureActivityDto

data class LeisureActivity(
    val id: String,
    val name: String,
    val type: LeisureActivityType,
    val participants: Int,
    val accessibility: Float,
    val priceRange: Float,
    val link: String
)

fun LeisureActivityDto.toLeisureActivity() = LeisureActivity(
    name = activity,
    accessibility = accessibility,
    type = type,
    participants = participants,
    priceRange = price,
    link = link,
    id = id
)

