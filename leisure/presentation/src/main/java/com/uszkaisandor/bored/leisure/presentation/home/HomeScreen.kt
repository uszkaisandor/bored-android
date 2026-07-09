package com.uszkaisandor.bored.leisure.presentation.home

import android.content.Intent
import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.hapticfeedback.HapticFeedbackType
import androidx.compose.ui.platform.LocalHapticFeedback
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.uszkaisandor.bored.core.designsystem.AppTheme
import com.uszkaisandor.bored.core.designsystem.Typography
import com.uszkaisandor.bored.core.ui.BaseScreen
import com.uszkaisandor.bored.leisure.domain.LeisureActivity
import com.uszkaisandor.bored.leisure.domain.LeisureActivityType
import com.uszkaisandor.bored.leisure.presentation.R
import com.uszkaisandor.bored.leisure.presentation.views.LeisureActivityCard
import org.koin.androidx.compose.koinViewModel

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = koinViewModel()
) {
    BaseScreen(viewModel = viewModel) { uiState ->
        val openUrlLauncher = rememberLauncherForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) { _ -> }
        val haptic = LocalHapticFeedback.current

        Column(
            modifier = modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background),
        ) {
            Box(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth()
            ) {
                if (uiState.hasError) {
                    ErrorContent(modifier = Modifier.align(Alignment.Center))
                } else {
                    AnimatedContent(
                        targetState = uiState.currentLeisureActivity,
                        modifier = Modifier.align(Alignment.TopCenter),
                        transitionSpec = {
                            (fadeIn() + scaleIn(initialScale = 0.92f)) togetherWith fadeOut()
                        },
                        contentKey = { it?.id },
                        label = "activity-reveal",
                    ) { activity ->
                        LeisureActivityCard(
                            modifier = Modifier.padding(20.dp),
                            leisureActivity = activity,
                            onFavouriteChecked = viewModel::onFavouriteChecked,
                            onLinkClicked = {
                                openUrlLauncher.launch(Intent(Intent.ACTION_VIEW, Uri.parse(it)))
                            }
                        )
                    }
                }
            }

            Button(
                modifier = Modifier
                    .padding(start = 20.dp, end = 20.dp, bottom = 20.dp)
                    .height(56.dp)
                    .fillMaxWidth(),
                onClick = {
                    haptic.performHapticFeedback(HapticFeedbackType.Confirm)
                    viewModel.onButtonClicked()
                }
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

@Composable
private fun ErrorContent(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier.padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(text = "😕", fontSize = 48.sp)
        Spacer(modifier = Modifier.height(12.dp))
        Text(
            text = stringResource(id = R.string.error_title),
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.onBackground,
            textAlign = TextAlign.Center,
        )
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
