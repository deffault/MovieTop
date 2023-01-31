package com.example.movietop.domain.movies

import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow

interface MoviesRepository {
    fun getPagedMovies(): Flow<PagingData<Film>>
}