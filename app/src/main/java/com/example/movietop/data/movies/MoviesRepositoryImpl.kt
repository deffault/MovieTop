package com.example.movietop.data.movies

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.movietop.domain.movies.Film
import com.example.movietop.domain.movies.MoviesRepository
import com.example.movietop.utils.providers.dispatchers.DispatcherProvider
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

class MoviesRepositoryImpl @Inject constructor(
    private val api: MoviesListApi,
) : MoviesRepository {
    override fun getPagedMovies(): Flow<PagingData<Film>> {
        return Pager(
            config = PagingConfig(
                pageSize = PAGE_SIZE,
                enablePlaceholders = false,
                initialLoadSize = PAGE_SIZE,
            ),
            pagingSourceFactory = { MoviesPagingSource(api = api) }
        ).flow
    }

    companion object {
        private const val PAGE_SIZE = 20 // 20 фильмов на страницу определено в апи
    }
}