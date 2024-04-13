package com.uszkaisandor.bored.views

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.uszkaisandor.bored.domain.LeisureActivity
import com.uszkaisandor.bored.presentation.home.sampleLeisureActivity
import com.uszkaisandor.bored.ui.theme.AppTheme
import com.uszkaisandor.bored.ui.theme.Typography

@Composable
fun LeisureActivityListItem(
    leisureActivity: LeisureActivity,
    onDeleteClicked: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .background(
                color = MaterialTheme.colorScheme.surfaceContainer,
                shape = RoundedCornerShape(16.dp)
            )
            .padding(horizontal = 16.dp, vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = leisureActivity.name,
            modifier = Modifier
                .weight(1f),
            textAlign = TextAlign.Start,
            style = Typography.titleMedium,
            color = MaterialTheme.colorScheme.onSurface
        )
        Spacer(modifier = modifier.width(16.dp))
        Icon(
            modifier = modifier
                .size(24.dp)
                .clickable {
                    onDeleteClicked(leisureActivity.id)
                },
            imageVector = Icons.Default.Clear,
            tint = MaterialTheme.colorScheme.error,
            contentDescription = null
        )
    }
}

@Preview
@Composable
fun LeisureActivityListItemPreview(leisureActivity: LeisureActivity = sampleLeisureActivity) {
    AppTheme {
        LeisureActivityListItem(sampleLeisureActivity, { })
    }
}