package com.uszkaisandor.bored.views

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
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
import com.uszkaisandor.bored.R
import com.uszkaisandor.bored.domain.Activity
import com.uszkaisandor.bored.domain.ActivityType
import com.uszkaisandor.bored.ui.theme.BoredTheme
import com.uszkaisandor.bored.ui.theme.Typography

@Composable
fun ActivityRecommendation(
    activity: Activity,
    onButtonClick: () -> Unit = {},
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(bottom = 16.dp),
        verticalArrangement = Arrangement.Top,
    ) {
        ActivityCard(
            activity = activity
        )
        Spacer(modifier = Modifier.weight(1f))
        Button(
            modifier = modifier
                .height(56.dp)
                .padding(start = 16.dp, end = 16.dp)
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
    BoredTheme(darkTheme = false) {
        ActivityRecommendation(activity = sampleActivity)
    }
}

@Preview(showBackground = true)
@Composable
fun ActivityRecommendationPreviewDark() {
    BoredTheme(darkTheme = true) {
        ActivityRecommendation(activity = sampleActivity)
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