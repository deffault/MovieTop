package com.example.movietop.domain.movies

data class Film(
    val id: Int,
    val name: String,
    val rating: String,
    val posterUrl: String,
    val posterUrlPreview: String
)