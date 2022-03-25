package com.example.gb04_android_on_kotlin_movie_finder.common.domain.repository

import com.example.gb04_android_on_kotlin_movie_finder.common.domain.model.movie.Movie
import com.example.gb04_android_on_kotlin_movie_finder.common.domain.model.movie.MovieCompilation
import com.example.gb04_android_on_kotlin_movie_finder.common.domain.model.pagination.PaginatedMovies

interface IMovieRepository {
    suspend fun getMovieDetails(movieId: Int): Movie
    suspend fun getMoviesCompilation(compilation: MovieCompilation, page: Int = 0): PaginatedMovies
}