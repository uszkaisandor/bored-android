package com.uszkaisandor.bored.presentation.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.ramcosta.composedestinations.annotation.Destination
import com.uszkaisandor.bored.R
import com.uszkaisandor.bored.domain.LeisureActivity
import com.uszkaisandor.bored.domain.LeisureActivityType
import com.uszkaisandor.bored.ui.theme.AppTheme
import com.uszkaisandor.bored.ui.theme.Typography
import com.uszkaisandor.bored.views.LeisureActivityCard
import com.uszkaisandor.bored.vm.home.HomeViewModel

@Destination(start = true)
@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = hiltViewModel(),
    onButtonClick: () -> Unit = {},
) {
    val uiState = viewModel.uiState.collectAsState()

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
        verticalArrangement = Arrangement.Top,
    ) {
        uiState.value.currentLeisureActivity?.let {
            LeisureActivityCard(
                leisureActivity = it
            )
        }
        Button(
            modifier = modifier
                .height(56.dp)
                .padding(start = 16.dp, end = 16.dp, bottom = 50.dp)
                .fillMaxWidth(),
            onClick = onButtonClick
        ) {
            Text(
                text = stringResource(id = R.string.get_new_activity),
                style = Typography.labelLarge,
                color = MaterialTheme.colorScheme.onBackground
            )
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
    link = "",
    key = "",
    participants = 3,
    priceRange = 0.71f
)