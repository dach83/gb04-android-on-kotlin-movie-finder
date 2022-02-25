package com.example.gb04_android_on_kotlin_movie_finder.domain.repository

import com.example.gb04_android_on_kotlin_movie_finder.domain.entity.Movie
import com.example.gb04_android_on_kotlin_movie_finder.domain.entity.Category

interface Repository {

    fun getMovies(category: Category): List<Movie>

}