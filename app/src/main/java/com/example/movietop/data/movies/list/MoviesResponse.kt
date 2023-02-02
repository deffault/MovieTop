package com.example.movietop.data.movies.list

import com.google.gson.annotations.SerializedName

data class MoviesResponse(
    @SerializedName("pagesCount")
    val pagesCount: Int,

    @SerializedName("films")
    val films: List<FilmDTO>
)

data class FilmDTO(
    @SerializedName("filmId")
    val id: Int,

    @SerializedName("nameRu")
    val name: String,

    @SerializedName("year")
    val year: String,

    @SerializedName("rating")
    val rating: String,

    @SerializedName("posterUrl")
    val posterUrl: String,

    @SerializedName("posterUrlPreview")
    val posterUrlPreview: String
)
