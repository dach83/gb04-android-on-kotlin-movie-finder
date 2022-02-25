package com.example.gb04_android_on_kotlin_movie_finder.ui.movies

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.gb04_android_on_kotlin_movie_finder.data.RepositoryImpl
import com.example.gb04_android_on_kotlin_movie_finder.domain.entity.Movie
import com.example.gb04_android_on_kotlin_movie_finder.domain.entity.Category
import com.example.gb04_android_on_kotlin_movie_finder.domain.repository.Repository

class MoviesViewModel : ViewModel() {

    private val repository: Repository = RepositoryImpl()
    private val movies = mutableMapOf<Category, MutableLiveData<List<Movie>>>()

    init {
        for(category in Category.values()) {
            movies[category] = MutableLiveData<List<Movie>>()
            movies[category]?.value = repository.getMovies(category)
        }
    }

    fun getPosterList(category: Category): LiveData<List<Movie>> = movies[category]!!

}