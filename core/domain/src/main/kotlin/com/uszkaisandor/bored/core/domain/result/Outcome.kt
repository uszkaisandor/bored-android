package com.uszkaisandor.bored.core.domain.result

/**
 * A success-or-typed-failure wrapper for domain operations. Prefer this over throwing so
 * callers must handle failure explicitly and errors carry [DomainError] intent.
 */
sealed interface Outcome<out T> {
    data class Success<T>(val data: T) : Outcome<T>
    data class Failure(val error: DomainError) : Outcome<Nothing>
}

/** Maps the success value, leaving a [Outcome.Failure] untouched. */
inline fun <T, R> Outcome<T>.map(transform: (T) -> R): Outcome<R> = when (this) {
    is Outcome.Success -> Outcome.Success(transform(data))
    is Outcome.Failure -> this
}

/** Collapses both branches into a single value. */
inline fun <T, R> Outcome<T>.fold(
    onSuccess: (T) -> R,
    onFailure: (DomainError) -> R,
): R = when (this) {
    is Outcome.Success -> onSuccess(data)
    is Outcome.Failure -> onFailure(error)
}
