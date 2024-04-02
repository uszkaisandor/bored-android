package com.uszkaisandor.bored.domain

data class LeisureActivity(
    val name: String,
    val accessibility: Float,
    val type: LeisureActivityType,
    val participants: Int,
    val priceRange: Float,
    val link: String,
    val key: String
)
