package com.uszkaisandor.bored.domain

import androidx.compose.ui.graphics.Color
import com.uszkaisandor.bored.ui.theme.ExtendedColors
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

fun LeisureActivityType.toEmoji(): String = when (this) {
    LeisureActivityType.EDUCATION -> "📚"
    LeisureActivityType.RECREATION -> "🎮"
    LeisureActivityType.SOCIAL -> "🤝"
    LeisureActivityType.DIY -> "🔨"
    LeisureActivityType.CHARITY -> "🤲"
    LeisureActivityType.COOKING -> "🍳"
    LeisureActivityType.RELAXATION -> "🌴"
    LeisureActivityType.MUSIC -> "🎵"
    LeisureActivityType.BUSYWORK -> "📅"
}

fun LeisureActivityType.toColor(extendedColors: ExtendedColors): Color = when (this) {
    LeisureActivityType.EDUCATION -> extendedColors.education
    LeisureActivityType.RECREATION -> extendedColors.recreation
    LeisureActivityType.SOCIAL -> extendedColors.social
    LeisureActivityType.DIY -> extendedColors.diy
    LeisureActivityType.CHARITY -> extendedColors.charity
    LeisureActivityType.COOKING -> extendedColors.cooking
    LeisureActivityType.RELAXATION -> extendedColors.relaxation
    LeisureActivityType.MUSIC -> extendedColors.music
    LeisureActivityType.BUSYWORK -> extendedColors.busyWork
}