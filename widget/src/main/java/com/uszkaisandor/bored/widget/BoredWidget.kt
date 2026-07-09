package com.uszkaisandor.bored.widget

import android.content.ComponentName
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
import com.uszkaisandor.bored.leisure.domain.LeisureActivity
import com.uszkaisandor.bored.leisure.domain.repository.LeisureActivityRepository
import kotlinx.coroutines.flow.first
import org.koin.core.context.GlobalContext

/**
 * A home-screen widget showing one activity with a refresh action. Rendered with
 * Glance (RemoteViews under the hood), so it deliberately stays simple. Each
 * update re-queries the repository for a fresh random pick.
 */
class BoredWidget : GlanceAppWidget() {

    override suspend fun provideGlance(context: Context, id: GlanceId) {
        val repository: LeisureActivityRepository = GlobalContext.get().get()
        val activity = runCatching { repository.getRandom().first() }.getOrNull()

        provideContent {
            GlanceTheme {
                WidgetContent(context, activity)
            }
        }
    }
}

@Composable
private fun WidgetContent(context: Context, activity: LeisureActivity?) {
    val openApp = ComponentName(
        context.packageName,
        "com.uszkaisandor.bored.architecture.MainActivity",
    )
    Column(
        modifier = GlanceModifier
            .fillMaxSize()
            .background(GlanceTheme.colors.background)
            .padding(16.dp)
            .clickable(actionStartActivity(openApp)),
        verticalAlignment = Alignment.Vertical.CenterVertically,
    ) {
        Text(
            text = activity?.name ?: context.getString(R.string.widget_empty),
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
