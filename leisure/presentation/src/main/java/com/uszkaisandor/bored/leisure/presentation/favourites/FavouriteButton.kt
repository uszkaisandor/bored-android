package com.uszkaisandor.bored.leisure.presentation.favourites

import androidx.compose.animation.Crossfade
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.uszkaisandor.bored.core.designsystem.ExtendedTheme
import com.uszkaisandor.bored.core.ui.rememberAppHaptics
import com.uszkaisandor.bored.leisure.presentation.R
import kotlinx.coroutines.launch

@Composable
fun FavouriteButton(
    isFavourite: Boolean,
    onClicked: (Boolean) -> Unit,
    modifier: Modifier = Modifier
) {
    val haptics = rememberAppHaptics()
    val scope = rememberCoroutineScope()
    val scale = remember { Animatable(1f) }

    Box(
        modifier = modifier
            .clickable(
                onClick = {
                    haptics.toggle(on = !isFavourite)
                    scope.launch {
                        scale.animateTo(
                            targetValue = 1.25f,
                            animationSpec = spring(
                                dampingRatio = Spring.DampingRatioMediumBouncy,
                                stiffness = Spring.StiffnessMedium,
                            ),
                        )
                        scale.animateTo(
                            targetValue = 1f,
                            animationSpec = spring(dampingRatio = Spring.DampingRatioMediumBouncy),
                        )
                    }
                    onClicked(!isFavourite)
                }
            )
    ) {
        Crossfade(
            modifier = Modifier
                .align(Alignment.Center)
                .padding(8.dp)
                .scale(scale.value),
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