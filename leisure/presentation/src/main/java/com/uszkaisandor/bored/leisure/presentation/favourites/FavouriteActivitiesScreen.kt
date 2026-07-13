package com.uszkaisandor.bored.leisure.presentation.favourites

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import com.uszkaisandor.bored.leisure.presentation.R
import org.koin.androidx.compose.koinViewModel

@Composable
fun FavouriteActivitiesScreen(
    onActivityClick: (String) -> Unit,
    viewModel: FavouriteActivitiesViewModel = koinViewModel(),
) {
    val pagingItems = viewModel.activityPagingDataFlow.collectAsLazyPagingItems()
    val refresh = pagingItems.loadState.refresh

    Box(modifier = Modifier.fillMaxSize()) {
        when {
            refresh is LoadState.Loading && pagingItems.itemCount == 0 ->
                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))

            refresh is LoadState.Error && pagingItems.itemCount == 0 ->
                FavouritesError(
                    onRetry = pagingItems::retry,
                    modifier = Modifier.align(Alignment.Center),
                )

            pagingItems.itemCount == 0 -> EmptyListState(
                lottieResId = R.raw.empty_favourites,
                title = stringResource(id = R.string.favourites_empty_title)
            )

            else -> FavouriteActivitiesList(
                pagingItems = pagingItems,
                onSwipedToDelete = viewModel::onSwipedToDelete,
                onActivityClick = onActivityClick,
            )
        }
    }
}

@Composable
private fun FavouritesError(
    onRetry: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier.padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        Text(
            text = stringResource(id = R.string.error_title),
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.onBackground,
            textAlign = TextAlign.Center,
        )
        OutlinedButton(onClick = onRetry) {
            Text(text = stringResource(id = R.string.retry))
        }
    }
}
