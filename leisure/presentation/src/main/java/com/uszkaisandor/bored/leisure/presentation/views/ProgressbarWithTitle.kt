package com.uszkaisandor.bored.leisure.presentation.views

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun ProgressbarWithTitle(
    title: String,
    value: Float,
    modifier: Modifier = Modifier,
    color: Color = MaterialTheme.colorScheme.onSurfaceVariant,
) {
    val progress = animateFloatAsState(targetValue = value)
    Column(modifier = modifier) {
        Text(
            text = title,
            style = MaterialTheme.typography.labelLarge,
        )
        Spacer(modifier = Modifier.height(6.dp))
        LinearProgressIndicator(
            modifier = Modifier
                .fillMaxWidth()
                .height(8.dp),
            color = color,
            progress = { progress.value },
            trackColor = color.copy(alpha = 0.18f),
            strokeCap = StrokeCap.Round,
        )
    }
}

@Preview
@Composable
fun ProgressbarWithTitlePreview() {
    MaterialTheme {
        Surface {
            ProgressbarWithTitle(
                title = "Price range",
                value = 0.7f,
            )
        }
    }
}