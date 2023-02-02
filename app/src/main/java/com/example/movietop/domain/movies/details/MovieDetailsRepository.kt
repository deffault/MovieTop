package com.example.movietop.domain.movies.details

import com.example.movietop.utils.Resource

interface MovieDetailsRepository {
    suspend fun getDetails(movieId: Int): Resource<MovieDetails>
}