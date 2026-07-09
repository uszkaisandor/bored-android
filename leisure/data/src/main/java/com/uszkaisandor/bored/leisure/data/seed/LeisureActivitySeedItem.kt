package com.uszkaisandor.bored.leisure.data.seed

import com.uszkaisandor.bored.core.database.entity.LeisureActivityEntity
import com.uszkaisandor.bored.leisure.domain.LeisureActivityType
import kotlinx.serialization.Serializable

/** One entry of the bundled `activities.json` dataset. */
@Serializable
data class LeisureActivitySeedItem(
    val id: String,
    val name: String,
    val type: LeisureActivityType,
    val participants: Int,
    val accessibility: Float,
    val priceRange: Float,
    val link: String? = null
)

fun LeisureActivitySeedItem.toEntity() = LeisureActivityEntity(
    id = id,
    name = name,
    type = type.name,
    participants = participants,
    accessibility = accessibility,
    priceRange = priceRange,
    link = link?.takeIf { it.isNotBlank() },
    isFavourite = false
)
