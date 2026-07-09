package com.uszkaisandor.bored.leisure.presentation.detail

import android.content.Intent
import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.uszkaisandor.bored.core.ui.BaseScreen
import com.uszkaisandor.bored.leisure.presentation.R
import com.uszkaisandor.bored.leisure.presentation.navigation.sharedActivityBounds
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
            when {
                state.isLoading -> CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))

                state.activity != null -> LeisureActivityCard(
                    modifier = Modifier
                        .align(Alignment.TopCenter)
                        .sharedActivityBounds(activityId),
                    leisureActivity = state.activity,
                    onFavouriteChecked = viewModel::onFavouriteChecked,
                    onLinkClicked = {
                        openUrlLauncher.launch(Intent(Intent.ACTION_VIEW, Uri.parse(it)))
                    },
                )

                else -> Text(
                    text = stringResource(id = R.string.activity_not_found),
                    style = MaterialTheme.typography.titleMedium,
                    modifier = Modifier.align(Alignment.Center),
                )
            }
        }
    }
}
