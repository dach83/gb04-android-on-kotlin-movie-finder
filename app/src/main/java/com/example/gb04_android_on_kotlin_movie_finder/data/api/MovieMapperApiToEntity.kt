package com.example.gb04_android_on_kotlin_movie_finder.data.api

import com.example.gb04_android_on_kotlin_movie_finder.domain.entity.Movie

object MovieMapperApiToEntity {

    fun transform(movie: MovieApi): Movie =
        Movie(
            movie.id ?: 0,
            movie.title ?: "",
        )

    fun transformList(movies: List<MovieApi>?): List<Movie> =
        movies?.map { transform(it) } ?: emptyList()
}