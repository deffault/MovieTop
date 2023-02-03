package com.example.movietop.utils.mappers

import com.example.movietop.data.movies.details.GenreDTO
import com.example.movietop.data.movies.details.MovieDetailsDTO
import com.example.movietop.domain.movies.details.Genre
import com.example.movietop.domain.movies.details.MovieDetails

fun MovieDetailsDTO.toMovieDetails() = MovieDetails(
    coverUrl = coverUrl,
    posterUrlPreview = posterUrlPreview,
    name = name,
    ratingForProgress = setRating(rating = rating),
    ratingForText = rating.toString(),
    year = year,
    filmLength = calcLength(length = filmLength),
    genres = getGenres(genres = this.genres.map { it.toGenre() }),
    description = description,
)

private fun GenreDTO.toGenre() = Genre(
    genre = genre
)

private fun calcLength(length: Int): String {
    val hours = length / 60
    val minutes = length % 60
    return "${hours}ч ${minutes}м"
}

private fun setRating(rating: Float): Int {
    println("VVVV ${rating}")
    return (rating * 10).toInt()
}

private fun getGenres(genres: List<Genre>): String {
    val latGenreIndex = if (genres.size > 3) 3 else genres.size
    val sb = StringBuilder()
    genres
        .subList(0, latGenreIndex)
        .forEach { genreObj ->
            sb.append("${genreObj.genre.replaceFirstChar { char -> char.titlecase() }}, ")
        }
    return sb.trim().dropLast(1).toString()
}