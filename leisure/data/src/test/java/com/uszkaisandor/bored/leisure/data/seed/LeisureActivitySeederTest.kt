package com.uszkaisandor.bored.leisure.data.seed

import com.uszkaisandor.bored.core.database.entity.LeisureActivityEntity
import com.uszkaisandor.bored.leisure.data.datasource.FakeLeisureLocalDataSource
import kotlinx.coroutines.test.runTest
import kotlinx.serialization.json.Json
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Test

class LeisureActivitySeederTest {

    private val dataSource = FakeLeisureLocalDataSource()
    private val json = Json { ignoreUnknownKeys = true }

    private val seedJson = """
        [{"id":"1","name":"Learn guitar","type":"music","participants":1,"accessibility":0.3,"priceRange":0.7}]
    """.trimIndent()

    @Test
    fun `seeds the dataset when the store is empty`() = runTest {
        val seeder = LeisureActivitySeeder(
            reader = { seedJson },
            localDataSource = dataSource,
            json = json,
        )

        seeder.seedIfEmpty()

        assertEquals(1, dataSource.stored.size)
        // Persisted as the enum name, not the serialized category key.
        assertEquals("MUSIC", dataSource.stored.single().type)
    }

    @Test
    fun `does not read or insert when the store is already populated`() = runTest {
        dataSource.stored += existingEntity
        var readCalled = false
        val seeder = LeisureActivitySeeder(
            reader = { readCalled = true; seedJson },
            localDataSource = dataSource,
            json = json,
        )

        seeder.seedIfEmpty()

        assertFalse(readCalled)
        assertEquals(1, dataSource.stored.size)
    }

    private val existingEntity = LeisureActivityEntity(
        id = "existing",
        name = "Already here",
        type = "SOCIAL",
        participants = 2,
        accessibility = 0.5f,
        priceRange = 0.5f,
        link = null,
        isFavourite = false,
    )
}
