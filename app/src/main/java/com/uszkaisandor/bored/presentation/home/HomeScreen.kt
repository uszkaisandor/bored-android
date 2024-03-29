package com.uszkaisandor.bored.presentation.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ramcosta.composedestinations.annotation.Destination
import com.uszkaisandor.bored.R
import com.uszkaisandor.bored.domain.Activity
import com.uszkaisandor.bored.domain.ActivityType
import com.uszkaisandor.bored.ui.theme.AppTheme
import com.uszkaisandor.bored.ui.theme.Typography
import com.uszkaisandor.bored.views.ActivityCard

@Destination(start = true)
@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    onButtonClick: () -> Unit = {},
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
        verticalArrangement = Arrangement.Top,
    ) {
        ActivityCard(
            activity = sampleActivity
        )
        Button(
            modifier = modifier
                .height(56.dp)
                .padding(start = 16.dp, end = 16.dp, bottom = 50.dp)
                .fillMaxWidth(),
            onClick = onButtonClick
        ) {
            Text(
                text = stringResource(id = R.string.get_new_activity),
                style = Typography.labelLarge,
                color = MaterialTheme.colorScheme.onPrimary
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ActivityRecommendationPreviewLight() {
    AppTheme(darkTheme = false) {
        HomeScreen()
    }
}

@Preview(showBackground = true)
@Composable
fun ActivityRecommendationPreviewDark() {
    AppTheme(darkTheme = true) {
        HomeScreen()
    }
}


val sampleActivity = Activity(
    name = "Learn guitar",
    type = ActivityType.MUSIC,
    accessibility = 0.3f,
    link = "",
    key = "",
    participants = 3,
    priceRange = 0.71f
)