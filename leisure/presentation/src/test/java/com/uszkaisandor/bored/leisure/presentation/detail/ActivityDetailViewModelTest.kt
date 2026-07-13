package com.uszkaisandor.bored.leisure.presentation.detail

import com.uszkaisandor.bored.core.domain.result.DomainError
import com.uszkaisandor.bored.core.domain.result.Outcome
import com.uszkaisandor.bored.leisure.presentation.util.FakeLeisureActivityRepository
import com.uszkaisandor.bored.leisure.presentation.util.MainDispatcherRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class ActivityDetailViewModelTest {

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private val repository = FakeLeisureActivityRepository()

    @Test
    fun `content is surfaced when the activity is found`() = runTest {
        val viewModel = ActivityDetailViewModel(repository, activityId = "1")

        val state = viewModel.uiState.value
        assertTrue(state is ActivityDetailUiState.Content)
        assertEquals("1", (state as ActivityDetailUiState.Content).activity.id)
    }

    @Test
    fun `NotFound is surfaced as an error`() = runTest {
        repository.activityProvider = { flowOf(Outcome.Failure(DomainError.NotFound)) }

        val viewModel = ActivityDetailViewModel(repository, activityId = "missing")

        val state = viewModel.uiState.value
        assertTrue(state is ActivityDetailUiState.Error)
        assertEquals(DomainError.NotFound, (state as ActivityDetailUiState.Error).error)
    }

    @Test
    fun `favourite toggle delegates to the repository`() = runTest {
        val viewModel = ActivityDetailViewModel(repository, activityId = "1")

        viewModel.onFavouriteChecked(true)

        assertEquals(listOf("1" to true), repository.favouriteCalls)
    }
}
