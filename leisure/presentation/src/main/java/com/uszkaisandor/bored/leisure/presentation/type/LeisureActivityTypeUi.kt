package com.uszkaisandor.bored.leisure.presentation.type

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Checklist
import androidx.compose.material.icons.rounded.Groups
import androidx.compose.material.icons.rounded.Handyman
import androidx.compose.material.icons.rounded.MusicNote
import androidx.compose.material.icons.rounded.Restaurant
import androidx.compose.material.icons.rounded.School
import androidx.compose.material.icons.rounded.SelfImprovement
import androidx.compose.material.icons.rounded.SportsEsports
import androidx.compose.material.icons.rounded.VolunteerActivism
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import com.uszkaisandor.bored.core.designsystem.ExtendedColors
import com.uszkaisandor.bored.leisure.domain.LeisureActivityType
import com.uszkaisandor.bored.leisure.presentation.R

/** Crisp vector icon per category — scales sharply where an emoji glyph would pixelate. */
fun LeisureActivityType.toIcon(): ImageVector = when (this) {
    LeisureActivityType.EDUCATION -> Icons.Rounded.School
    LeisureActivityType.RECREATION -> Icons.Rounded.SportsEsports
    LeisureActivityType.SOCIAL -> Icons.Rounded.Groups
    LeisureActivityType.DIY -> Icons.Rounded.Handyman
    LeisureActivityType.CHARITY -> Icons.Rounded.VolunteerActivism
    LeisureActivityType.COOKING -> Icons.Rounded.Restaurant
    LeisureActivityType.RELAXATION -> Icons.Rounded.SelfImprovement
    LeisureActivityType.MUSIC -> Icons.Rounded.MusicNote
    LeisureActivityType.BUSYWORK -> Icons.Rounded.Checklist
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
