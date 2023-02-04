package com.example.movietop.data.movies.details

import com.google.gson.annotations.SerializedName

data class MovieDetailsDTO(
    @SerializedName("coverUrl")
    val coverUrl: String,

    @SerializedName("posterUrlPreview")
    val posterUrlPreview: String,

    @SerializedName("nameRu")
    val name: String,

    @SerializedName("ratingKinopoisk")
    val rating: Float,

    @SerializedName("year")
    val year: String,

    @SerializedName("filmLength")
    val filmLength: Int,

    @SerializedName("genres")
    val genres: List<GenreDTO>,

    @SerializedName("description")
    val description: String
)

data class GenreDTO(
    @SerializedName("genre")
    val genre: String
)
