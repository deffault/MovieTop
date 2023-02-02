package com.example.movietop.data.movies.details

import retrofit2.http.GET
import retrofit2.http.Path

interface MovieDetailsApi {
    @GET("/api/v2.2/films/{id}")
    suspend fun getMovieDetails(
        @Path("id") id: Int
    ): MovieDetailsDTO
}