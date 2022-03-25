package com.example.gb04_android_on_kotlin_movie_finder.common.domain

import com.example.gb04_android_on_kotlin_movie_finder.common.domain.model.movie.Movie
import com.example.gb04_android_on_kotlin_movie_finder.common.domain.model.pagination.PaginatedMovies

sealed class ResponseState<out T> {
    object Loading : ResponseState<Nothing>()
    data class Error(val throwable: Throwable) : ResponseState<Nothing>()
    data class Success<T>(val data: T) : ResponseState<T>()
}

typealias MovieResponseState = ResponseState<Movie>
typealias MovieCompilationResponseState = ResponseState<PaginatedMovies>