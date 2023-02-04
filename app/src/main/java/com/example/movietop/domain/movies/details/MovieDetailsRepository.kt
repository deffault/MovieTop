package com.example.movietop.domain.movies.details

import com.example.movietop.domain.movies.details.actors.Actor
import com.example.movietop.utils.Resource

interface MovieDetailsRepository {
    suspend fun getDetails(movieId: Int): Resource<MovieDetails>

    suspend fun getActors(movieId: Int): Resource<List<Actor>>
}