package com.uszkaisandor.bored.leisure.presentation.type

import androidx.compose.ui.graphics.Color
import com.uszkaisandor.bored.core.designsystem.ExtendedColors
import com.uszkaisandor.bored.leisure.domain.LeisureActivityType
import com.uszkaisandor.bored.leisure.presentation.R

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

fun LeisureActivityType.toStringResource(): Int = when (this) {
    LeisureActivityType.EDUCATION -> R.string.education
    LeisureActivityType.RECREATION -> R.string.recreation
    LeisureActivityType.SOCIAL -> R.string.social
    LeisureActivityType.DIY -> R.string.diy
    LeisureActivityType.CHARITY -> R.string.charity
    LeisureActivityType.COOKING -> R.string.cooking
    LeisureActivityType.RELAXATION -> R.string.relaxation
    LeisureActivityType.MUSIC -> R.string.music
    LeisureActivityType.BUSYWORK -> R.string.busywork
}
