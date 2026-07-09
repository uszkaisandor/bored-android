package com.uszkaisandor.bored.leisure.presentation.navigation

import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.runtime.Composable
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.Modifier
import androidx.navigation3.ui.LocalNavAnimatedContentScope

/**
 * The [SharedTransitionScope] from the app's `SharedTransitionLayout`, exposed so
 * feature screens can opt into shared-element transitions. Null when no layout
 * is present (e.g. previews), in which case [sharedActivityBounds] is a no-op.
 */
@OptIn(ExperimentalSharedTransitionApi::class)
val LocalSharedTransitionScope = compositionLocalOf<SharedTransitionScope?> { null }

/** A shared-bounds (container transform) keyed to an activity, matched between list and detail. */
fun activityBoundsKey(id: String): String = "activity-bounds-$id"

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun Modifier.sharedActivityBounds(id: String): Modifier {
    val sharedScope = LocalSharedTransitionScope.current ?: return this
    val animatedScope = LocalNavAnimatedContentScope.current
    return with(sharedScope) {
        this@sharedActivityBounds.sharedBounds(
            sharedContentState = rememberSharedContentState(activityBoundsKey(id)),
            animatedVisibilityScope = animatedScope,
        )
    }
}
