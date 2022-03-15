package com.example.gb04_android_on_kotlin_movie_finder.ui.movie_compilations

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.gb04_android_on_kotlin_movie_finder.data.RepositoryImpl
import com.example.gb04_android_on_kotlin_movie_finder.data.api.CompilationApi
import com.example.gb04_android_on_kotlin_movie_finder.data.api.MovieDbLoader
import com.example.gb04_android_on_kotlin_movie_finder.domain.MovieCompilationResponseState
import com.example.gb04_android_on_kotlin_movie_finder.domain.ResponseState
import com.example.gb04_android_on_kotlin_movie_finder.domain.entity.Category
import com.example.gb04_android_on_kotlin_movie_finder.domain.entity.Movie
import com.example.gb04_android_on_kotlin_movie_finder.domain.repository.Repository
import java.lang.Thread.sleep
import kotlin.concurrent.thread

class MovieCompilationsViewModel : ViewModel() {

    private val repository: Repository = RepositoryImpl
    private val movieDbLoader = MovieDbLoader(object: MovieDbLoader.MovieLoaderListener {
        override fun onLoaded(category: Category, movies: List<Movie>) {
            postMovieCompilationResponseState(category, ResponseState.Success(movies))
        }

        override fun onFailed(category: Category, throwable: Throwable) {
            postMovieCompilationResponseState(category, ResponseState.Error(throwable))
        }
    })

    private val _movieCompilations =
        mutableMapOf<Category, MutableLiveData<MovieCompilationResponseState>>()
    val movieCompilations: Map<Category, LiveData<MovieCompilationResponseState>>
        get() = _movieCompilations

    private fun postMovieCompilationResponseState(
        category: Category,
        state: MovieCompilationResponseState
    ) {
        _movieCompilations.computeIfAbsent(category) { MutableLiveData<MovieCompilationResponseState>() }
            .postValue(state)
    }

    fun getMovieCompilation(vararg categories: Category) {
        //thread {
            categories.forEach { category ->
                postMovieCompilationResponseState(category, ResponseState.Loading)
                movieDbLoader.loadMoviesCompilation(category)
            }
        //}
    }
}

