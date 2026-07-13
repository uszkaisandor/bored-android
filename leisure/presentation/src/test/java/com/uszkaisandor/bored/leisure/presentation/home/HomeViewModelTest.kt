package com.uszkaisandor.bored.leisure.presentation.home

import com.uszkaisandor.bored.core.domain.result.DomainError
import com.uszkaisandor.bored.core.domain.result.Outcome
import com.uszkaisandor.bored.leisure.domain.LeisureActivityType
import com.uszkaisandor.bored.leisure.presentation.home.HomeUiState.ActivityState
import com.uszkaisandor.bored.leisure.presentation.util.FakeLeisureActivityRepository
import com.uszkaisandor.bored.leisure.presentation.util.MainDispatcherRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class HomeViewModelTest {

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private val repository = FakeLeisureActivityRepository()

    @Test
    fun `initial load surfaces content`() = runTest {
        val viewModel = HomeViewModel(repository)

        val state = viewModel.uiState.value.activityState
        assertTrue(state is ActivityState.Content)
        assertEquals("1", (state as ActivityState.Content).activity.id)
    }

    @Test
    fun `a repository failure surfaces a typed error`() = runTest {
        repository.randomProvider = { flowOf(Outcome.Failure(DomainError.Empty)) }

        val viewModel = HomeViewModel(repository)

        val state = viewModel.uiState.value.activityState
        assertTrue(state is ActivityState.Error)
        assertEquals(DomainError.Empty, (state as ActivityState.Error).error)
    }

    @Test
    fun `retry recovers from an error to content`() = runTest {
        repository.randomProvider = { flowOf(Outcome.Failure(DomainError.Unknown(RuntimeException()))) }
        val viewModel = HomeViewModel(repository)
        assertTrue(viewModel.uiState.value.activityState is ActivityState.Error)

        repository.randomProvider = { flowOf(Outcome.Success(FakeLeisureActivityRepository.sampleActivity)) }
        viewModel.onRetry()

        assertTrue(viewModel.uiState.value.activityState is ActivityState.Content)
    }

    @Test
    fun `selecting a type reloads with that filter`() = runTest {
        val viewModel = HomeViewModel(repository)

        viewModel.onTypeSelected(LeisureActivityType.COOKING)

        assertEquals(LeisureActivityType.COOKING, viewModel.uiState.value.selectedType)
        assertEquals(LeisureActivityType.COOKING, repository.lastRequestedType)
    }

    @Test
    fun `favourite toggle is optimistic`() = runTest {
        val viewModel = HomeViewModel(repository)

        viewModel.onFavouriteChecked(true)

        val state = viewModel.uiState.value.activityState as ActivityState.Content
        assertTrue(state.activity.isFavourite)
        assertEquals(listOf("1" to true), repository.favouriteCalls)
    }

    @Test
    fun `favourite toggle rolls back when persistence fails`() = runTest {
        repository.setFavouriteError = RuntimeException("disk full")
        val viewModel = HomeViewModel(repository)

        viewModel.onFavouriteChecked(true)

        val state = viewModel.uiState.value.activityState as ActivityState.Content
        assertFalse(state.activity.isFavourite)
    }
}
