package com.example.movietop.data.movies.list

import retrofit2.http.GET
import retrofit2.http.Query

interface MoviesListApi {
    @GET("api/v2.2/films/top")
    suspend fun getMovies(
        @Query("page") page: Int
    ): MoviesResponse

    companion object {
        const val MAX_PAGE_SIZE = 20
    }
}