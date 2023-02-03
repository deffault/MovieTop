package com.example.movietop.domain.movies.details

data class MovieDetails(
    val coverUrl: String,
    val posterUrlPreview: String,
    val name: String,
    val ratingForProgress: Int,
    val ratingForText: String,
    val year: String,
    val filmLength: String,
    val genres: String,
    val description: String
)

data class Genre(
    val genre: String
)
