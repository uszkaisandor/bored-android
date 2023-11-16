package com.uszkaisandor.bored.views

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun PersonsView(
    persons: Int,
    modifier: Modifier = Modifier
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .animateContentSize()
    ) {
        repeat((0 until persons).count()) {
            PersonIcon(modifier = modifier)
        }
    }
}