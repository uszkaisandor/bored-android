package com.uszkaisandor.bored.leisure.data.mapper

import android.database.SQLException
import com.uszkaisandor.bored.core.domain.result.DomainError
import kotlin.coroutines.cancellation.CancellationException

/** Translates a low-level failure into a [DomainError] the presentation layer can act on. */
fun Throwable.toDomainError(): DomainError = when (this) {
    is CancellationException -> throw this // never swallow coroutine cancellation
    is SQLException -> DomainError.Storage(this)
    else -> DomainError.Unknown(this)
}
