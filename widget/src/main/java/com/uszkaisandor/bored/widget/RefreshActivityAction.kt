package com.uszkaisandor.bored.widget

import android.content.Context
import androidx.glance.GlanceId
import androidx.glance.action.ActionParameters
import androidx.glance.appwidget.action.ActionCallback

/** Re-runs the widget's [BoredWidget.provideGlance], which picks a fresh activity. */
class RefreshActivityAction : ActionCallback {
    override suspend fun onAction(
        context: Context,
        glanceId: GlanceId,
        parameters: ActionParameters,
    ) {
        BoredWidget().update(context, glanceId)
    }
}
