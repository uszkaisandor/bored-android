package com.uszkaisandor.bored.core.ui

import android.content.Context
import android.os.Build
import android.os.VibrationEffect
import android.os.Vibrator
import android.os.VibratorManager
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext

/**
 * Deliberate, designed haptics for the app's tactile moments. Backed by the
 * platform [Vibrator] rather than [androidx.compose.ui.hapticfeedback.HapticFeedback]
 * so the feedback is actually felt — the view-based path is often a no-op, and
 * emulators never vibrate (test on a physical device). Requires the VIBRATE permission.
 */
interface AppHaptics {
    /** A light confirmation — e.g. a new activity revealed. */
    fun confirm()

    /** A crisp double pulse — the "shuffle" of rerolling by shake. */
    fun shuffle()

    /** State change for a toggle; heavier when turning on. */
    fun toggle(on: Boolean)

    /** A subtle tick — e.g. crossing the swipe-to-delete threshold. */
    fun tick()
}

@Composable
fun rememberAppHaptics(): AppHaptics {
    val context = LocalContext.current
    return remember(context) { VibratorAppHaptics(context.applicationContext) }
}

private enum class Pulse(val fallbackMillis: Long) {
    TICK(12L),
    CLICK(20L),
    HEAVY(35L),
    DOUBLE(45L),
}

private class VibratorAppHaptics(context: Context) : AppHaptics {

    private val vibrator: Vibrator? = resolveVibrator(context)

    override fun confirm() = play(Pulse.CLICK)
    override fun shuffle() = play(Pulse.DOUBLE)
    override fun toggle(on: Boolean) = play(if (on) Pulse.HEAVY else Pulse.TICK)
    override fun tick() = play(Pulse.TICK)

    private fun play(pulse: Pulse) {
        val vibrator = vibrator ?: return
        if (!vibrator.hasVibrator()) return
        when {
            Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q -> {
                val effectId = when (pulse) {
                    Pulse.TICK -> VibrationEffect.EFFECT_TICK
                    Pulse.CLICK -> VibrationEffect.EFFECT_CLICK
                    Pulse.HEAVY -> VibrationEffect.EFFECT_HEAVY_CLICK
                    Pulse.DOUBLE -> VibrationEffect.EFFECT_DOUBLE_CLICK
                }
                vibrator.vibrate(VibrationEffect.createPredefined(effectId))
            }

            Build.VERSION.SDK_INT >= Build.VERSION_CODES.O ->
                vibrator.vibrate(
                    VibrationEffect.createOneShot(pulse.fallbackMillis, VibrationEffect.DEFAULT_AMPLITUDE)
                )

            else -> @Suppress("DEPRECATION") vibrator.vibrate(pulse.fallbackMillis)
        }
    }
}

private fun resolveVibrator(context: Context): Vibrator? =
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
        (context.getSystemService(Context.VIBRATOR_MANAGER_SERVICE) as? VibratorManager)?.defaultVibrator
    } else {
        @Suppress("DEPRECATION")
        context.getSystemService(Context.VIBRATOR_SERVICE) as? Vibrator
    }
