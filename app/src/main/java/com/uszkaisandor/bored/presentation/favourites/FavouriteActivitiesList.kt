package com.uszkaisandor.bored.presentation.favourites

import androidx.compose.animation.core.FastOutLinearInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.itemKey
import com.uszkaisandor.bored.domain.LeisureActivity
import com.uszkaisandor.bored.views.LeisureActivityListItem
import com.uszkaisandor.bored.views.SwipeToDeleteBox

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun FavouriteActivitiesList(
    pagingItems: LazyPagingItems<LeisureActivity>,
    onSwipedToDelete: (String) -> Unit
) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        state = rememberLazyListState(),
        contentPadding = PaddingValues(20.dp)
    ) {
        items(
            count = pagingItems.itemCount,
            key = pagingItems.itemKey { it.id },
        ) { index ->
            pagingItems[index]?.let {
                SwipeToDeleteBox(
                    shape = RoundedCornerShape(16.dp),
                    onDelete = {
                        onSwipedToDelete(it.id)
                    }
                ) {
                    LeisureActivityListItem(
                        modifier = Modifier.animateItemPlacement(
                            animationSpec = tween(
                                durationMillis = 200,
                                easing = FastOutLinearInEasing
                            )
                        ),
                        leisureActivity = it
                    )
                }
            }
        }
        item {
            if (pagingItems.loadState.append is LoadState.Loading) {
                CircularProgressIndicator(modifier = Modifier.padding(16.dp))
            }
        }
    }
}
