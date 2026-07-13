package com.uszkaisandor.bored.leisure.data.coroutines

import com.uszkaisandor.bored.core.domain.coroutines.DispatcherProvider
import kotlinx.coroutines.CoroutineDispatcher

/** Routes every dispatcher to a single test dispatcher for deterministic execution. */
class TestDispatcherProvider(dispatcher: CoroutineDispatcher) : DispatcherProvider {
    override val io: CoroutineDispatcher = dispatcher
    override val default: CoroutineDispatcher = dispatcher
}
