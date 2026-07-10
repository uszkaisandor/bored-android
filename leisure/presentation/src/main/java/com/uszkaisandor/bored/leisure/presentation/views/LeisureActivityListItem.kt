package com.uszkaisandor.bored.leisure.presentation.views

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.uszkaisandor.bored.leisure.domain.LeisureActivity
import com.uszkaisandor.bored.leisure.presentation.home.sampleLeisureActivity
import com.uszkaisandor.bored.core.designsystem.AppTheme
import com.uszkaisandor.bored.core.designsystem.Typography

@Composable
fun LeisureActivityListItem(
    leisureActivity: LeisureActivity,
    modifier: Modifier = Modifier,
    titleModifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .background(
                color = MaterialTheme.colorScheme.surfaceContainer,
                shape = RoundedCornerShape(16.dp)
            ),
        contentAlignment = Alignment.CenterStart
    ) {
        Text(
            modifier = titleModifier
                .padding(horizontal = 16.dp, vertical = 16.dp),
            text = leisureActivity.name,
            textAlign = TextAlign.Start,
            style = Typography.titleMedium,
            color = MaterialTheme.colorScheme.onSurface
        )
    }
}

@Preview
@Composable
fun LeisureActivityListItemPreview(leisureActivity: LeisureActivity = sampleLeisureActivity) {
    AppTheme {
        LeisureActivityListItem(sampleLeisureActivity)
    }
}