package com.uszkaisandor.bored.views

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.uszkaisandor.bored.R
import com.uszkaisandor.bored.domain.LeisureActivity
import com.uszkaisandor.bored.domain.toColor
import com.uszkaisandor.bored.domain.toEmoji
import com.uszkaisandor.bored.presentation.home.sampleLeisureActivity
import com.uszkaisandor.bored.ui.theme.AppTheme
import com.uszkaisandor.bored.ui.theme.ExtendedTheme
import com.uszkaisandor.bored.ui.theme.Typography

@Composable
fun LeisureActivityCard(
    leisureActivity: LeisureActivity,
    modifier: Modifier = Modifier,
) {
    val colors = ExtendedTheme.colors
    val accentColor = leisureActivity.type.toColor(colors)

    Column(
        modifier = modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .background(
                color = MaterialTheme.colorScheme.surfaceContainer,
                shape = RoundedCornerShape(16.dp)
            )
            .padding(all = 12.dp)
    ) {
        Box(
            modifier = modifier
                .height((3 * 28 + 16).dp)
                .fillMaxWidth()
        ) {
            Text(
                text = leisureActivity.name,
                modifier = Modifier
                    .align(Alignment.Center)
                    .animateContentSize()
                    .padding(horizontal = 16.dp)
                    .background(
                        color = accentColor,
                        shape = RoundedCornerShape(20.dp)
                    )
                    .padding(vertical = 8.dp, horizontal = 16.dp),
                textAlign = TextAlign.Center,
                style = Typography.titleLarge,
                color = MaterialTheme.colorScheme.onSurface
            )
        }

        Box(
            modifier = modifier
                .align(Alignment.CenterHorizontally)
                .wrapContentSize()
                .background(color = accentColor, shape = CircleShape)
                .padding(8.dp)
        ) {
            Text(
                modifier = Modifier
                    .align(Alignment.Center),
                text = leisureActivity.type.toEmoji(),
                style = MaterialTheme.typography.headlineMedium,
            )
        }

        SliderWithTitle(
            title = stringResource(id = R.string.price_range),
            value = leisureActivity.priceRange,
            range = (0f..1f),
            steps = 8,
            isEnabled = false,
            onValueChanged = {},
            modifier = modifier
                .padding(top = 16.dp, bottom = 0.dp)
        )

        SliderWithTitle(
            title = stringResource(id = R.string.accessibility),
            value = kotlin.math.abs(1 - leisureActivity.accessibility),
            range = (0f..1f),
            steps = 8,
            isEnabled = false,
            onValueChanged = {},
        )

        PersonsView(
            persons = leisureActivity.participants,
            modifier = modifier.align(Alignment.CenterHorizontally),
            color = accentColor
        )

    }
}

@Preview
@Composable
fun LeisureActivityCardPreview(leisureActivity: LeisureActivity = sampleLeisureActivity) {
    AppTheme {
        LeisureActivityCard(sampleLeisureActivity)
    }
}