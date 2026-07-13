package com.uszkaisandor.bored.leisure.presentation.navigation

import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.animation.SharedTransitionScope.ResizeMode
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.runtime.Composable
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.navigation3.ui.LocalNavAnimatedContentScope

/**
 * The [SharedTransitionScope] from the app's `SharedTransitionLayout`, exposed so
 * feature screens can opt into shared-element transitions. Null when no layout
 * is present (e.g. previews), in which case [sharedActivityTitle] is a no-op.
 */
@OptIn(ExperimentalSharedTransitionApi::class)
val LocalSharedTransitionScope = compositionLocalOf<SharedTransitionScope?> { null }

/** Keyed to an activity's title, matched between the favourites row and the detail header. */
fun activityTitleKey(id: String): String = "activity-title-$id"

/**
 * Marks the activity title as a shared element so it travels from the favourites row to
 * the detail header. Uses [ScaleToBounds] so the differing text style (titleMedium start ↔
 * titleLarge center) morphs smoothly instead of hard-swapping. No-op without a shared scope.
 */
@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun Modifier.sharedActivityTitle(id: String): Modifier {
    val sharedScope = LocalSharedTransitionScope.current ?: return this
    val animatedScope = LocalNavAnimatedContentScope.current
    return with(sharedScope) {
        this@sharedActivityTitle.sharedBounds(
            sharedContentState = rememberSharedContentState(activityTitleKey(id)),
            animatedVisibilityScope = animatedScope,
            resizeMode = ResizeMode.scaleToBounds(
                contentScale = ContentScale.FillWidth,
                alignment = Alignment.Center,
            ),
            enter = fadeIn(tween(200)),
            exit = fadeOut(tween(200)),
            // Land in sync with the NavDisplay's 320ms content tween.
            boundsTransform = { _, _ -> tween(durationMillis = 360, easing = FastOutSlowInEasing) },
        )
    }
}
