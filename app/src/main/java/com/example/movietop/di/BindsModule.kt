package com.example.movietop.di

import com.example.movietop.data.movies.MoviesRepositoryImpl
import com.example.movietop.domain.movies.MoviesRepository
import dagger.Binds
import dagger.Module

@Module
interface BindsModule {
    @Binds
    fun bindMoviesListRepository(implementation: MoviesRepositoryImpl): MoviesRepository
}