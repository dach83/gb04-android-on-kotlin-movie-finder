package com.example.gb04_android_on_kotlin_movie_finder.common.presentation.movie

import com.example.gb04_android_on_kotlin_movie_finder.common.domain.model.movie.MovieCompilation

sealed class MoviesHomeEvent {
    object RequestInitialMoviesCompilations: MoviesHomeEvent()
    data class RequestMoreMovies(val compilation: MovieCompilation): MoviesHomeEvent()
}
