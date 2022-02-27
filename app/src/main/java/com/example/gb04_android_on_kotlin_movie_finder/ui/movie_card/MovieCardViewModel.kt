package com.example.gb04_android_on_kotlin_movie_finder.ui.movie_card

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.gb04_android_on_kotlin_movie_finder.data.RepositoryImpl
import com.example.gb04_android_on_kotlin_movie_finder.domain.MovieResponseState
import com.example.gb04_android_on_kotlin_movie_finder.domain.ResponseState
import com.example.gb04_android_on_kotlin_movie_finder.domain.repository.Repository
import java.io.IOException

class MovieCardViewModel : ViewModel() {

    private val repository: Repository = RepositoryImpl

    private var _movieCard = MutableLiveData<MovieResponseState>()
    val movieCard: LiveData<MovieResponseState>
        get() = _movieCard

    fun getMovie(movieId: Int) {
        val movie = repository.getMovie(movieId)
        if (movie != null) {
            _movieCard.value = ResponseState.Success(movie)
        } else {
            _movieCard.value = ResponseState.Error(IOException("Movie id=$movieId not found"))
        }
    }
}