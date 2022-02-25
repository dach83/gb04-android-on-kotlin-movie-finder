package com.example.gb04_android_on_kotlin_movie_finder.ui.movies

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.gb04_android_on_kotlin_movie_finder.data.RepositoryImpl
import com.example.gb04_android_on_kotlin_movie_finder.domain.movie.Movie
import com.example.gb04_android_on_kotlin_movie_finder.domain.movie.MovieCategory
import com.example.gb04_android_on_kotlin_movie_finder.domain.repository.Repository

class MoviesViewModel : ViewModel() {

    private val repository: Repository = RepositoryImpl()
    private val _movies = mutableMapOf<MovieCategory, MutableLiveData<List<Movie>>>()

    init {
        for(category in MovieCategory.values()) {
            _movies[category] = MutableLiveData<List<Movie>>()
            _movies[category]?.value = repository.getMovies(category)
        }
    }

    fun getMoviesList(category: MovieCategory): LiveData<List<Movie>> = _movies[category]!!

}