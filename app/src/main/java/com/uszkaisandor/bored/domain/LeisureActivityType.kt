package com.uszkaisandor.bored.domain

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
enum class LeisureActivityType {
    @SerialName("education")
    EDUCATION,

    @SerialName("recreational")
    RECREATIONAL,

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

fun LeisureActivityType.toEmoji(): String {
    return when (this) {
        LeisureActivityType.EDUCATION -> "📚"
        LeisureActivityType.RECREATIONAL -> "🎮"
        LeisureActivityType.SOCIAL -> "🤝"
        LeisureActivityType.DIY -> "🔨"
        LeisureActivityType.CHARITY -> "🤲"
        LeisureActivityType.COOKING -> "🍳"
        LeisureActivityType.RELAXATION -> "🌴"
        LeisureActivityType.MUSIC -> "🎵"
        LeisureActivityType.BUSYWORK -> "📅"
    }
}

