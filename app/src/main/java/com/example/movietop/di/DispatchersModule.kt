package com.example.movietop.di

import com.example.movietop.utils.providers.dispatchers.DispatcherProvider
import com.example.movietop.utils.providers.dispatchers.MainDispatcherProvider
import dagger.Binds
import dagger.Module

@Module
interface DispatchersModule {
    @Binds
    fun bindDispatcher(implementation: MainDispatcherProvider): DispatcherProvider
}