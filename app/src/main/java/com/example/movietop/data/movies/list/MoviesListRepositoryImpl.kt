package com.example.movietop.data.movies.list

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.movietop.domain.movies.list.Film
import com.example.movietop.domain.movies.list.MoviesListRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MoviesListRepositoryImpl @Inject constructor(
    private val api: MoviesListApi,
) : MoviesListRepository {
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