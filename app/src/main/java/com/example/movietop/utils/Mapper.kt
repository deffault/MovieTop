package com.example.movietop.utils

import com.example.movietop.data.movies.FilmDTO
import com.example.movietop.domain.movies.Film

fun FilmDTO.toFilm() = Film(
    id = id,
    name = name,
    year = year,
    rating = rating,
    posterUrl = posterUrl,
    posterUrlPreview = posterUrlPreview
)