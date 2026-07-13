package com.uszkaisandor.bored.core.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "activities")
data class LeisureActivityEntity(
    @PrimaryKey val id: String,
    val name: String,
    /** Persisted as the domain enum name; enum mapping lives in the data layer. */
    val type: String,
    val participants: Int,
    val accessibility: Float,
    val priceRange: Float,
    val link: String? = null,
    val isFavourite: Boolean
)
