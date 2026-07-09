package com.uszkaisandor.bored.leisure.presentation.favourites

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import com.uszkaisandor.bored.leisure.presentation.R
import org.koin.androidx.compose.koinViewModel

@Composable
fun FavouriteActivitiesScreen(
    viewModel: FavouriteActivitiesViewModel = koinViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()

    val pagingItems = viewModel.activityPagingDataFlow.collectAsLazyPagingItems()

    Box(modifier = Modifier.fillMaxSize()) {
        if (pagingItems.loadState.refresh is LoadState.Loading && pagingItems.itemCount == 0) {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
        } else {
            if (pagingItems.itemCount == 0) {
                EmptyListState(
                    lottieResId = R.raw.empty_favourites,
                    title = stringResource(id = R.string.favourites_empty_title)
                )
            } else {
                FavouriteActivitiesList(
                    pagingItems = pagingItems,
                    onSwipedToDelete = viewModel::onSwipedToDelete
                )
            }
        }
    }
}
