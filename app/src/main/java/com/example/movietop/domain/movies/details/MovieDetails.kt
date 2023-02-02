package com.example.movietop.domain.movies.details

data class MovieDetails(
    val coverUrl: String,
    val posterUrlPreview: String,
    val name: String,
    val rating: Float,
    val year: String,
    val filmLength: Int,
    val genres: List<Genre>,
    val description: String
)

data class Genre(
    val genre: String
)
