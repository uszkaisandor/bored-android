package com.uszkaisandor.bored.domain

import com.uszkaisandor.bored.persistence.entity.LeisureActivityEntity

data class LeisureActivity(
    val id: String,
    val name: String,
    val type: LeisureActivityType,
    val participants: Int,
    val accessibility: Float,
    val priceRange: Float,
    val isFavourite: Boolean,
    val link: String? = null
)

fun LeisureActivityEntity.toLeisureActivity() = LeisureActivity(
    id = id,
    name = name,
    accessibility = accessibility,
    type = type,
    participants = participants,
    priceRange = priceRange,
    isFavourite = isFavourite,
    link = link
)

