package com.example.movietop.utils

import com.example.movietop.data.movies.details.GenreDTO
import com.example.movietop.data.movies.details.MovieDetailsDTO
import com.example.movietop.data.movies.list.FilmDTO
import com.example.movietop.domain.movies.details.Genre
import com.example.movietop.domain.movies.details.MovieDetails
import com.example.movietop.domain.movies.list.Film

fun FilmDTO.toFilm() = Film(
    id = id,
    name = name,
    year = year,
    rating = rating,
    posterUrlPreview = posterUrlPreview
)

fun MovieDetailsDTO.toMovieDetails() = MovieDetails(
    coverUrl = coverUrl,
    posterUrlPreview = posterUrlPreview,
    name = name,
    rating = rating,
    year = year,
    filmLength = filmLength,
    genres = genres.map { dto -> dto.toGenre() },
    description = description
)

fun GenreDTO.toGenre() = Genre(
    genre = genre
)