package com.uszkaisandor.bored.leisure.presentation.views

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
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
import com.uszkaisandor.bored.core.ui.shimmerBrush
import com.uszkaisandor.bored.leisure.domain.LeisureActivity
import com.uszkaisandor.bored.leisure.presentation.R
import com.uszkaisandor.bored.leisure.presentation.favourites.FavouriteButton
import com.uszkaisandor.bored.leisure.presentation.home.sampleLeisureActivity
import com.uszkaisandor.bored.leisure.presentation.type.toColor
import com.uszkaisandor.bored.leisure.presentation.type.toEmoji
import com.uszkaisandor.bored.leisure.presentation.type.toStringResource
import kotlin.math.abs

// Single source for the card rounding, shared with the header's top edge.
private val CardCorner = 28.dp

@Composable
fun LeisureActivityCard(
    leisureActivity: LeisureActivity?,
    onFavouriteChecked: (Boolean) -> Unit,
    onLinkClicked: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    leisureActivity?.let { activity ->
        val accentColor = activity.type.toColor(ExtendedTheme.colors)

        Surface(
            modifier = modifier
                .fillMaxWidth()
                .animateContentSize(),
            shape = RoundedCornerShape(CardCorner),
            color = MaterialTheme.colorScheme.surfaceContainer,
            tonalElevation = 2.dp,
            shadowElevation = 6.dp,
        ) {
            Column(modifier = Modifier.padding(bottom = 20.dp)) {
                ActivityHeader(
                    accentColor = accentColor,
                    emoji = activity.type.toEmoji(),
                    category = stringResource(activity.type.toStringResource()),
                    name = activity.name,
                    isFavourite = activity.isFavourite,
                    onFavouriteChecked = onFavouriteChecked,
                )

                Column(
                    modifier = Modifier.padding(top = 20.dp, start = 20.dp, end = 20.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp),
                ) {
                    ProgressbarWithTitle(
                        title = stringResource(id = R.string.price_range),
                        value = activity.priceRange,
                        color = accentColor,
                    )
                    ProgressbarWithTitle(
                        title = stringResource(id = R.string.accessibility),
                        value = abs(1 - activity.accessibility),
                        color = accentColor,
                    )
                    PersonsView(
                        persons = activity.participants,
                        modifier = Modifier.fillMaxWidth(),
                    )
                    activity.link?.let { url ->
                        Text(
                            text = stringResource(id = R.string.learn_more),
                            modifier = Modifier.clickable { onLinkClicked(url) },
                            style = MaterialTheme.typography.bodyLarge,
                            color = ExtendedTheme.colors.link,
                            textDecoration = TextDecoration.Underline,
                        )
                    }
                }
            }
        }
    } ?: Box(
        modifier = modifier
            .fillMaxWidth()
            .height(360.dp)
            .clip(RoundedCornerShape(CardCorner))
            .background(shimmerBrush(targetValue = 1400f))
    )
}

@Composable
private fun ActivityHeader(
    accentColor: Color,
    emoji: String,
    category: String,
    name: String,
    isFavourite: Boolean,
    onFavouriteChecked: (Boolean) -> Unit,
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .aspectRatio(1.9f)
            .clip(RoundedCornerShape(topStart = CardCorner, topEnd = CardCorner))
            .background(
                Brush.linearGradient(
                    colors = listOf(accentColor, accentColor.copy(alpha = 0.62f)),
                )
            )
    ) {
        // Category eyebrow — frosted pill mirroring the favourite button opposite it.
        FrostedPill(modifier = Modifier.align(Alignment.TopStart).padding(12.dp)) {
            Text(
                text = category.uppercase(),
                style = MaterialTheme.typography.labelSmall.copy(letterSpacing = 1.5.sp),
                color = MaterialTheme.colorScheme.onSurface,
            )
        }

        Text(
            text = emoji,
            fontSize = 84.sp,
            modifier = Modifier.align(Alignment.Center),
        )

        FavouriteButton(
            modifier = Modifier
                .size(48.dp)
                .align(Alignment.TopEnd)
                .padding(8.dp)
                .clip(MaterialTheme.shapes.medium)
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
                        colors = listOf(Color.Transparent, MaterialTheme.colorScheme.surfaceContainer),
                    )
                )
                .padding(horizontal = 20.dp, vertical = 14.dp),
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.titleLarge,
            color = MaterialTheme.colorScheme.onSurface,
        )
    }
}

@Composable
private fun FrostedPill(
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit,
) {
    Box(
        modifier = modifier
            .clip(MaterialTheme.shapes.small)
            .background(MaterialTheme.colorScheme.surfaceContainer.copy(alpha = 0.7f))
            .padding(horizontal = 10.dp, vertical = 5.dp),
    ) {
        content()
    }
}

@Preview
@Composable
fun LeisureActivityCardPreview() {
    AppTheme {
        LeisureActivityCard(sampleLeisureActivity, {}, {})
    }
}
