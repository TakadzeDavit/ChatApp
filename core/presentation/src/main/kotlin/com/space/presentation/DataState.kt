package com.space.presentation

import com.space.core.domain.common.NetworkError

sealed interface DataState<out T> {
    object Idle : DataState<Nothing>
    object Loading : DataState<Nothing>
    data class Success<out T>(val data: T) : DataState<T>
    data class Error(
        val errorType: NetworkError,
        val message: String? = null,
        val resourceId: Int? = null
    ) : DataState<Nothing>
}

val DataState<*>.isLoading: Boolean
    get() = this is DataState.Loading

val DataState<*>.errorMessageResId: Int?
    get() = (this as? DataState.Error)?.resourceId