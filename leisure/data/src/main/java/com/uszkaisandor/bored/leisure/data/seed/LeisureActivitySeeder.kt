package com.uszkaisandor.bored.leisure.data.seed

import android.content.Context
import com.uszkaisandor.bored.core.database.dao.LeisureActivityDao
import kotlinx.serialization.json.Json

/**
 * Populates Room from the bundled `activities.json` on first run. Replaces the
 * defunct remote API as the source of activities.
 */
class LeisureActivitySeeder(
    private val context: Context,
    private val dao: LeisureActivityDao,
    private val json: Json,
) {
    suspend fun seedIfEmpty() {
        if (dao.count() > 0) return
        val text = context.assets
            .open(ASSET_NAME)
            .bufferedReader()
            .use { it.readText() }
        val items = json.decodeFromString<List<LeisureActivitySeedItem>>(text)
        dao.insertAll(items.map { it.toEntity() })
    }

    private companion object {
        const val ASSET_NAME = "activities.json"
    }
}
