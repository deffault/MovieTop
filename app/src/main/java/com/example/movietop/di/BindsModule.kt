package com.example.movietop.di

import com.example.movietop.data.movies.list.MoviesListRepositoryImpl
import com.example.movietop.domain.movies.list.MoviesListRepository
import dagger.Binds
import dagger.Module

@Module
interface BindsModule {
    @Binds
    fun bindMoviesListRepository(implementation: MoviesListRepositoryImpl): MoviesListRepository
}