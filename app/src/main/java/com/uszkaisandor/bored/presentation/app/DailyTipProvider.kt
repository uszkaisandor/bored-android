package com.uszkaisandor.bored.presentation.app

/** Picks a stable daily index into the `daily_tips` string-array (rotates once per day). */
object DailyTipProvider {
    fun todayIndex(tipCount: Int): Int {
        if (tipCount <= 0) return 0
        val millisInDay = 24 * 60 * 60 * 1000L
        val todayEpochDay = System.currentTimeMillis() / millisInDay
        return (todayEpochDay % tipCount).toInt()
    }
}
