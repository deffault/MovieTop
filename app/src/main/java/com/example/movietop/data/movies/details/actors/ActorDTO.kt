package com.example.movietop.data.movies.details.actors

import com.google.gson.annotations.SerializedName

data class ActorDTO(
    @SerializedName("staffId")
    val id: Int,

    @SerializedName("nameRu")
    val nameRu: String?,

    @SerializedName("nameEn")
    val nameEn: String,

    @SerializedName("description")
    val description: String?,

    @SerializedName("posterUrl")
    val posterUrl: String?
)
