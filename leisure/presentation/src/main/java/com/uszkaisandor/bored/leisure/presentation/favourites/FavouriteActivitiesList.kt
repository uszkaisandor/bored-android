package com.uszkaisandor.bored.leisure.presentation.favourites

import androidx.compose.animation.core.FastOutLinearInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.uszkaisandor.bored.leisure.presentation.R
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.itemKey
import com.uszkaisandor.bored.leisure.domain.LeisureActivity
import com.uszkaisandor.bored.leisure.presentation.navigation.sharedActivityTitle
import com.uszkaisandor.bored.leisure.presentation.views.LeisureActivityListItem
import com.uszkaisandor.bored.leisure.presentation.views.SwipeToDeleteBox

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun FavouriteActivitiesList(
    pagingItems: LazyPagingItems<LeisureActivity>,
    onSwipedToDelete: (String) -> Unit,
    onActivityClick: (String) -> Unit,
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
                    shape = MaterialTheme.shapes.medium,
                    onDelete = {
                        onSwipedToDelete(it.id)
                    }
                ) {
                    LeisureActivityListItem(
                        modifier = Modifier
                            .animateItem(
                                placementSpec = tween(
                                    durationMillis = 200,
                                    easing = FastOutLinearInEasing
                                )
                            )
                            .clickable { onActivityClick(it.id) },
                        leisureActivity = it,
                        titleModifier = Modifier.sharedActivityTitle(it.id),
                    )
                }
            }
        }
        item {
            when (pagingItems.loadState.append) {
                is LoadState.Loading ->
                    CircularProgressIndicator(modifier = Modifier.padding(16.dp))

                is LoadState.Error -> TextButton(
                    onClick = pagingItems::retry,
                    modifier = Modifier.padding(8.dp),
                ) {
                    Text(text = stringResource(id = R.string.retry))
                }

                else -> Unit
            }
        }
    }
}
