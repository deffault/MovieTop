package com.example.movietop.domain.movies.list

import androidx.paging.PagingData
import com.example.movietop.domain.movies.Film
import kotlinx.coroutines.flow.Flow

interface MoviesListRepository {
    fun getPagedMovies(): Flow<PagingData<Film>>
}