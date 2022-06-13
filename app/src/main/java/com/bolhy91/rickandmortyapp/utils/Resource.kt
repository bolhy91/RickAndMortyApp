package com.bolhy91.rickandmortyapp.utils

sealed class Resource<T>(val data: T? = null, val message: String? = null, val pages: Int? = null) {
    class Success<T>(data: T?, pages: Int?) : Resource<T>(data, null, pages)
    class Error<T>(message: String, data: T? = null) : Resource<T>(data, message)
    class Loading<T>(val isLoading: Boolean = true) : Resource<T>(null)
}
