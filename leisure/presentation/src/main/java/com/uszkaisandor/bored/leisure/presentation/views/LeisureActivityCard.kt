package com.uszkaisandor.bored.leisure.presentation.views

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.uszkaisandor.bored.core.designsystem.AppTheme
import com.uszkaisandor.bored.core.designsystem.ExtendedTheme
import com.uszkaisandor.bored.core.designsystem.Typography
import com.uszkaisandor.bored.core.ui.shimmerBrush
import com.uszkaisandor.bored.leisure.domain.LeisureActivity
import com.uszkaisandor.bored.leisure.presentation.R
import com.uszkaisandor.bored.leisure.presentation.favourites.FavouriteButton
import com.uszkaisandor.bored.leisure.presentation.home.sampleLeisureActivity
import com.uszkaisandor.bored.leisure.presentation.type.toColor
import com.uszkaisandor.bored.leisure.presentation.type.toEmoji
import com.uszkaisandor.bored.leisure.presentation.type.toStringResource
import kotlin.math.abs

@Composable
fun LeisureActivityCard(
    leisureActivity: LeisureActivity?,
    onFavouriteChecked: (Boolean) -> Unit,
    onLinkClicked: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    leisureActivity?.let {
        Column(
            modifier = modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .animateContentSize()
                .clip(RoundedCornerShape(20.dp))
                .background(MaterialTheme.colorScheme.surfaceContainer)
                .padding(bottom = 16.dp)
        ) {
            val accentColor = leisureActivity.type.toColor(ExtendedTheme.colors)

            ActivityHeader(
                accentColor = accentColor,
                emoji = leisureActivity.type.toEmoji(),
                name = leisureActivity.name,
                isFavourite = leisureActivity.isFavourite,
                onFavouriteChecked = onFavouriteChecked,
            )

            Spacer(modifier = Modifier.height(16.dp))

            leisureActivity.link?.let { url ->
                Text(
                    text = stringResource(id = R.string.learn_more),
                    modifier = Modifier
                        .padding(horizontal = 16.dp, vertical = 4.dp)
                        .clickable { onLinkClicked(url) },
                    style = MaterialTheme.typography.bodyMedium,
                    color = ExtendedTheme.colors.link,
                    textDecoration = TextDecoration.Underline,
                )
            }

            ProgressbarWithTitle(
                modifier = Modifier.padding(horizontal = 16.dp),
                title = stringResource(id = R.string.price_range),
                value = leisureActivity.priceRange,
            )

            ProgressbarWithTitle(
                modifier = Modifier.padding(horizontal = 16.dp),
                title = stringResource(id = R.string.accessibility),
                value = abs(1 - leisureActivity.accessibility),
            )

            PersonsView(
                persons = leisureActivity.participants,
                modifier = Modifier
                    .padding(top = 8.dp, bottom = 12.dp, start = 16.dp, end = 16.dp)
                    .fillMaxWidth()
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
                    .wrapContentHeight(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = stringResource(
                        id = R.string.category,
                        stringResource(id = leisureActivity.type.toStringResource()),
                    ),
                    style = MaterialTheme.typography.titleSmall
                )
                Text(
                    modifier = Modifier
                        .wrapContentSize()
                        .padding(start = 8.dp)
                        .background(color = accentColor.copy(alpha = 0.7f), shape = CircleShape)
                        .padding(4.dp),
                    text = leisureActivity.type.toEmoji(),
                    style = MaterialTheme.typography.labelMedium,
                )
            }
        }
    } ?: run {
        Box(
            modifier = modifier
                .fillMaxWidth()
                .height(360.dp)
                .clip(RoundedCornerShape(20.dp))
                .background(shimmerBrush(targetValue = 1400f))
        )
    }
}

@Composable
private fun ActivityHeader(
    accentColor: Color,
    emoji: String,
    name: String,
    isFavourite: Boolean,
    onFavouriteChecked: (Boolean) -> Unit,
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .aspectRatio(1.9f)
            .clip(RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp))
            .background(
                Brush.linearGradient(
                    colors = listOf(
                        accentColor.copy(alpha = 0.9f),
                        accentColor.copy(alpha = 0.4f),
                    )
                )
            )
    ) {
        Text(
            text = emoji,
            fontSize = 88.sp,
            modifier = Modifier.align(Alignment.Center),
        )

        FavouriteButton(
            modifier = Modifier
                .size(48.dp)
                .clip(RoundedCornerShape(bottomStart = 16.dp, topEnd = 20.dp))
                .align(Alignment.TopEnd)
                .background(MaterialTheme.colorScheme.surfaceContainer.copy(alpha = 0.7f)),
            isFavourite = isFavourite,
            onClicked = onFavouriteChecked,
        )

        Text(
            text = name,
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth()
                .background(
                    Brush.verticalGradient(
                        colors = listOf(Color.Transparent, MaterialTheme.colorScheme.surfaceContainer)
                    )
                )
                .padding(horizontal = 16.dp, vertical = 12.dp),
            textAlign = TextAlign.Center,
            style = Typography.titleMedium,
            color = MaterialTheme.colorScheme.onSurface,
        )
    }
}

@Preview
@Composable
fun LeisureActivityCardPreview() {
    AppTheme {
        LeisureActivityCard(sampleLeisureActivity, {}, {})
    }
}
