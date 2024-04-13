package com.uszkaisandor.bored.views

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.uszkaisandor.bored.R

@Composable
fun PersonsView(
    persons: Int,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .animateContentSize(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start,
    ) {
        Text(
            modifier = Modifier
                .wrapContentSize(),
            text = stringResource(id = R.string.participants),
            style = MaterialTheme.typography.labelLarge,
        )
        repeat((0 until persons).count()) {
            PersonIcon(
                modifier = Modifier
                    .padding(start = 8.dp),
                tintColor = MaterialTheme.colorScheme.primary
            )
        }
    }
}

@Preview
@Composable
fun PersonsViewPreview() {
    MaterialTheme {
        PersonsView(persons = 3)
    }
}