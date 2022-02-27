package com.example.gb04_android_on_kotlin_movie_finder.domain

import com.example.gb04_android_on_kotlin_movie_finder.domain.entity.Movie

sealed class ResponseState<out T> {
    object Loading : ResponseState<Nothing>()
    data class Error(val throwable: Throwable) : ResponseState<Nothing>()
    data class Success<T>(val data: T) : ResponseState<T>()
}

typealias MoviesResponseState = ResponseState<List<Movie>>