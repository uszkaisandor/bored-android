package com.uszkaisandor.bored.presentation.categories

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.uszkaisandor.bored.domain.LeisureActivityType
import com.uszkaisandor.bored.domain.toImageUrl
import com.uszkaisandor.bored.domain.toStringResource
import com.uszkaisandor.bored.presentation.common.shimmerBrush

@Composable
fun CategoryCard(leisureActivityType: LeisureActivityType) {
    Card(shape = RoundedCornerShape(12.dp)) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(1.4f)
        ) {
            var showShimmer by remember { mutableStateOf(true) }

            AsyncImage(
                model = leisureActivityType.toImageUrl(),
                modifier = Modifier
                    .background(
                        shimmerBrush(
                            targetValue = 1200f,
                            showShimmer = showShimmer
                        )
                    ),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                onSuccess = { showShimmer = false }
            )
            Text(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .background(
                        brush = Brush.verticalGradient(
                            colors = listOf(
                                MaterialTheme.colorScheme.surface,
                                MaterialTheme.colorScheme.primaryContainer
                            ),
                        ),
                        alpha = 0.8f
                    )
                    .padding(vertical = 4.dp, horizontal = 8.dp),
                text = stringResource(
                    id = leisureActivityType.toStringResource(),
                ),
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.onSurface
            )
        }
    }

}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_NO)
@Composable
fun CategoryCardLightMode() {
    CategoryCard(leisureActivityType = LeisureActivityType.EDUCATION)
}