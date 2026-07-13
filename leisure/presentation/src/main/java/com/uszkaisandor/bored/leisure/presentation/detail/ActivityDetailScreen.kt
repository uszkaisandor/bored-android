package com.uszkaisandor.bored.leisure.presentation.detail

import android.content.Intent
import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
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
import com.uszkaisandor.bored.core.domain.result.DomainError
import com.uszkaisandor.bored.core.ui.BaseScreen
import com.uszkaisandor.bored.leisure.presentation.R
import com.uszkaisandor.bored.leisure.presentation.navigation.sharedActivityTitle
import com.uszkaisandor.bored.leisure.presentation.views.LeisureActivityCard
import org.koin.androidx.compose.koinViewModel
import org.koin.core.parameter.parametersOf

@Composable
fun ActivityDetailScreen(
    activityId: String,
    modifier: Modifier = Modifier,
    viewModel: ActivityDetailViewModel = koinViewModel { parametersOf(activityId) },
) {
    BaseScreen(viewModel = viewModel) { state ->
        val openUrlLauncher = rememberLauncherForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) { _ -> }

        Box(
            modifier = modifier
                .fillMaxSize()
                .padding(20.dp),
        ) {
            when (state) {
                ActivityDetailUiState.Loading ->
                    CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))

                is ActivityDetailUiState.Content -> LeisureActivityCard(
                    modifier = Modifier.align(Alignment.TopCenter),
                    leisureActivity = state.activity,
                    onFavouriteChecked = viewModel::onFavouriteChecked,
                    onLinkClicked = {
                        openUrlLauncher.launch(Intent(Intent.ACTION_VIEW, Uri.parse(it)))
                    },
                    titleModifier = Modifier.sharedActivityTitle(activityId),
                )

                is ActivityDetailUiState.Error -> DetailError(
                    error = state.error,
                    onRetry = viewModel::onRetry,
                    modifier = Modifier.align(Alignment.Center),
                )
            }
        }
    }
}

@Composable
private fun DetailError(
    error: DomainError,
    onRetry: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        Text(
            text = stringResource(
                id = if (error is DomainError.NotFound) R.string.activity_not_found else R.string.error_title
            ),
            style = MaterialTheme.typography.titleMedium,
            textAlign = TextAlign.Center,
        )
        // A deleted activity won't come back; only offer retry for transient failures.
        if (error !is DomainError.NotFound) {
            OutlinedButton(onClick = onRetry) {
                Text(text = stringResource(id = R.string.retry))
            }
        }
    }
}
