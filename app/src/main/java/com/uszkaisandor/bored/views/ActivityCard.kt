package com.uszkaisandor.bored.views

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.uszkaisandor.bored.domain.Activity
import com.uszkaisandor.bored.ui.theme.BoredTheme
import com.uszkaisandor.bored.ui.theme.Typography

@Composable
fun ActivityCard(
    name: String?,
    modifier: Modifier = Modifier,
    participants: Int
) {
    ElevatedCard(
        elevation = CardDefaults.cardElevation(
            defaultElevation = 8.dp
        ),
        modifier = modifier
            .fillMaxWidth()
            .height(200.dp)
    ) {
        Column(
            modifier = modifier
                .padding(16.dp)
        ) {
            Text(
                text = name ?: "",
                modifier = Modifier
                    .fillMaxWidth(),
                textAlign = TextAlign.Center,
                style = Typography.titleLarge
            )
            Spacer(modifier = modifier.weight(1f))
            Row {
                repeat((0 until participants).count()) {
                    PersonIcon()
                }
            }
        }
    }
}

@Preview
@Composable
fun ActivityCardPreview(activity: Activity = sampleActivity) {
    BoredTheme {
        ActivityCard(activity.name, participants = activity.participants)
    }
}