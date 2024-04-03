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

fun LeisureActivityType.toImageUrl(): String = when (this) {
    LeisureActivityType.EDUCATION -> "https://thumbs.dreamstime.com/z/school-supplies-wooden-table-background-frame-made-stationery-blank-paper-card-center-education-studying-back-to-146352750.jpg"
    LeisureActivityType.RECREATION -> "https://images.pexels.com/photos/287240/pexels-photo-287240.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=2"
    LeisureActivityType.SOCIAL -> "https://images.pexels.com/photos/20770025/pexels-photo-20770025/free-photo-of-colorful-powder-over-people-at-festival.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=2"
    LeisureActivityType.DIY -> "https://images.pexels.com/photos/209235/pexels-photo-209235.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=2"
    LeisureActivityType.CHARITY -> "https://images.pexels.com/photos/6994982/pexels-photo-6994982.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=2"
    LeisureActivityType.COOKING -> "https://images.pexels.com/photos/2284166/pexels-photo-2284166.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=2"
    LeisureActivityType.RELAXATION -> "https://images.pexels.com/photos/904616/pexels-photo-904616.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=2"
    LeisureActivityType.MUSIC -> "https://images.pexels.com/photos/1389429/pexels-photo-1389429.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=2"
    LeisureActivityType.BUSYWORK -> "https://images.pexels.com/photos/3184418/pexels-photo-3184418.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=2"
}