package com.example.movietop.data.movies.list

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.movietop.domain.movies.list.Film
import com.example.movietop.utils.toFilm

class MoviesPagingSource(
    private val api: MoviesListApi,
) : PagingSource<Int, Film>() {

    override fun getRefreshKey(state: PagingState<Int, Film>): Int? {
        val anchorPosition = state.anchorPosition ?: return null
        val page = state.closestPageToPosition(anchorPosition) ?: return null

        return page.prevKey?.plus(1) ?: page.nextKey?.minus(1)
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Film> {
        val pageNumber: Int = params.key ?: 1
        val pageSize: Int = params.loadSize

        return try {
            val response = api.getMovies(page = pageNumber)
            val films = response.films.map { it.toFilm() }

            val prevKey = if (pageNumber == 1) null else pageNumber - 1
            val nextKey = if (films.size < pageSize || pageNumber == response.pagesCount) null else pageNumber + 1

            LoadResult.Page(films, prevKey, nextKey)
        } catch (e: Throwable) {
            LoadResult.Error(e)
        }
    }
}