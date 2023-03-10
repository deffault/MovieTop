package com.example.movietop.utils.mappers

import com.example.movietop.data.movies.list.FilmDTO
import com.example.movietop.domain.movies.list.Film

fun FilmDTO.toFilm() = Film(
    id = id,
    name = name,
    year = year,
    rating = rating,
    posterUrlPreview = posterUrlPreview
)