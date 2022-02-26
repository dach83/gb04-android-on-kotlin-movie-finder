package com.example.gb04_android_on_kotlin_movie_finder.ui.movies

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.gb04_android_on_kotlin_movie_finder.data.RepositoryImpl
import com.example.gb04_android_on_kotlin_movie_finder.domain.entity.Category
import com.example.gb04_android_on_kotlin_movie_finder.domain.entity.Movie
import com.example.gb04_android_on_kotlin_movie_finder.domain.entity.movieCategories
import com.example.gb04_android_on_kotlin_movie_finder.domain.repository.Repository

class MoviesViewModel : ViewModel() {

    private val repository: Repository = RepositoryImpl()

    private val _movieCompilations = mutableMapOf<Category, MutableLiveData<List<Movie>>>()
    val movieCompilations: Map<Category, MutableLiveData<List<Movie>>>
        get() = _movieCompilations

    init {
        for (category in movieCategories) {
            _movieCompilations[category] = MutableLiveData<List<Movie>>().apply {
                value = repository.getMovies(category)
            }
        }
    }

}