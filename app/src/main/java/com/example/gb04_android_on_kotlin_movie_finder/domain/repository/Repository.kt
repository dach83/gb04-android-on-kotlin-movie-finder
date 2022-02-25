package com.example.gb04_android_on_kotlin_movie_finder.domain.repository

import com.example.gb04_android_on_kotlin_movie_finder.domain.movie.Movie
import com.example.gb04_android_on_kotlin_movie_finder.domain.movie.MovieCategory

interface Repository {

    fun getMovies(category: MovieCategory): List<Movie>

}