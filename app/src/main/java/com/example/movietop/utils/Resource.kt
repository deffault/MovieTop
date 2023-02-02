package com.example.movietop.utils

sealed class Resource<T>(private val data: T? = null, private val message: String? = null) {
    data class Success<T>(val data: T): Resource<T>(data = data, message = null)
    data class Error<T>(val message: String): Resource<T>(data = null, message = message)
    class Loading<T>: Resource<T>(data = null, message = null)
}
