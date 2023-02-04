package com.example.movietop.utils.providers.dispatchers

import kotlinx.coroutines.CoroutineDispatcher

interface DispatcherProvider {
    fun ui(): CoroutineDispatcher

    fun io(): CoroutineDispatcher
}