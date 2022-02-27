package com.example.gb04_android_on_kotlin_movie_finder.ui.movies

import android.accounts.NetworkErrorException
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.gb04_android_on_kotlin_movie_finder.data.RepositoryImpl
import com.example.gb04_android_on_kotlin_movie_finder.domain.MoviesResponseState
import com.example.gb04_android_on_kotlin_movie_finder.domain.ResponseState
import com.example.gb04_android_on_kotlin_movie_finder.domain.entity.*
import com.example.gb04_android_on_kotlin_movie_finder.domain.repository.Repository
import java.lang.Thread.sleep
import kotlin.concurrent.thread
import kotlin.random.Random

class MoviesViewModel : ViewModel() {

    private val repository: Repository = RepositoryImpl()

    private val _movieCompilations = mutableMapOf<Category, MutableLiveData<MoviesResponseState>>()
    val movieCompilations: Map<Category, MutableLiveData<MoviesResponseState>>
        get() = _movieCompilations

    private fun postMovieCompilationResponseState(category: Category, state: MoviesResponseState) {
        _movieCompilations.computeIfAbsent(category) { MutableLiveData<MoviesResponseState>() }
            .postValue(state)
    }

    fun requestMovieCompilations(vararg categories: Category) {
        thread {
            categories.forEach { category ->
                postMovieCompilationResponseState(category, ResponseState.Loading)
            }

            sleep(2000)

            categories.forEach { category ->
                if (Random.nextBoolean()) {
                    postMovieCompilationResponseState(
                        category,
                        ResponseState.Success(repository.getMovies(category))
                    )
                } else {
                    postMovieCompilationResponseState(
                        category,
                        ResponseState.Error(NetworkErrorException("Сompilation ${category.title} was not loaded"))
                    )
                }
            }
        }
    }

}