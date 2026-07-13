package com.uszkaisandor.bored.core.domain.result

/**
 * Framework-agnostic error taxonomy surfaced by the domain layer. Repositories translate
 * low-level exceptions into these cases so the presentation layer can react to intent
 * (empty vs. not-found vs. storage failure) rather than to `Throwable` subtypes.
 */
sealed interface DomainError {
    /** A query succeeded but yielded no data (e.g. no activity matches the current filter). */
    data object Empty : DomainError

    /** A specific entity was requested but does not (or no longer) exists. */
    data object NotFound : DomainError

    /** Local persistence (Room/disk) failed. */
    data class Storage(val cause: Throwable) : DomainError

    /** Anything not otherwise classified. */
    data class Unknown(val cause: Throwable) : DomainError
}
