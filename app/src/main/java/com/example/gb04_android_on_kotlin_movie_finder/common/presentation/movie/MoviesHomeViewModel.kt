package com.example.gb04_android_on_kotlin_movie_finder.common.presentation.movie

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gb04_android_on_kotlin_movie_finder.common.data.MovieRepository
import com.example.gb04_android_on_kotlin_movie_finder.common.domain.model.movie.MovieCompilation
import com.example.gb04_android_on_kotlin_movie_finder.common.presentation.poster.PosterCompilationViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MoviesHomeViewModel @Inject constructor(private val repository: MovieRepository) : ViewModel() {

    private val _state = buildMap<MovieCompilation, MutableLiveData<PosterCompilationViewState>> {
        MovieCompilation.values().forEach {
            put(it, MutableLiveData(PosterCompilationViewState()))
        }
    }

    val state: Map<MovieCompilation, LiveData<PosterCompilationViewState>> get() = _state

    fun onEvent(event: MoviesHomeEvent) {
        when(event) {
            is MoviesHomeEvent.RequestInitialMoviesCompilations -> loadCompilations()
            is MoviesHomeEvent.RequestMoreMovies -> loadCompilationNextPage(event.compilation)
        }
    }

    private fun loadCompilations() {
        TODO("Not yet implemented")
    }

    private fun loadCompilationNextPage(compilation: MovieCompilation) {
        TODO("Not yet implemented")
    }
}

