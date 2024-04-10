package com.uszkaisandor.bored.presentation.favourites

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun FavouriteButton(
    isFavourite: Boolean,
    onClicked: (Boolean) -> Unit,
    modifier: Modifier = Modifier
) {
    // todo use param later
    var isFavourite by remember {
        mutableStateOf(false)
    }
    Box(
        modifier = modifier
            .clickable(
                onClick = {
                    // todo remove later
                    isFavourite = !isFavourite
                    onClicked(!isFavourite)
                }
            )
    ) {
        Icon(
            modifier = Modifier
                .align(Alignment.Center)
                .size(32.dp),
            imageVector = if (isFavourite) {
                Icons.Default.Favorite
            } else {
                Icons.Outlined.FavoriteBorder
            },
            tint = MaterialTheme.colorScheme.error,
            contentDescription = null
        )
    }
}