package com.uszkaisandor.bored.views

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.background
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
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.uszkaisandor.bored.R
import com.uszkaisandor.bored.domain.LeisureActivity
import com.uszkaisandor.bored.domain.toColor
import com.uszkaisandor.bored.domain.toEmoji
import com.uszkaisandor.bored.domain.toImageUrl
import com.uszkaisandor.bored.domain.toStringResource
import com.uszkaisandor.bored.presentation.common.shimmerBrush
import com.uszkaisandor.bored.presentation.favourites.FavouriteButton
import com.uszkaisandor.bored.presentation.home.sampleLeisureActivity
import com.uszkaisandor.bored.ui.theme.AppTheme
import com.uszkaisandor.bored.ui.theme.ExtendedTheme
import com.uszkaisandor.bored.ui.theme.Typography

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
                .background(
                    color = MaterialTheme.colorScheme.surfaceContainer,
                    shape = RoundedCornerShape(16.dp)
                )
                .padding(bottom = 16.dp)
        ) {
            val colors = ExtendedTheme.colors
            val accentColor = leisureActivity.type.toColor(colors)
            var showShimmer by remember { mutableStateOf(true) }

            Box(
                modifier = Modifier
                    .wrapContentHeight()
                    .fillMaxWidth()
            ) {
                AsyncImage(
                    model = leisureActivity.type.toImageUrl(),
                    modifier = Modifier
                        .fillMaxWidth()
                        .alpha(0.7f)
                        .aspectRatio(2f)
                        .background(
                            shimmerBrush(
                                targetValue = 1200f,
                                showShimmer = showShimmer
                            )
                        )
                        .clip(RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp)),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    onSuccess = { showShimmer = false }
                )

                FavouriteButton(
                    modifier = Modifier
                        .size(48.dp)
                        .clip(RoundedCornerShape(bottomStart = 16.dp, topEnd = 16.dp))
                        .align(Alignment.TopEnd)
                        .background(MaterialTheme.colorScheme.surfaceContainer.copy(alpha = 0.7f)),
                    isFavourite = leisureActivity.isFavourite,
                    onClicked = { isChecked ->
                        onFavouriteChecked(isChecked)
                    }
                )

                Text(
                    text = leisureActivity.name,
                    modifier = Modifier
                        .align(Alignment.BottomCenter)
                        .fillMaxWidth()
                        .animateContentSize()
                        .background(
                            brush = Brush.verticalGradient(
                                colors = listOf(
                                    MaterialTheme.colorScheme.surfaceContainer,
                                    MaterialTheme.colorScheme.surface
                                ),
                            ),
                            alpha = 0.8f
                        )
                        .padding(16.dp),
                    textAlign = TextAlign.Center,
                    style = Typography.titleLarge,
                    color = MaterialTheme.colorScheme.onBackground
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            SliderWithTitle(
                modifier = Modifier.padding(horizontal = 16.dp),
                title = stringResource(id = R.string.price_range),
                value = leisureActivity.priceRange,
                range = (0f..1f),
                steps = 8,
                isEnabled = false,
                onValueChanged = {},
            )

            SliderWithTitle(
                modifier = Modifier.padding(horizontal = 16.dp),
                title = stringResource(id = R.string.accessibility),
                value = kotlin.math.abs(1 - leisureActivity.accessibility),
                range = (0f..1f),
                steps = 8,
                isEnabled = false,
                onValueChanged = {},
            )

            PersonsView(
                persons = leisureActivity.participants,
                modifier = Modifier
                    .padding(bottom = 12.dp, start = 16.dp, end = 16.dp)
                    .fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(8.dp))

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
                        .background(
                            color = accentColor.copy(alpha = 0.7f),
                            shape = CircleShape
                        )
                        .padding(4.dp),
                    text = leisureActivity.type.toEmoji(),
                    style = MaterialTheme.typography.labelMedium,
                )
            }

            leisureActivity.link?.let {
                Spacer(modifier = Modifier.height(8.dp))
                ClickableText(
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .padding(horizontal = 16.dp, vertical = 4.dp),
                    text = AnnotatedString(
                        text = it,
                        spanStyle = SpanStyle(color = ExtendedTheme.colors.link)
                    ),
                    style = MaterialTheme.typography.bodyMedium,
                    onClick = { _ ->
                        onLinkClicked(it)
                    },
                )
            }
        }
    } ?: run {
        Box(
            modifier = Modifier
                .padding(all = 16.dp)
                .fillMaxWidth()
                .height(360.dp)
                .clip(RoundedCornerShape(16.dp))
                .background(shimmerBrush(targetValue = 1400f))
        )

    }
}

@Preview
@Composable
fun LeisureActivityCardPreview(leisureActivity: LeisureActivity = sampleLeisureActivity) {
    AppTheme {
        LeisureActivityCard(
            sampleLeisureActivity, {}, {}
        )
    }
}