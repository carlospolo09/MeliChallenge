package com.carlospolo.melichallenge.utils

/**
 * Represents a sealed result type for handling different states of an operation.
 *
 * @param T The type of the successful result.
 */
sealed interface MeliResult<out T> {

    /**
     * Represents a successful result containing the expected data.
     *
     * @param data The data retrieved from the operation.
     */
    data class Success<T>(val data: T) : MeliResult<T>

    /**
     * Represents an error result containing an exception.
     *
     * @param exception The error that occurred during the operation.
     */
    data class Error(val exception: Throwable) : MeliResult<Nothing>

    /**
     * Represents a loading state while the operation is in progress.
     */
    data object Loading : MeliResult<Nothing>
}

/**
 * Executes the given block if the result is [MeliResult.Success].
 *
 * @param block The function to execute with the successful data.
 * @return The same instance of [MeliResult] to allow chaining.
 */
inline fun <T> MeliResult<T>.onSuccess(
    block: (T) -> Unit
): MeliResult<T> = if (this is MeliResult.Success) also { block(data) } else this

/**
 * Executes the given block if the result is [MeliResult.Error].
 *
 * @param block The function to execute with the exception.
 * @return The same instance of [MeliResult] to allow chaining.
 */
inline fun <T> MeliResult<T>.onError(
    block: (Throwable) -> Unit
): MeliResult<T> = if (this is MeliResult.Error) also { block(exception) } else this

/**
 * Executes the given block if the result is [MeliResult.Loading].
 *
 * @param block The function to execute when loading.
 * @return The same instance of [MeliResult] to allow chaining.
 */
inline fun <T> MeliResult<T>.onLoading(
    block: () -> Unit
): MeliResult<T> = if (this is MeliResult.Loading) also { block() } else this