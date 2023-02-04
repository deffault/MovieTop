package com.example.movietop.domain.movies.details.actors

data class Actor(
    val id: Int,
    val nameRu: String?,
    val nameEn: String,
    val description: String?,
    val posterUrl: String?
)