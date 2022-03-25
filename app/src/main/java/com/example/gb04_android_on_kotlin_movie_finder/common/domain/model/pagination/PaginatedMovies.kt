package com.example.gb04_android_on_kotlin_movie_finder.common.domain.model.pagination

import com.example.gb04_android_on_kotlin_movie_finder.common.domain.model.movie.Movie

data class PaginatedMovies(
    val movies: List<Movie>,
    val pagination: Pagination
)
