package com.uszkaisandor.bored.leisure.data.seed

import android.content.Context

/** Supplies the raw bundled dataset JSON. Abstracted so the seeder is unit-testable without assets. */
fun interface ActivitySeedReader {
    fun readJson(): String
}

/** Reads the dataset from `assets/activities.json`. */
class AssetActivitySeedReader(
    private val context: Context,
    private val assetName: String = "activities.json",
) : ActivitySeedReader {
    override fun readJson(): String =
        context.assets.open(assetName).bufferedReader().use { it.readText() }
}
