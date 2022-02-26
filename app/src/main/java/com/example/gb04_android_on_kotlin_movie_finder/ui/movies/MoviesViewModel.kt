package com.example.gb04_android_on_kotlin_movie_finder.ui.movies

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.gb04_android_on_kotlin_movie_finder.data.RepositoryImpl
import com.example.gb04_android_on_kotlin_movie_finder.domain.entity.*
import com.example.gb04_android_on_kotlin_movie_finder.domain.repository.Repository
import java.lang.Thread.sleep
import kotlin.concurrent.thread

class MoviesViewModel : ViewModel() {

    private val categories = movieCategories
    private val repository: Repository = RepositoryImpl()

    private val _movieCompilations = mutableMapOf<Category, MutableLiveData<List<Movie>>>()
    val movieCompilations: Map<Category, MutableLiveData<List<Movie>>>
        get() = _movieCompilations

    init {
        categories.forEach { category ->
            _movieCompilations[category] = MutableLiveData<List<Movie>>().apply {
                //value = listOf<Movie>(Movie())
            }
        }
    }

    fun requestMovieCompilations() {
        thread {
            sleep(5000)
            _movieCompilations.forEach { category, mutableLiveData ->
                mutableLiveData.postValue(repository.getMovies(category))
            }
        }
    }

}