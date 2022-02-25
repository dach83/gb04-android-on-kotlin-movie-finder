package com.example.gb04_android_on_kotlin_movie_finder.data

import com.example.gb04_android_on_kotlin_movie_finder.domain.movie.Movie
import com.example.gb04_android_on_kotlin_movie_finder.domain.movie.MovieCategory
import com.example.gb04_android_on_kotlin_movie_finder.domain.repository.Repository

class RepositoryImpl : Repository {

    override fun getMovies(category: MovieCategory): List<Movie> =
        listOf(
            Movie(1, "Batman"),
            Movie(2, "Terminator"),
            Movie(3, "Rocky"),
            Movie(4, "Superman"),
            Movie(5, "Forest Gump")
        )
}