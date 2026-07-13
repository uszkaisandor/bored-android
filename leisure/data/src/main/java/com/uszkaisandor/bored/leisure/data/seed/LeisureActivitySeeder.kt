package com.uszkaisandor.bored.leisure.data.seed

import com.uszkaisandor.bored.leisure.data.datasource.LeisureLocalDataSource
import kotlinx.serialization.json.Json

/**
 * Populates Room from the bundled `activities.json` on first run. Replaces the
 * defunct remote API as the source of activities.
 */
class LeisureActivitySeeder(
    private val reader: ActivitySeedReader,
    private val localDataSource: LeisureLocalDataSource,
    private val json: Json,
) {
    suspend fun seedIfEmpty() {
        if (localDataSource.count() > 0) return
        val items = json.decodeFromString<List<LeisureActivitySeedItem>>(reader.readJson())
        localDataSource.insertAll(items.map { it.toEntity() })
    }
}
