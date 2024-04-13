package com.uszkaisandor.bored.presentation.favourites

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.rememberLottieComposition
import com.uszkaisandor.bored.R

@Composable
fun EmptyListState(lottieResId: Int, title: String) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(lottieResId))
        LottieAnimation(composition, modifier = Modifier.size(200.dp))
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            modifier = Modifier.padding(horizontal = 20.dp),
            text = title,
            style = MaterialTheme.typography.titleLarge,
            textAlign = TextAlign.Center
        )
    }
}

@Preview
@Composable
fun EmptyListStatePreview() {
    MaterialTheme {
        EmptyListState(
            lottieResId = R.raw.empty_favourites,
            title = stringResource(id = R.string.favourites_empty_title)
        )
    }
}
