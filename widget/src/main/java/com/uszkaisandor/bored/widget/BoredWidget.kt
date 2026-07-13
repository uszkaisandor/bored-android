package com.uszkaisandor.bored.widget

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.glance.Button
import androidx.glance.GlanceId
import androidx.glance.GlanceModifier
import androidx.glance.GlanceTheme
import androidx.glance.action.actionStartActivity
import androidx.glance.action.clickable
import androidx.glance.appwidget.GlanceAppWidget
import androidx.glance.appwidget.action.actionRunCallback
import androidx.glance.appwidget.provideContent
import androidx.glance.background
import androidx.glance.layout.Alignment
import androidx.glance.layout.Column
import androidx.glance.layout.Spacer
import androidx.glance.layout.fillMaxSize
import androidx.glance.layout.height
import androidx.glance.layout.padding
import androidx.glance.text.FontWeight
import androidx.glance.text.Text
import androidx.glance.text.TextStyle
import com.uszkaisandor.bored.core.domain.result.DomainError
import com.uszkaisandor.bored.core.domain.result.Outcome
import com.uszkaisandor.bored.leisure.domain.repository.LeisureActivityRepository
import kotlinx.coroutines.flow.first
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

/**
 * A home-screen widget showing one activity with a refresh action. Rendered with
 * Glance (RemoteViews under the hood), so it deliberately stays simple. Each
 * update re-queries the repository for a fresh random pick.
 *
 * Implements [KoinComponent] so the repository is injected rather than pulled from
 * the global context by hand (the widget is instantiated by the framework, not Koin).
 */
class BoredWidget : GlanceAppWidget(), KoinComponent {

    private val repository: LeisureActivityRepository by inject()

    override suspend fun provideGlance(context: Context, id: GlanceId) {
        val state = runCatching {
            when (val outcome = repository.getRandom().first()) {
                is Outcome.Success -> WidgetState.Content(outcome.data.name)
                is Outcome.Failure ->
                    if (outcome.error is DomainError.Empty) WidgetState.Empty else WidgetState.Error
            }
        }.getOrElse { WidgetState.Error }

        provideContent {
            GlanceTheme {
                WidgetContent(context, state)
            }
        }
    }
}

private sealed interface WidgetState {
    data class Content(val name: String) : WidgetState
    data object Empty : WidgetState
    data object Error : WidgetState
}

@Composable
private fun WidgetContent(context: Context, state: WidgetState) {
    // Resolve the launcher activity dynamically instead of hardcoding its class name.
    val launcherComponent = context.packageManager
        .getLaunchIntentForPackage(context.packageName)
        ?.component

    var modifier = GlanceModifier
        .fillMaxSize()
        .background(GlanceTheme.colors.background)
        .padding(16.dp)
    if (launcherComponent != null) {
        modifier = modifier.clickable(actionStartActivity(launcherComponent))
    }

    val label = when (state) {
        is WidgetState.Content -> state.name
        WidgetState.Empty -> context.getString(R.string.widget_empty)
        WidgetState.Error -> context.getString(R.string.widget_error)
    }

    Column(
        modifier = modifier,
        verticalAlignment = Alignment.Vertical.CenterVertically,
    ) {
        Text(
            text = label,
            style = TextStyle(
                color = GlanceTheme.colors.onBackground,
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium,
            ),
        )
        Spacer(GlanceModifier.height(12.dp))
        Button(
            text = context.getString(R.string.widget_refresh),
            onClick = actionRunCallback<RefreshActivityAction>(),
        )
    }
}
