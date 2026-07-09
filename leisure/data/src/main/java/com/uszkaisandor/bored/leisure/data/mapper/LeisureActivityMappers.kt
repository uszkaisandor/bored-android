package com.uszkaisandor.bored.leisure.data.mapper

import com.uszkaisandor.bored.core.database.entity.LeisureActivityEntity
import com.uszkaisandor.bored.leisure.domain.LeisureActivity
import com.uszkaisandor.bored.leisure.domain.LeisureActivityType

fun LeisureActivityEntity.toDomain() = LeisureActivity(
    id = id,
    name = name,
    type = enumValueOf<LeisureActivityType>(type),
    participants = participants,
    accessibility = accessibility,
    priceRange = priceRange,
    isFavourite = isFavourite,
    link = link
)
