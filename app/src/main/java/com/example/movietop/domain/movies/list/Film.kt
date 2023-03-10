package com.example.movietop.domain.movies.list

data class Film(
    val id: Int,
    val name: String,
    val year: String,
    val rating: String,
    val posterUrlPreview: String
)