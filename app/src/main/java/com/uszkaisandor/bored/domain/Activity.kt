package com.uszkaisandor.bored.domain

data class Activity(
    val name: String,
    val accessibility: Float,
    val type: ActivityType,
    val participants: Int,
    val priceRange: Float,
    val link: String,
    val key: String
)
