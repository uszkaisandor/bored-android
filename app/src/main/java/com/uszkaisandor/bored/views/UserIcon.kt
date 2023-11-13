package com.uszkaisandor.bored.views

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun PersonIcon() {
    Icon(
        imageVector = Icons.Default.Person,
        contentDescription = null, // Provide a meaningful description if needed
        tint = MaterialTheme.colorScheme.onSurface, // Adjust the color as needed
        modifier = Modifier
            .size(32.dp)
            .background(Color.Transparent)
            .clip(CircleShape)
    )
}
