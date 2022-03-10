package com.example.gb04_android_on_kotlin_movie_finder.data

import com.example.gb04_android_on_kotlin_movie_finder.domain.entity.Category
import com.example.gb04_android_on_kotlin_movie_finder.domain.entity.Movie
import com.example.gb04_android_on_kotlin_movie_finder.domain.repository.Repository

object RepositoryImpl : Repository {

    private val movies = listOf(
        Movie(1, "Batman"),
        Movie(2, "Terminator"),
        Movie(3, "Rocky"),
        Movie(4, "Superman"),
        Movie(5, "Forest Gump")
    )

    override fun getMovies(category: Category): List<Movie> = movies.toList()

    override fun getMovie(movieId: Int): Movie? = movies.firstOrNull { it.id == movieId }
}