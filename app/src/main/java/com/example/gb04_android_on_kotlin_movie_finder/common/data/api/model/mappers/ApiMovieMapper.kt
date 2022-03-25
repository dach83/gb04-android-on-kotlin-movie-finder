package com.example.gb04_android_on_kotlin_movie_finder.common.data.api.model.mappers

import com.example.gb04_android_on_kotlin_movie_finder.common.data.api.model.movie.compilation.ApiMovieCompilationItem
import com.example.gb04_android_on_kotlin_movie_finder.common.domain.model.movie.Movie
import javax.inject.Inject

class ApiMovieMapper @Inject constructor() : ApiMapper<ApiMovieCompilationItem, Movie> {
    override fun mapToDomain(apiEntity: ApiMovieCompilationItem): Movie {
        return Movie(
            apiEntity.id,
            apiEntity.title.orEmpty()
        )
    }
}