package com.example.gb04_android_on_kotlin_movie_finder.domain.repository

import com.example.gb04_android_on_kotlin_movie_finder.domain.entity.Category
import com.example.gb04_android_on_kotlin_movie_finder.domain.entity.Movie

interface Repository {
    fun getMovies(category: Category): List<Movie>
    fun getMovie(movieId: Int): Movie?
}