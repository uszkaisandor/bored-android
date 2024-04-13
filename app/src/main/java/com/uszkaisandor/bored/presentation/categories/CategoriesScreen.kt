package com.uszkaisandor.bored.presentation.categories

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.ramcosta.composedestinations.annotation.Destination
import com.uszkaisandor.bored.presentation.common.BaseScreen
import com.uszkaisandor.bored.vm.categories.CategoriesViewModel

@Destination
@Composable
fun CategoriesScreen(
    modifier: Modifier = Modifier,
    viewModel: CategoriesViewModel = hiltViewModel()
) {
    BaseScreen(
        modifier = modifier,
        viewModel = viewModel
    ) { uiState ->
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            contentPadding = PaddingValues(all = 20.dp),
            content = {
                items(uiState.categories.size) { index ->
                    CategoryCard(uiState.categories[index])
                }
            }
        )
    }
}