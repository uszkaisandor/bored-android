package com.uszkaisandor.bored.leisure.presentation.favourites

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.uszkaisandor.bored.core.designsystem.ExtendedTheme
import com.uszkaisandor.bored.leisure.presentation.R

@Composable
fun FavouriteButton(
    isFavourite: Boolean,
    onClicked: (Boolean) -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .clickable(
                onClick = {
                    onClicked(!isFavourite)
                }
            )
    ) {
        Crossfade(
            modifier = Modifier
                .align(Alignment.Center)
                .padding(8.dp),
            targetState = isFavourite,
        ) { isFavourite ->
            Icon(
                modifier = Modifier.fillMaxSize(),
                imageVector = if (isFavourite) {
                    Icons.Default.Favorite
                } else {
                    Icons.Outlined.FavoriteBorder
                },
                tint = ExtendedTheme.colors.favourite,
                contentDescription = stringResource(
                    if (isFavourite) R.string.remove_from_favourites
                    else R.string.add_to_favourites
                )
            )
        }

    }
}