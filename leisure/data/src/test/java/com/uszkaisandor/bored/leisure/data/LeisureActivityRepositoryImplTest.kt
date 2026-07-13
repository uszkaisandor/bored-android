package com.uszkaisandor.bored.leisure.data

import app.cash.turbine.test
import com.uszkaisandor.bored.core.database.entity.LeisureActivityEntity
import com.uszkaisandor.bored.core.domain.result.DomainError
import com.uszkaisandor.bored.core.domain.result.Outcome
import com.uszkaisandor.bored.leisure.data.coroutines.TestDispatcherProvider
import com.uszkaisandor.bored.leisure.data.datasource.FakeLeisureLocalDataSource
import com.uszkaisandor.bored.leisure.data.seed.ActivitySeedReader
import com.uszkaisandor.bored.leisure.data.seed.LeisureActivitySeeder
import com.uszkaisandor.bored.leisure.domain.LeisureActivityType
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import kotlinx.serialization.json.Json
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class LeisureActivityRepositoryImplTest {

    private val dataSource = FakeLeisureLocalDataSource()
    private val seeder = LeisureActivitySeeder(
        reader = ActivitySeedReader { "[]" },
        localDataSource = dataSource,
        json = Json { ignoreUnknownKeys = true },
    )

    private fun repo() = LeisureActivityRepositoryImpl(
        localDataSource = dataSource,
        seeder = seeder,
        dispatchers = TestDispatcherProvider(UnconfinedTestDispatcher()),
    )

    @Test
    fun `getRandom emits Success when an activity exists`() = runTest {
        dataSource.stored += entity("1", type = "MUSIC")

        repo().getRandom().test {
            val item = awaitItem()
            assertTrue(item is Outcome.Success)
            assertEquals("1", (item as Outcome.Success).data.id)
            awaitComplete()
        }
    }

    @Test
    fun `getRandom emits Failure Empty when no activity matches`() = runTest {
        // stored stays empty; seeder inserts nothing (reader returns "[]")
        repo().getRandom().test {
            assertEquals(Outcome.Failure(DomainError.Empty), awaitItem())
            awaitComplete()
        }
    }

    @Test
    fun `getRandom by type filters on the enum name`() = runTest {
        dataSource.stored += entity("music", type = "MUSIC")
        dataSource.stored += entity("cooking", type = "COOKING")

        repo().getRandom(LeisureActivityType.COOKING).test {
            val item = awaitItem() as Outcome.Success
            assertEquals("cooking", item.data.id)
            awaitComplete()
        }
    }

    @Test
    fun `getRandom maps a thrown exception to a Failure`() = runTest {
        dataSource.failWith = RuntimeException("boom")

        repo().getRandom().test {
            val item = awaitItem()
            assertTrue(item is Outcome.Failure)
            assertTrue((item as Outcome.Failure).error is DomainError.Unknown)
            awaitComplete()
        }
    }

    @Test
    fun `getActivity emits NotFound when the id is absent`() = runTest {
        repo().getActivity("missing").test {
            assertEquals(Outcome.Failure(DomainError.NotFound), awaitItem())
        }
    }

    @Test
    fun `getActivity emits Success once the entity is observed`() = runTest {
        repo().getActivity("1").test {
            assertEquals(Outcome.Failure(DomainError.NotFound), awaitItem())
            dataSource.emitObserved(entity("1"))
            val item = awaitItem() as Outcome.Success
            assertEquals("1", item.data.id)
        }
    }

    @Test
    fun `setIsFavourite delegates to the data source`() = runTest {
        dataSource.stored += entity("1", favourite = false)

        repo().setIsFavourite("1", true)

        assertTrue(dataSource.stored.single().isFavourite)
    }

    private fun entity(
        id: String,
        type: String = "MUSIC",
        favourite: Boolean = false,
    ) = LeisureActivityEntity(
        id = id,
        name = "Activity $id",
        type = type,
        participants = 1,
        accessibility = 0.5f,
        priceRange = 0.5f,
        link = null,
        isFavourite = favourite,
    )
}
