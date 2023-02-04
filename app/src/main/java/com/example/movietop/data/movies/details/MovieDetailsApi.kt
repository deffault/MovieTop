package com.example.movietop.data.movies.details

import com.example.movietop.data.movies.details.actors.ActorDTO
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieDetailsApi {
    @GET("/api/v2.2/films/{id}")
    suspend fun getMovieDetails(
        @Path("id") id: Int
    ): MovieDetailsDTO

    @GET("/api/v1/staff")
    suspend fun getActorsList(
        @Query("filmId") id: Int
    ): List<ActorDTO>

}