package com.example.gb04_android_on_kotlin_movie_finder.domain.model.poster

data class PaginatedPoster(
    val posters: List<Poster>,
    val pagination: Pagination,
)
