package com.uszkaisandor.bored.persistence.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.uszkaisandor.bored.domain.LeisureActivityType
import com.uszkaisandor.bored.network.dto.LeisureActivityDto

@Entity(tableName = "activities")
data class LeisureActivityEntity(
    @PrimaryKey val id: String,
    val name: String,
    val type: LeisureActivityType,
    val participants: Int,
    val accessibility: Float,
    val priceRange: Float,
    val link: String? = null,
    val isFavourite: Boolean
)

fun LeisureActivityDto.toEntity() = LeisureActivityEntity(
    id = id,
    name = name,
    type = type,
    participants = participants,
    accessibility = accessibility,
    priceRange = price,
    link = link,
    isFavourite = false
)