package com.uszkaisandor.bored.leisure.presentation.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.FilterChip
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.uszkaisandor.bored.core.ui.rememberAppHaptics
import com.uszkaisandor.bored.leisure.domain.LeisureActivityType
import com.uszkaisandor.bored.leisure.presentation.R
import com.uszkaisandor.bored.leisure.presentation.type.toIcon
import com.uszkaisandor.bored.leisure.presentation.type.toStringResource

/**
 * Horizontal category filters for the home screen. Selecting a chip constrains
 * the next random pick to that category; "All" clears the filter.
 */
@Composable
fun CategoryFilterRow(
    selectedType: LeisureActivityType?,
    onTypeSelected: (LeisureActivityType?) -> Unit,
    modifier: Modifier = Modifier,
) {
    val haptics = rememberAppHaptics()
    LazyRow(
        modifier = modifier,
        contentPadding = PaddingValues(horizontal = 20.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        item {
            FilterChip(
                selected = selectedType == null,
                onClick = {
                    haptics.tick()
                    onTypeSelected(null)
                },
                label = { Text(stringResource(R.string.filter_all)) },
            )
        }
        items(LeisureActivityType.entries) { type ->
            FilterChip(
                selected = selectedType == type,
                onClick = {
                    haptics.tick()
                    onTypeSelected(type)
                },
                label = { Text(stringResource(type.toStringResource())) },
                leadingIcon = {
                    Icon(
                        imageVector = type.toIcon(),
                        contentDescription = null,
                        modifier = Modifier.size(18.dp),
                    )
                },
            )
        }
    }
}
