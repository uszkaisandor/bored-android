package com.uszkaisandor.bored.views

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun SliderWithTitle(
    title: String,
    value: Float,
    range: ClosedFloatingPointRange<Float>,
    steps: Int,
    isEnabled: Boolean,
    onValueChanged: (Float) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        Text(
            text = title,
            style = MaterialTheme.typography.labelLarge,
        )

        Slider(
            value = value,
            valueRange = range,
            enabled = isEnabled,
            colors = SliderDefaults.colors(
                disabledThumbColor = MaterialTheme.colorScheme.primary,
                disabledActiveTickColor = MaterialTheme.colorScheme.onPrimary,
                disabledInactiveTickColor = MaterialTheme.colorScheme.onSecondaryContainer,
                disabledActiveTrackColor = MaterialTheme.colorScheme.secondary,
                disabledInactiveTrackColor = MaterialTheme.colorScheme.secondaryContainer,
            ),
            steps = steps,
            onValueChange = onValueChanged,
        )
    }
}
