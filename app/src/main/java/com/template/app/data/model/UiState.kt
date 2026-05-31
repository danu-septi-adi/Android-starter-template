package com.template.app.data.model

/**
 * Generic UI state wrapper for handling loading/success/error states.
 */
sealed interface UiState<out T> {
    data object Loading : UiState<Nothing>
    data class Success<T>(val data: T) : UiState<T>
    data class Error(val message: String, val throwable: Throwable? = null) : UiState<Nothing>
}

// Convenience extensions
fun <T> UiState<T>.dataOrNull(): T? = (this as? UiState.Success)?.data

fun <T> UiState<T>.isLoading(): Boolean = this is UiState.Loading

fun <T> UiState<T>.errorMessage(): String? = (this as? UiState.Error)?.message

fun <T> UiState<T>.isSuccess(): Boolean = this is UiState.Success
