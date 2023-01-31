package com.example.movietop.data.movies

import com.example.movietop.domain.movies.MoviesRepository
import com.example.movietop.utils.providers.dispatchers.DispatcherProvider
import javax.inject.Inject

class MoviesRepositoryImpl @Inject constructor(
    private val api: MoviesListApi,
    private val dispatcherProvider: DispatcherProvider
) : MoviesRepository {
}