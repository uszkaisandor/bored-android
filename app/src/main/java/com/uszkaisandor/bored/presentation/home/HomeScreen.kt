package com.uszkaisandor.bored.presentation.home

import android.content.Intent
import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.ramcosta.composedestinations.annotation.Destination
import com.uszkaisandor.bored.R
import com.uszkaisandor.bored.domain.LeisureActivity
import com.uszkaisandor.bored.domain.LeisureActivityType
import com.uszkaisandor.bored.presentation.common.BaseScreen
import com.uszkaisandor.bored.ui.theme.AppTheme
import com.uszkaisandor.bored.ui.theme.Typography
import com.uszkaisandor.bored.views.LeisureActivityCard
import com.uszkaisandor.bored.vm.home.HomeViewModel

@Destination(start = true)
@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = hiltViewModel()
) {
    BaseScreen(viewModel = viewModel) { uiState ->
        val openUrlLauncher = rememberLauncherForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) { _ -> }

        Column(
            modifier = modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background),
            verticalArrangement = Arrangement.Top,
        ) {
            LeisureActivityCard(
                modifier = Modifier.padding(20.dp),
                leisureActivity = uiState.currentLeisureActivity,
                onFavouriteChecked = viewModel::onFavouriteChecked,
                onLinkClicked = {
                    openUrlLauncher.launch(
                        Intent(Intent.ACTION_VIEW, Uri.parse(it))
                    )
                }
            )
            Spacer(modifier = Modifier.weight(1f))
            Button(
                modifier = modifier
                    .padding(start = 20.dp, end = 20.dp, bottom = 20.dp)
                    .height(56.dp)
                    .fillMaxWidth(),
                onClick = { viewModel.onButtonClicked() }
            ) {
                Text(
                    text = stringResource(id = R.string.get_new_activity),
                    style = Typography.labelLarge,
                    color = MaterialTheme.colorScheme.onPrimary
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ActivityRecommendationPreviewLight() {
    AppTheme(darkTheme = false) {
        HomeScreen()
    }
}

@Preview(showBackground = true)
@Composable
fun ActivityRecommendationPreviewDark() {
    AppTheme(darkTheme = true) {
        HomeScreen()
    }
}


val sampleLeisureActivity = LeisureActivity(
    name = "Learn guitar",
    type = LeisureActivityType.MUSIC,
    accessibility = 0.3f,
    link = "https://boredapi.com",
    id = "123",
    participants = 3,
    priceRange = 0.71f,
    isFavourite = true
)