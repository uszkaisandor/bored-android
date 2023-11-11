package com.uszkaisandor.bored.views

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.uszkaisandor.bored.ui.theme.BoredTheme

@Composable
fun ActivityRecommendation(modifier: Modifier = Modifier) {
    Box(Modifier.fillMaxSize()) {
        Text(
            text = "Hello!",
            modifier = modifier.align(Alignment.Center)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ActivityRecommendationPreview() {
    BoredTheme {
        ActivityRecommendation()
    }
}