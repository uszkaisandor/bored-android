package com.uszkaisandor.bored.views

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.twotone.Person
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun PersonIcon(modifier: Modifier = Modifier, tintColor: Color) {
    Icon(
        imageVector = Icons.TwoTone.Person,
        contentDescription = null,
        tint = tintColor,
        modifier = modifier
            .size(28.dp)
            .background(Color.Transparent)
            .clip(CircleShape)
    )
}
