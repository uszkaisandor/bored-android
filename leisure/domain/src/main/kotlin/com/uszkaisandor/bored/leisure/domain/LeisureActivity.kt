package com.uszkaisandor.bored.leisure.domain

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
