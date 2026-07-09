package com.uszkaisandor.bored.presentation.app

object DailyTipProvider {
    private val tips = listOf(
        "Try something new today!",
        "Take a short walk to refresh your mind.",
        "Call a friend you haven't talked to in a while.",
        "Read a few pages from a book.",
        "Write down three things you're grateful for.",
        "Listen to your favorite song.",
        "Plan a fun activity for the weekend.",
        "Drink a glass of water!",
        "Smile at someone today.",
        "Take a deep breath and relax."
    )

    fun getTipForToday(): String {
        val millisInDay = 24 * 60 * 60 * 1000L
        val todayEpochDay = System.currentTimeMillis() / millisInDay
        val index = (todayEpochDay % tips.size).toInt()
        return tips[index]
    }
}
