package com.example.gb04_android_on_kotlin_movie_finder.domain

import androidx.paging.PagingData
import com.example.gb04_android_on_kotlin_movie_finder.domain.model.movie.MovieCompilation
import com.example.gb04_android_on_kotlin_movie_finder.domain.model.poster.Poster
import kotlinx.coroutines.flow.Flow

interface IRepository {
    fun getMovieCompilation(compilation: MovieCompilation): Flow<PagingData<Poster>>
}