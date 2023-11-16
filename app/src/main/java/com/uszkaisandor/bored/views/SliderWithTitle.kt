package com.uszkaisandor.bored.views

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

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
    Column {
        Text(
            text = title,
            style = MaterialTheme.typography.labelLarge,
            modifier = modifier
                .padding(horizontal = 6.dp)
        )
        Slider(
            value = value,
            valueRange = range,
            enabled = isEnabled,
            colors = SliderDefaults.colors(
                thumbColor = MaterialTheme.colorScheme.secondary,
                activeTrackColor = MaterialTheme.colorScheme.secondary,
                inactiveTrackColor = MaterialTheme.colorScheme.secondaryContainer,
            ),
            steps = steps,
            onValueChange = onValueChanged,
        )
    }
}