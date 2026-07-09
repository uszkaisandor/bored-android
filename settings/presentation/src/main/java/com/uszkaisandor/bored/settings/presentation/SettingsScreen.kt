package com.uszkaisandor.bored.settings.presentation

import android.os.Build
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SegmentedButton
import androidx.compose.material3.SegmentedButtonDefaults
import androidx.compose.material3.SingleChoiceSegmentedButtonRow
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.uszkaisandor.bored.core.domain.preferences.ThemeMode
import com.uszkaisandor.bored.core.ui.BaseScreen
import com.uszkaisandor.bored.core.ui.rememberAppHaptics
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsScreen(
    modifier: Modifier = Modifier,
    viewModel: SettingsViewModel = koinViewModel(),
) {
    BaseScreen(viewModel = viewModel) { state ->
        val haptics = rememberAppHaptics()

        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(20.dp),
            verticalArrangement = Arrangement.spacedBy(28.dp),
        ) {
            Text(
                text = stringResource(R.string.settings_title),
                style = MaterialTheme.typography.titleLarge,
            )

            Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
                Text(
                    text = stringResource(R.string.settings_theme),
                    style = MaterialTheme.typography.titleMedium,
                )
                val modes = listOf(ThemeMode.SYSTEM, ThemeMode.LIGHT, ThemeMode.DARK)
                SingleChoiceSegmentedButtonRow(modifier = Modifier.fillMaxWidth()) {
                    modes.forEachIndexed { index, mode ->
                        SegmentedButton(
                            selected = state.themeMode == mode,
                            onClick = {
                                haptics.tick()
                                viewModel.onThemeModeSelected(mode)
                            },
                            shape = SegmentedButtonDefaults.itemShape(index, modes.size),
                        ) {
                            Text(stringResource(mode.labelRes()))
                        }
                    }
                }
            }

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(16.dp),
                ) {
                    Column(modifier = Modifier.weight(1f)) {
                        Text(
                            text = stringResource(R.string.settings_dynamic_color),
                            style = MaterialTheme.typography.titleMedium,
                        )
                        Text(
                            text = stringResource(R.string.settings_dynamic_color_desc),
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.onSurfaceVariant,
                        )
                    }
                    Switch(
                        checked = state.dynamicColor,
                        onCheckedChange = {
                            haptics.tick()
                            viewModel.onDynamicColorToggled(it)
                        },
                    )
                }
            }
        }
    }
}

private fun ThemeMode.labelRes(): Int = when (this) {
    ThemeMode.SYSTEM -> R.string.theme_system
    ThemeMode.LIGHT -> R.string.theme_light
    ThemeMode.DARK -> R.string.theme_dark
}
