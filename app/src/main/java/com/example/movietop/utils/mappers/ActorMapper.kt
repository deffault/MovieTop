package com.example.movietop.utils.mappers

import com.example.movietop.data.movies.details.actors.ActorDTO
import com.example.movietop.domain.movies.details.actors.Actor

fun ActorDTO.toActor() = Actor(
    id = id,
    nameRu = nameRu,
    nameEn = nameEn,
    description = description,
    posterUrl = posterUrl
)