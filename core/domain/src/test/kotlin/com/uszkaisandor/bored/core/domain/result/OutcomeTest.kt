package com.uszkaisandor.bored.core.domain.result

import org.junit.Assert.assertEquals
import org.junit.Test

class OutcomeTest {

    @Test
    fun `map transforms success value`() {
        val result = Outcome.Success(2).map { it * 10 }
        assertEquals(Outcome.Success(20), result)
    }

    @Test
    fun `map leaves failure untouched`() {
        val failure: Outcome<Int> = Outcome.Failure(DomainError.NotFound)
        val result = failure.map { it * 10 }
        assertEquals(Outcome.Failure(DomainError.NotFound), result)
    }

    @Test
    fun `fold runs success branch on success`() {
        val label = Outcome.Success("ok").fold(onSuccess = { "S:$it" }, onFailure = { "F" })
        assertEquals("S:ok", label)
    }

    @Test
    fun `fold runs failure branch on failure`() {
        val outcome: Outcome<String> = Outcome.Failure(DomainError.Empty)
        val label = outcome.fold(onSuccess = { "S" }, onFailure = { "F:$it" })
        assertEquals("F:${DomainError.Empty}", label)
    }
}
