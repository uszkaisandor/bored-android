package com.uszkaisandor.bored.leisure.domain

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
enum class LeisureActivityType {
    @SerialName("education")
    EDUCATION,

    @SerialName("recreational")
    RECREATION,

    @SerialName("social")
    SOCIAL,

    @SerialName("diy")
    DIY,

    @SerialName("charity")
    CHARITY,

    @SerialName("cooking")
    COOKING,

    @SerialName("relaxation")
    RELAXATION,

    @SerialName("music")
    MUSIC,

    @SerialName("busywork")
    BUSYWORK;
}
