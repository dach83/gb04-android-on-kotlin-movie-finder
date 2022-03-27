package com.example.gb04_android_on_kotlin_movie_finder.presentation.movie

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.gb04_android_on_kotlin_movie_finder.data.Repository
import com.example.gb04_android_on_kotlin_movie_finder.domain.model.movie.MovieCompilation
import com.example.gb04_android_on_kotlin_movie_finder.domain.model.poster.Poster
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class MovieMainViewModel @Inject constructor(private val repository: Repository) : ViewModel() {

    val postersFlow = buildMap {
        MovieCompilation.values().forEach { compilation ->
            put(compilation, repository.getMovieCompilation(compilation).cachedIn(viewModelScope))
        }
    }

}

